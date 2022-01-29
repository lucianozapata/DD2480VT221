package com.group24.decide;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the LIC conditions.
 *
 * @author
 */
class LICTest {


    /**
     *
     */
    @Test
    void condition0() {
    }

    @Test
    void condition1() {
    }

    @Test
    void condition2() {
    }

    @Test
    @DisplayName("Condition3: compare max triangle area")
    void condition3() {
        // check max area of 3 consecutive data-points against threshold

        Parameter parameter = new Parameter();
        Datapoints[] points = { new Datapoints(0, 0),
                                new Datapoints(5, 0),
                                new Datapoints(0, 5),};
        LIC lic = new LIC(parameter, points);

        // default AREA1 is set to 0
        assertTrue(lic.Condition3());

        parameter.AREA1 = 10;
        assertTrue(lic.Condition3());

        parameter.AREA1 = 15;
        assertFalse(lic.Condition3());
    }

    @Test
    void condition4() {
    }

    @Test
    void condition5() {
    }

    @Test
    void condition6() {
    }

    @Test
    void condition7() {
    }

    @Test
    void condition8() {
    }

    @Test
    void condition9() {
    }

    @Test
    void condition10() {
    }

    @Test
    void condition11() {
    }

    @Test
    void condition12() {
    }

    @Test
    void condition13() {
    }

    @Test
    void condition14() {
    }
}