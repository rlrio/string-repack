package com.rlrio.test;

import com.rlrio.main.StringRepack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;


public class StringRepackTest {

    @Test
    void repackValidTest() {
        Assumptions.assumeTrue(StringRepack.repack("3[xyz]4[xy]z").equals("xyzxyzxyzxyxyxyxyz"));
    }

    @Test
    void repackInvalidTest() {
        Assertions.assertDoesNotThrow(() -> StringRepack.repack(""));
        Assertions.assertDoesNotThrow(() -> StringRepack.repack("3xyz4[xy]z"));
    }
}
