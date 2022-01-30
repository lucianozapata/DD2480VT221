package com.group24.decide;

import org.junit.jupiter.api.Test;


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
        Parameter parameters = new Parameter(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Datapoints[] testDataPoints = new Datapoints[2];
        testDataPoints[0] = new Datapoints(1,1);
        testDataPoints[1] = new Datapoints(1,0);
        // Distance should be 1.
        LIC testLIC = new LIC(parameters, testDataPoints);
        assertFalse(testLIC.Condition0());

    }

    @Test
    void condition1() {
    }

    @Test
    void condition2() {
    }

    @Test
    void condition3() {
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