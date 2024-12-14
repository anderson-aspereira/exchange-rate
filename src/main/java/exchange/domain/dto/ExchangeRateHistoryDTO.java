package exchange.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateHistoryDTO {
	private Integer id;
	private String code;
	private String codein;
	private String name;
	private Double currentValue;
	private LocalDateTime date;
}
