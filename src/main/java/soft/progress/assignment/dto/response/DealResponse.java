package soft.progress.assignment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DealResponse {
    Long id;
    String dealTimestamp;
    String fromCurrency;
    String toCurrency;
    Double dealAmount;

}
