package exchange.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateExternal {
	
	String code;
    String codein;
    String name;
    String high;
    String low;
    String varBid;
    String pctChange;
    Double bid;
    Double ask;
    String timestamp;
    String create_date;

}
