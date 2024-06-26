package soft.progress.assignment.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.dto.response.DealResponse;
import soft.progress.assignment.dto.response.GeneralResponse;
import soft.progress.assignment.exception.CustomException;
import soft.progress.assignment.service.DealService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DealControllerTest {
    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealDataController;

    @Test
    public void testGetAllFxDeal() {
        List<DealResponse> expectedFxDeals = Arrays.asList(
                new DealResponse(1L, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), "JOD", "EUR", 14.99),
                new DealResponse(9L, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), "GBP", "USD", 150.54)
        );

        when(dealService.getAll()).thenReturn(expectedFxDeals);

        ResponseEntity<List<DealResponse>> responseEntity = dealDataController.getAllDeals();
        List<DealResponse> actualFxDeals = responseEntity.getBody();

        assertEquals(expectedFxDeals, actualFxDeals);
        verify(dealService, times(1)).getAll();
    }
    @Test
    public void testAddDeal() throws CustomException {
        DealRequest dealRequest = new DealRequest(1L,"USD", "EUR", 100.0);
        GeneralResponse expectedResponse = new GeneralResponse("Added Deal Successfully");

        doNothing().when(dealService).addDeal(dealRequest);

        ResponseEntity<GeneralResponse> responseEntity = dealDataController.addDeal(dealRequest);
        GeneralResponse actualResponse = responseEntity.getBody();

        assertEquals(expectedResponse, actualResponse);
        verify(dealService, times(1)).addDeal(dealRequest);
    }

    @Test
    public void testAddDealWithInvalidInput() throws CustomException {
        DealRequest dealRequest = new DealRequest(1L, "USD", "EUR", -100.0);
        doThrow(new CustomException("The amount must be greater than zero", HttpStatus.BAD_REQUEST.value())).when(dealService).addDeal(dealRequest);
        assertThrows(CustomException.class, () -> dealDataController.addDeal(dealRequest));

    }
}
