package soft.progress.assignment.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DealRequest {
    Long Id;
    String fromCurrency;
    String toCurrency;
    String dealTimestamp;
    Double dealAmount;
}
