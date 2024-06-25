package soft.progress.assignment.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.entity.Deal;
import soft.progress.assignment.exception.CustomException;
import soft.progress.assignment.repository.DealRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DealService {
    DealRepository dealRepository;
    ModelMapper modelMapper;
    public void addDeal(DealRequest dealRequest) throws CustomException {
        validateDeal(dealRequest);
        Deal deal = modelMapper.map(dealRequest, Deal.class);
        dealRepository.save(deal);
    }

    private void validateDeal(DealRequest dealRequest) throws CustomException {
        validateId(dealRequest.getId());
        validateDate(dealRequest.getDealTimestamp());
        validateCurrency(dealRequest.getToCurrency(), dealRequest.getFromCurrency());
        validateAmount(dealRequest.getDealAmount());
    }

    private void validateAmount(Double dealAmount) throws CustomException {
        if(dealAmount == null) {
            throw new CustomException("The amount must not be empty", 400);
        } else if(dealAmount < 0) {
            throw new CustomException("The amount must be greater than zero", 400);
        }
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

    private void validateDate(String dealTimestamp) throws CustomException {

        if(dealTimestamp == null) {
            throw new CustomException("Date must Not be null", 400);
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(dealTimestamp);
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDate dealDate = LocalDate.parse(dealTimestamp, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDateTime dealDateTime = dealDate.atStartOfDay(); // convert LocalDate to LocalDateTime
            if (dealDateTime.isAfter(currentDate)) {
                throw new CustomException("Deal date cannot be in the future", 400);
            }
        }catch (ParseException e) {
            throw new CustomException("Date is not valid", 400);
        }
    }

    private void validateId(Long id) throws CustomException {
        if(id == null) {
            throw new CustomException("Id must Not be null", 400);
        } else if (dealRepository.findById(Math.toIntExact(id)).isPresent()) {
            throw new CustomException("Id already exist, it's not unique", 400);
        }
    }
}
