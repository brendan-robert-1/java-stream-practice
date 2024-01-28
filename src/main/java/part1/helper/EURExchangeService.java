package part1.helper;

import java.math.BigDecimal;

public class EURExchangeService {

    public static BigDecimal rate(){
        return BigDecimal.valueOf(1.1);
    }

    public static BigDecimal rate(String item){
        if(item.equals("eggs")){
            throw new RuntimeException();
        }
        return BigDecimal.valueOf(0.9);
    }
}
