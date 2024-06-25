package soft.progress.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "deal")
public class Deal {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 40)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false, length = 40)
    private String toCurrency;

    @Column(name = "deal_amount", nullable = false)
    private Double dealAmount;

    @Column(name = "deal_timestamp", nullable = false)
    private LocalDate dealTimestamp;

}