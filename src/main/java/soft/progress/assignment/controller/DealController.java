package soft.progress.assignment.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.dto.response.GeneralResponse;
import soft.progress.assignment.exception.CustomException;
import soft.progress.assignment.service.DealService;

@RestController
@RequestMapping("warehouse/deal")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealController {
    DealService dealService;
    @PostMapping("/add")
    public ResponseEntity<GeneralResponse> addDeal(@RequestBody DealRequest dealRequest)  throws CustomException {
        dealService.addDeal(dealRequest);
        return ResponseEntity.ok().body(new GeneralResponse("Added Deal Successfully"));
    }
}
