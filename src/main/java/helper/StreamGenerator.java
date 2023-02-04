package helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamGenerator {

    public static Stream<SourcePojo> shoppingListStream(){
        List<SourcePojo> stream = new ArrayList<>();
        SourcePojo source1 = new SourcePojo("ramen", 19.99);
        SourcePojo source2 = new SourcePojo("eggs", 7.25);
        SourcePojo source3 = new SourcePojo("milk", 3.99);
        stream.add(source1);
        stream.add(source2);
        stream.add(source3);
        return stream.stream();
    }

    public static Stream<SourcePojo> shoppingListStreamWithDuplicateNames(){
        List<SourcePojo> stream = new ArrayList<>();
        SourcePojo source1 = new SourcePojo("ramen", 19.99);
        SourcePojo source2 = new SourcePojo("eggs", 7.25);
        SourcePojo source3 = new SourcePojo("milk", 3.99);
        SourcePojo source4 = new SourcePojo("milk", 5.99);
        stream.add(source1);
        stream.add(source2);
        stream.add(source3);
        stream.add(source4);
        return stream.stream();
    }

    public static Stream<SourcePojo> shoppingListStreamWithDuplicatePrice(){
        List<SourcePojo> stream = new ArrayList<>();
        SourcePojo source1 = new SourcePojo("ramen", 19.99);
        SourcePojo source2 = new SourcePojo("eggs", 7.25);
        SourcePojo source3 = new SourcePojo("milk", 3.99);
        SourcePojo source4 = new SourcePojo("granola", 3.99);
        stream.add(source1);
        stream.add(source2);
        stream.add(source3);
        stream.add(source4);
        return stream.stream();
    }

}
