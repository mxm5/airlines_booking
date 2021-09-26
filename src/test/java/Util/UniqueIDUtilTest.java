package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueIDUtilTest {

    @Test
    void generateAndCompareUID() {
        Long generateA = UniqueIDUtil.generate();
        Long generateB = UniqueIDUtil.generate();
        assertNotEquals(generateA,generateB);

    }
}