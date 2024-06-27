package soft.progress.assignment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DealResponse {
    Long id;
    String dealTimestamp;
    String fromCurrency;
    String toCurrency;
    Double dealAmount;

}
