package part1.operations;

import part1.helper.BigDecimalSource;
import part1.helper.EURExchangeService;
import part1.helper.LoggerHelper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class WorkingWithMaps {
    /**
     * https://www.baeldung.com/java-maps-streams
     *
     */

    public static void practice(){
        practiceFilteringWhenException();
    }

    private static void practiceFilteringWhenException(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = Arrays.asList(
                new BigDecimalSource("eggs", BigDecimal.valueOf(5.00)),
                new BigDecimalSource("ramen", BigDecimal.valueOf(6.00)),
                new BigDecimalSource("milk", BigDecimal.valueOf(4.00))
        );
        List<BigDecimalSource> filteredWhenEurRateFails = shoppingList.stream()
                .filter(WorkingWithMaps::checkEurConversion)
                .map(WorkingWithMaps::convertToEur)
                .toList();
        System.out.println("The list filtered out (eggs) and conveted to eur " + filteredWhenEurRateFails); //Would be currency pass in instead of name but im lazy
    }

    /*
     * Extracted out to make not ugly
     */
    private static boolean checkEurConversion(BigDecimalSource source) {
        try {
            EURExchangeService.rate(source.getName());
            return true;
        } catch(Exception e){
            System.out.println("could not get rate for: " + source);
            return false;
        }
    }

    private static BigDecimalSource convertToEur(BigDecimalSource source) {
        try {
            BigDecimal rate = EURExchangeService.rate(source.getName());
            return new BigDecimalSource(source.getName(), source.getAmount().multiply(rate));
        } catch(Exception e){
            return source;
        }
    }
}
