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
        Datapoints[] points1 = {
                new Datapoints(0, 1),
                new Datapoints(2, 3),
                new Datapoints(-1, 0),
                new Datapoints(-1, 1),
                new Datapoints(0,-1),
                new Datapoints(1, -1)
        };
        Parameter parameters = new Parameter(0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0);
        parameters.QUADS = 2;
        parameters.Q_PTS = 4;

        // Positive test
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition4());

        // Negative test 1: invalid input for parameter Q_PTS (input too small)
        parameters.Q_PTS = 1;
        LIC lic2 = new LIC(parameters, points1);
        assertFalse(lic2.Condition4());

        // Negative test 2: invalid input for parameter Q_PTS (input too large)
        parameters.Q_PTS = 7;
        LIC lic3 = new LIC(parameters, points1);
        assertFalse(lic3.Condition4());

        // Negative test 3: invalid input for parameter QUADs (input too small)
        parameters.QUADS = 0;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition4());

        // Negative test 4: invalid input for parameter QUADs (input too large)
        parameters.QUADS = 4;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition4());

        // Negative test 5: all inputs are valid but the result is false
        Datapoints[] points2 = {
                new Datapoints(1, 1),
                new Datapoints(1, 3),
                new Datapoints(-1, 0),
                new Datapoints(-1, 1),
                new Datapoints(0,0),
                new Datapoints(1, 0)
        };
        parameters.QUADS = 2;
        parameters.Q_PTS = 3;
        LIC lic6 = new LIC(parameters, points2);
        assertFalse(lic6.Condition4());
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