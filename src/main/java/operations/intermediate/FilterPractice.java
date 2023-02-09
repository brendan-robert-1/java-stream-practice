package operations.intermediate;

import helper.BigDecimalSource;
import helper.LoggerHelper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterPractice {
    /**
     * https://www.baeldung.com/java-stream-filter-lambda
     */

    public static void practice(){
        practice20();
        practiceExceptionHandling();
        practiceEggs();
        practiceEggs20();
    }

    private static void practice20(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = getBigDecimalSources();
        List<BigDecimalSource> shoppingListFiltered = shoppingList.stream()
                .filter(item -> item.getAmount().compareTo(BigDecimal.valueOf(20.00)) < 0)
                .collect(Collectors.toList());
        System.out.println("the list was filtered of all items over 20.00: " + shoppingListFiltered.toString());

    }

    private static void practiceEggs(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = getBigDecimalSources();
        List<BigDecimalSource> filtered = shoppingList.stream()
                .filter(item -> !item.getName().equals("eggs"))
                .collect(Collectors.toList());
        System.out.println("The list was filtered of eggs: " + filtered.toString());
    }

    private static void practiceEggs20(){
        LoggerHelper.logLine();
        List<BigDecimalSource> list = getBigDecimalSources();
        List<BigDecimalSource> filtered = list.stream().filter(
                item -> !item.getName().equals("eggs") || item.getAmount().compareTo(BigDecimal.valueOf(20.00)) < 0
        ).collect(Collectors.toList());
        System.out.println("List filtered of eggs over 20.00: " + filtered.toString());
    }

    //TODO filter collection with method call that can throw an exception
    private static void practiceExceptionHandling(){
        LoggerHelper.logLine();
    }

    private static List<BigDecimalSource> getBigDecimalSources(){
        List<BigDecimalSource> sources = Arrays.asList(
                new BigDecimalSource("eggs", BigDecimal.valueOf(25.00)),
                new BigDecimalSource("eggs", BigDecimal.valueOf(1.99)),
                new BigDecimalSource("coffee", BigDecimal.valueOf(21.99)),
                new BigDecimalSource("cereal", BigDecimal.valueOf(4.00))

        );
        return sources;
    }
}
