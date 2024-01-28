package part1.operations.intermediate;

import part1.helper.BigDecimalSource;
import part1.helper.EURExchangeService;
import part1.helper.LoggerHelper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MapPractice {

    /**
     * .map() is an intermediate operation that takes each element of the original stream, applies any function and returns an object of the same type
     * to the stream. Think of it like a mutator.
     *
     * One of the archetypical examples is having a list of integers and stream.map(x -> x *3) to return a list of integers with all
     * the original values multiplied by 3.
     *
     * Another example could be having a list of complex pojos with a currency amount and applying a conversion function to the amount
     * to be say USD to EUR
     */
    public static void practice(){
        practiceComplexPojoConversionToEur();
    }

    private static void practiceComplexPojoConversionToEur(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = Arrays.asList(
                new BigDecimalSource("eggs", BigDecimal.valueOf(5.00)),
                new BigDecimalSource("ramen", BigDecimal.valueOf(6.00)),
                new BigDecimalSource("milk", BigDecimal.valueOf(4.00))
        );
        List<BigDecimalSource> eurShoppingList = shoppingList.stream()
                .map(
                        item -> {
                          try {
                              BigDecimal rate = EURExchangeService.rate();
                              BigDecimal eurVal = item.getAmount().multiply(rate);
                              item.setAmount(eurVal);
                              return item;
                          } catch (Exception e){
                              System.out.println("error");
                          }
                        return item;
                        }
                ).toList();
        System.out.println("The shopping list converted to eur values is: " + eurShoppingList);
    }

}
