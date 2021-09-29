package Services.Impls;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    void listSortTest() {
        List<Integer> a = Arrays.asList(1, 2, 3);
//        a.add(4);
        assertThrows(UnsupportedOperationException.class, () -> a.add(4));
        List<Integer> b = new ArrayList();
        b.addAll(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> c = Stream.of(1, 3,3,4).toList();
//        c.add(4);
        assertThrows(
                UnsupportedOperationException.class,
                () -> c.add(4));
        List d = newArrayList(1, 3, 3, 2, 4, 5);
        d.add(43);
    }
}