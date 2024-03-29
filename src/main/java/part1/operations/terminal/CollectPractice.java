package part1.operations.terminal;
import part1.helper.LoggerHelper;
import part1.helper.SourcePojo;
import part1.helper.StreamGenerator;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * https://www.baeldung.com/java-8-collectors
 */
public class CollectPractice {

    /**
     * stream.collect() is a terminating part1.operations that generate a collection of our choosing
     */
    public static void practice() {
        System.out.println("Practicing collect practice.");
        practiceToSet();
        practiceToList();
        practiceToMapWithNoDuplicateKeys();
        practiceToMapWithDuplicateProtection();
        practiceCounting();
        practiceMaxBy();
        practiceGroupingBy();
    }

    /**
     * Since sets don't have duplicates if the original list has any duplicates these will not be duplicated in the set
     */
    private static void practiceToSet() {
        LoggerHelper.logLine();
        List<String> listWithDuplicates = Arrays.asList("a", "a", "b", "c", "d");
        Set<String> result = listWithDuplicates.stream().collect(toSet());
        System.out.println("resulting set contains no duplicates with size: " + result.size() + " with original size of list: " + listWithDuplicates.size());
    }

    private static void practiceToList() {
        List<String> listWithDuplicates = Arrays.asList("a", "a", "b", "c", "d");
        List<String> fromStream = listWithDuplicates.stream().collect(toList());

        //Java 12 allows returning unmodifiable lists:
        List<String> immutable = listWithDuplicates.stream().collect(toUnmodifiableList());
    }



    /**
     * Maps require two functions to build the map, keyMapper and valueMapper
     * By default collecting to a map doesnt work if there are duplicate keys in the original stream
     * if there are duplicates an IllegalStateException will be thrown. So to be safe you really need to
     * have a third parameter to tell the collecter what to do if theres a duplicate.
     */
    private static void practiceToMapWithDuplicateProtection() {
        LoggerHelper.logLine();
        Stream<SourcePojo> shoppingListWithDuplicates = StreamGenerator.shoppingListStreamWithDuplicateNames();
        Map<String, Double> map = shoppingListWithDuplicates
                .collect(toMap(
                        SourcePojo::getName,
                        SourcePojo::getAmount,
                        (item, identicalItem) -> item //this is the binary operator that tells the collector to use original item if identical key exists
                ));
        System.out.println("The map with duplicates that resolves duplicate issues: ");
        for(String key : map.keySet()) {
            System.out.println("Key: " + key + ", value: " + map.get(key));
        }
    }

    private static void practiceToMapWithNoDuplicateKeys() {
        LoggerHelper.logLine();
        Stream<SourcePojo> noDupes = StreamGenerator.shoppingListStream();
        Map<String, Double> map = noDupes.collect(toMap(SourcePojo::getName, SourcePojo::getAmount));
        System.out.println("The map with no duplicates collected: ");
        for(String key : map.keySet()) {
            System.out.println("Key: " + key + ", value: " + map.get(key));
        }
    }

    private static void practiceCollectionAndThen(Stream<SourcePojo> stream) {
        /*allows one additional lambda to be run after the collection is complete
            this is mainly useful in pre java 12 for collecting to unmodifiable maps/lists etc since
            this functionality wasnt native
        */
    }

    private static void practiceCounting() {
        LoggerHelper.logLine();
        long count = StreamGenerator.shoppingListStream()
                .collect(counting());
        System.out.println("Number of items in the shopping list: " + count);
    }

    private static void practiceMaxBy() {
        LoggerHelper.logLine();
        List<String> list = Arrays.asList("a", "c", "b", "e");
        Optional<String> max = list.stream()
                .collect(maxBy(
                        Comparator.naturalOrder()
                ));
        System.out.println("Max string by natural order: " + max.get());

    }



    /**
     * Grouping by is essentially returning a multimap
     */
    private static void practiceGroupingBy() {
        LoggerHelper.logLine();
        Map<Double, Set<SourcePojo>> result = StreamGenerator.shoppingListStreamWithDuplicatePrice()
                .collect(groupingBy(
                        SourcePojo::getAmount, toSet()
                ));
        System.out.println("The multimap generated by grouping by ");
        for(Double key : result.keySet()) {
            System.out.println("Key: " + key + ", products at this price: " + result.get(key));
        }
    }
}
