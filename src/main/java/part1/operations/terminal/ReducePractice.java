package part1.operations.terminal;

import part1.helper.BigDecimalSource;
import part1.helper.EURExchangeService;
import part1.helper.LoggerHelper;
import part1.helper.SourcePojo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ReducePractice {

    /**
     * The reduce terminal part1.operations has 3 components.
     *
     * Identity - an element that is the initial value of the reduction operation and the default result if the stream is empty
     *
     * Accumulator - a function that takes two parameters: a partial result of the reduction and the next element
     *
     * Combiner - a function used to combine the partial result of the reduction operation when parallelized or when there is an argument
     * mismatch
     *
     * https://www.baeldung.com/java-stream-reduce
     */

    public static void practice() {
        practiceBasicReduce();
        practiceJoiningStrings();
        practiceToUpperCase();
        practiceSummingPojos();
        practicePojoSumming2();
        practiceTotalComplexPojos2();
        exceptionHandlingWhileReducing();
    }

    private static void practiceBasicReduce() {
        LoggerHelper.logLine();
        List<Integer> list = Arrays.asList(1,4,5,98);
        Integer result = list.stream().reduce(
            0, //Identity, initial value and default result if stream is empty
            (subtotal, element) -> subtotal +  element //accumulator can be replaced by the method reference Integer::sum
        );
        System.out.println("The result of reducing by adding integers: " + result);
    }

    private static void practiceJoiningStrings() {
        LoggerHelper.logLine();
        List<String> list = Arrays.asList("h", "e", "l", "l", "o");
        String result = list.stream().reduce(
                "", //Identity
                (partial, element) -> partial + element //accumulator can also  use String::concat for this
        );
        System.out.println("The result of reducing to concatenate a string: " + result);
    }

    private static void practiceToUpperCase(){
        LoggerHelper.logLine();
        List<String> list = Arrays.asList("h", "e", "l", "l", "o");
        String result = list.stream().reduce(
                "", (partial, next) -> partial.toUpperCase() + next.toUpperCase()
        );
        System.out.println("The result of using reduce to uppercase a list of characters: " + result);
    }

    /**
     * Parallel streams - Java can split the streams into two substreams, but we need a combiner to rejoin them.
     */

    private static void practiceSummingPojos(){
        LoggerHelper.logLine();
        List<SourcePojo> shoppingList = Arrays.asList(new SourcePojo("milk", 3.99), new SourcePojo("eggs", 7.99));
        Double total = shoppingList.stream()
                .reduce(
                        0.0, //Identity
                        (partialTotal, item) -> partialTotal + item.getAmount(), //Accumulator
                        Double::sum //Combiner
                );
        System.out.println("The total of the shopping list when reduced using a combiner is: " + total);
    }



    //Reduce is a terminal operation that is used for doing part1.operations like summing, concatenating etc you need 3 params
    //an identity, an accumulator and potentially a combiner if working with pojos typically or parrallelized streams

    public static void practicePojoSumming2(){
        LoggerHelper.logLine();
        List<SourcePojo> shoppingList = Arrays.asList(new SourcePojo("eggs", 10.00), new SourcePojo("chocolate", 300.0), new SourcePojo("potatoes", 1.99));
        //filter out potatoes
        Double total = shoppingList.stream()
                .filter(item -> !item.getName().equals("potatoes"))
                .reduce(0.00, (partial, next) -> partial + next.getAmount(), Double::sum);
        System.out.println("The total with potatoes filtered out is: " + total + " should be 310.0");
    }


    public static void practiceTotalComplexPojos2(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = Arrays.asList(
                new BigDecimalSource("beef", BigDecimal.valueOf(6.00)),
                new BigDecimalSource("cereal", BigDecimal.valueOf(10.50))
        );
        BigDecimal result = shoppingList.stream().reduce(
                BigDecimal.ZERO,
                (partial, next) -> partial.add(next.getAmount()),
                BigDecimal::add
        );
        System.out.println("Sum of complex pojos using big decimal add: " + result + " should be 16.50");
    }

    public static void exceptionHandlingWhileReducing() {
        List<BigDecimalSource> shoppingList = getBigDecimalSources();
        BigDecimal total = shoppingList.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (partial, next) -> partial.add(convertToEUR(next.getAmount())),
                        BigDecimal::add
                );
        System.out.println("Excpetion handling total: " + total);
    }

    private static BigDecimal convertToEUR(BigDecimal value){
        try {
            if(value.equals(BigDecimal.valueOf(7.00))){
                throw new RuntimeException();
            }
            BigDecimal rate = EURExchangeService.rate();
            return value.multiply(rate);
        } catch(Exception e){
            System.out.println("ERROR converting to EUR.");
        }
        return BigDecimal.ZERO;
    }

    //TODO
    public static void complexPojoReducing(){
        LoggerHelper.logLine();
        List<BigDecimalSource> shoppingList = getBigDecimalSources();
        BigDecimal total = shoppingList.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (partial, next) -> partial.add(next.getAmount()),
                        BigDecimal::add
                );
        System.out.println("Complex pojo reducing total: " + total);
    }

    private static List<BigDecimalSource> getBigDecimalSources(){
        List<BigDecimalSource> sources = Arrays.asList(
                new BigDecimalSource("eggs", BigDecimal.valueOf(7.00)),
                new BigDecimalSource("milk", BigDecimal.valueOf(1.99)),
                new BigDecimalSource("coffee", BigDecimal.valueOf(19.99))
        );
        return sources;
    }
}
