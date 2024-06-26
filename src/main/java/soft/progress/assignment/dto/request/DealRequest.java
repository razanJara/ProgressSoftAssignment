package soft.progress.assignment.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DealRequest {
    Long Id;
    String fromCurrency;
    String toCurrency;
    Double dealAmount;
}
