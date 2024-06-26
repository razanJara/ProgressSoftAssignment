package soft.progress.assignment.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.dto.response.DealResponse;
import soft.progress.assignment.entity.Deal;
import soft.progress.assignment.exception.CustomException;
import soft.progress.assignment.repository.DealRepository;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class DealService {
    DealRepository dealRepository;
    ModelMapper modelMapper;
    public void addDeal(DealRequest dealRequest) throws CustomException {
        log.info("Adding a new deal: {}", dealRequest);
        validateDeal(dealRequest);
        Deal deal = modelMapper.map(dealRequest, Deal.class);
        dealRepository.save(deal);
        log.info("Deal has been added: {}", deal);
    }

    private void validateDeal(DealRequest dealRequest) throws CustomException {
        log.debug("Validate deal: {}", dealRequest);
        validateId(dealRequest.getId());
        validateCurrency(dealRequest.getToCurrency(), dealRequest.getFromCurrency());
        validateAmount(dealRequest.getDealAmount());
        log.debug("Deal validation was successful.");
    }

    private void validateAmount(Double dealAmount) throws CustomException {
        if(dealAmount == null) {
            throw new CustomException("The amount must not be empty", 400);
        } else if(dealAmount < 0) {
            throw new CustomException("The amount must be greater than zero", 400);
        }
        log.debug("Amount validation was successful.");
    }

    private void validateCurrency(String toCurrency, String fromCurrency) throws CustomException{
        if(toCurrency == null || fromCurrency == null) {
            throw new CustomException("Currency must not be null", 400);
        } else{
            try {
                Currency.getInstance(toCurrency);
                Currency.getInstance(fromCurrency);
            } catch (IllegalArgumentException e) {
                throw new CustomException("Currency is not valid", 400);
            }
        }
    }

    private void validateId(Long id) throws CustomException {
        if(id == null) {
            throw new CustomException("Id must Not be null", 400);
        } else if (dealRepository.findById(Math.toIntExact(id)).isPresent()) {
            throw new CustomException("Id already exist, it's not unique", 400);
        }
    }

    public List<DealResponse> getAll() {
        List<Deal> allDeals = dealRepository.findAll();
        return allDeals.stream()
                .map(deal -> modelMapper.map(deal, DealResponse.class))
                .collect(Collectors.toList());

    }
}
