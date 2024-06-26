package soft.progress.assignment.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.dto.response.DealResponse;
import soft.progress.assignment.entity.Deal;
import soft.progress.assignment.exception.CustomException;
import soft.progress.assignment.repository.DealRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DealServiceTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealService dealService;
    @Test
    void getAllFx() {
        List<Deal> mockDeals = new ArrayList<>();
        Mockito.when(dealRepository.findAll()).thenReturn(mockDeals);
        List<DealResponse> result = dealService.getAll();
        assertTrue(result.isEmpty());
    }
    @Test
    void addDealFx() {
        DealRequest dealRequest = new DealRequest(1L, "JOD", "GBP", 35.89);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(new Deal());
        Mockito.when(dealRepository.save(Mockito.any(Deal.class))).thenReturn(new Deal(1L, "JOD", "GBP", 35.89, LocalDateTime.now()));
        assertDoesNotThrow(() -> dealService.addDeal(dealRequest));
    }
    @Test
    void addDealFx_withInvalidDealRequest_shouldThrowCustomException() {
        DealRequest dealRequest = new DealRequest(1L, "JOD", "GBP", -35.89);
        assertThrows(CustomException.class, () -> dealService.addDeal(dealRequest));
    }

}
