package com.group24.decide;

import org.junit.jupiter.api.DisplayName;
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

    @Test
    void condition0() {

        // Negative test where the distance is 1 and LENGTH1 is 1.
        Parameter parameters = new Parameter(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Datapoints[] testDataPoints = new Datapoints[2];
        testDataPoints[0] = new Datapoints(1,1);
        testDataPoints[1] = new Datapoints(1,0);
        LIC testLIC = new LIC(parameters, testDataPoints);
        assertFalse(testLIC.Condition0());

        //Positive test where the distance is 9 and LENGTH1 is 1.
        testDataPoints[0] = new Datapoints(0,0);
        testDataPoints[1] = new Datapoints(0,9);
        LIC postiveLIC = new LIC(parameters, testDataPoints);
        assertTrue(postiveLIC.Condition0());



    }

    @Test
    void condition1() {
        // Positive test where smallest circle has radius higher than 1.
        Parameter parameters = new Parameter(0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Datapoints[] testDataPoints = new Datapoints[3];
        testDataPoints[0] = new Datapoints(0,9);
        testDataPoints[1] = new Datapoints(0,0);
        testDataPoints[2] = new Datapoints(0,1);
        LIC testLIC = new LIC(parameters,testDataPoints);
        assertTrue(testLIC.Condition1());


        // Negative test were all points fits in a circle with radius 3.
        parameters = new Parameter(0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testDataPoints = new Datapoints[3];
        testDataPoints[0] = new Datapoints(0,1);
        testDataPoints[1] = new Datapoints(1,0);
        testDataPoints[2] = new Datapoints(0,0);
        testLIC = new LIC(parameters,testDataPoints);
        assertFalse(testLIC.Condition1());

        // Negative test where all points are on the circle with radius 1
        parameters = new Parameter(0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testDataPoints = new Datapoints[3];
        testDataPoints[0] = new Datapoints(0,1);
        testDataPoints[1] = new Datapoints(1,0);
        testDataPoints[2] = new Datapoints(0,1);
        testLIC = new LIC(parameters,testDataPoints);
        assertFalse(testLIC.Condition1());


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
        Parameter parameter = new Parameter();
        
        Datapoints[] points = { new Datapoints(0,0),
                                new Datapoints(-1,0),
                                new Datapoints(0,0),};
        LIC lic = new LIC(parameter,points);
        //there exist at least one set of two consecutive points such that X[j] - X[i] < 0 where i = j-1, LIC5 should return true.
        assertTrue(lic.Condition5());

        Datapoints[] points1 = { new Datapoints(0,0),
                                new Datapoints(1,0),
                                new Datapoints(2,0),};
        LIC lic1 = new LIC(parameter,points1);
        //there exist no set of two consecutive points such that X[j] - X[i] < 0 where i = j-1, LIC5 should return false.
        assertFalse(lic1.Condition5()); 

    }

    @Test
    @DisplayName("Condition6: Compare distance of a point and the line between two other points")
    void condition6() {
        // Positive test
        Parameter parameter = new Parameter();
        parameter.DIST = 1;
        parameter.N_PTS = 3;
        Datapoints[] points = { new Datapoints(0,0),
                new Datapoints(0,0),
                new Datapoints(0,1.01),
                new Datapoints(1,0),
        };
        LIC lic = new LIC(parameter, points);
        assertTrue(lic.Condition6());

        // Negative test
        parameter = new Parameter();
        parameter.DIST = 1;
        parameter.N_PTS = 4;
        Datapoints[] points1 = { new Datapoints(0,0),
                new Datapoints(-1,0),
                new Datapoints(1,0),
                new Datapoints(0,1),
                new Datapoints(1,1),
                };
        lic = new LIC(parameter, points1);
        assertFalse(lic.Condition6());

        // Invalid input (N_PTS>NUMPOINTS)
        parameter = new Parameter();
        parameter.DIST = 1;
        parameter.N_PTS = 5;
        Datapoints[] points2 = { new Datapoints(0,0),
                new Datapoints(-1,0),
                new Datapoints(100,0),
                new Datapoints(1,1),
        };
        lic = new LIC(parameter, points2);
        assertFalse(lic.Condition6());

        // Invalid input (DIST < 0)
        parameter = new Parameter();
        parameter.DIST = -1;
        parameter.N_PTS = 3;
        Datapoints[] points3 = { new Datapoints(0,0),
                new Datapoints(-1,0),
                new Datapoints(100,0),
                new Datapoints(1,1),
        };
        lic = new LIC(parameter, points3);
        assertFalse(lic.Condition6());


    }

    @Test
    void condition7() {
        Datapoints[] points1 = {
                new Datapoints(0, 1),
                new Datapoints(2, 3),
                new Datapoints(-1, 5),
                new Datapoints(2, -2),
                new Datapoints(-1, -1)
        };
        Parameter parameters = new Parameter();

        // Positive test
        parameters.K_PTS = 2;
        parameters.LENGTH1 = 3;
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition7());

        // Negative test 1: invalid input for points (input too few)
        Datapoints[] points2 = {
                new Datapoints(-1, 5),
                new Datapoints(-1, -1)
        };
        LIC lic2 = new LIC(parameters, points2);
        assertFalse(lic2.Condition7());

        // Negative test 2: invalid input for parameter K_PTS (input too small)
        parameters.K_PTS = 0;
        LIC lic3 = new LIC(parameters, points1);
        assertFalse(lic3.Condition7());

        // Negative test 3: invalid input for parameter K_PTS (input too small)
        parameters.K_PTS = 4;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition7());

        // Negative test 4: all inputs are valid but the result is false
        parameters.K_PTS = 2;
        parameters.LENGTH1 = 5.1;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition7());
    }

    @Test
    @DisplayName("Condition8: check if three points fit in a circle")
    void condition8() {
        Datapoints[] points1 = {
                new Datapoints(0, 1),
                new Datapoints(-2, 0),
                new Datapoints(-1, 5),
                new Datapoints(0, 2),
                new Datapoints(-1, -1),
                new Datapoints(2, 0)
        };
        Parameter parameters = new Parameter();

        // Positive test
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 3;
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition8());

        // Negative test 1: all inputs are valid but the result is false
        parameters.RADIUS1 = 1.9;
        LIC lic2 = new LIC(parameters, points1);
        assertFalse(lic2.Condition8());

        // Negative test 2: invalid input for points (input too few)
        Datapoints[] points2 = {
                new Datapoints(-1, 5),
                new Datapoints(-1, -1)
        };
        LIC lic3 = new LIC(parameters, points2);
        assertFalse(lic3.Condition8());

        // Negative test 3: invalid inputs for parameters A_PTS and B_PTS (inputs too small)
        parameters.A_PTS = 0;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition8());
        parameters.B_PTS = 0;
        parameters.A_PTS = 1;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition8());

        // Negative test 4: invalid inputs for parameters A_PTS and B_PTS (inputs too large)
        parameters.A_PTS = 3;
        parameters.B_PTS = 1;
        LIC lic6 = new LIC(parameters, points1);
        assertFalse(lic6.Condition8());

    }

    @Test
    void condition9() {
    }

    @Test
    void condition10() {
    }

    @Test
    @DisplayName("Condition 11: Checks if there is a set of Datapoints such that they are separated by G_PTS and the first is bigger than the latter.")
    void condition11() {
        // Positive test
        Parameter parameter = new Parameter(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        parameter.G_PTS = 2;

        Datapoints[] points = { new Datapoints(1, 1),
                new Datapoints(2, 0),
                new Datapoints(2, 0),
                new Datapoints(0, 0),
        };
        LIC lic = new LIC(parameter, points);
        assertTrue(lic.Condition11());

        // Negative test
        parameter.G_PTS = 1;

        Datapoints[] points1 = { new Datapoints(1, 1),
                new Datapoints(1, 0),
                new Datapoints(2, 0),
                new Datapoints(1.01, 0),
        };
        lic = new LIC(parameter, points1);
        assertFalse(lic.Condition11());

        // Invalid input
        parameter.G_PTS = 2;

        Datapoints[] points2 = { new Datapoints(1, 1),
                new Datapoints(-1, 0),
                new Datapoints(-2, 0),
        };
        lic = new LIC(parameter, points2);
        assertFalse(lic.Condition11());

        // Invalid input (G_PTS>NUMPOINTS-2)
        parameter.G_PTS = 3;

        Datapoints[] points3 = { new Datapoints(1, 1),
                new Datapoints(2, 0),
                new Datapoints(2, 0),
                new Datapoints(0, 0),
        };
        lic = new LIC(parameter, points3);
        assertFalse(lic.Condition11());

    }

    @Test
    void condition12() {
        // Positive test
        Parameter parameter = new Parameter();
        parameter.LENGTH1 = 1;
        parameter.LENGTH2 = 1;
        parameter.K_PTS = 2;
        Datapoints[] points = { new Datapoints(0,0),
                new Datapoints(0,0),
                new Datapoints(0,0.01),
                new Datapoints(1,1),
        };
        LIC lic = new LIC(parameter, points);
        assertTrue(lic.Condition12());

        // Negative test
        parameter = new Parameter();
        parameter.LENGTH1 = 2;
        parameter.LENGTH2 = 2;
        parameter.K_PTS = 2;

        lic = new LIC(parameter, points);
        assertFalse(lic.Condition12());

        // Invalid test (K_PTS>NUMPOINTS-2)
        parameter = new Parameter();
        parameter.LENGTH1 = 1;
        parameter.LENGTH2 = 1;
        parameter.K_PTS = 3;

        lic = new LIC(parameter, points);
        assertFalse(lic.Condition12());

        // Invalid test (NUMPOINTS<3)
        parameter = new Parameter();
        Datapoints[] points1 = { new Datapoints(0,0),
                new Datapoints(0,0),
        };
        parameter.LENGTH1 = 1;
        parameter.LENGTH2 = 1;
        parameter.K_PTS = 3;

        lic = new LIC(parameter, points1);
        assertFalse(lic.Condition12());



    }

    @Test
    void condition13() {
        Parameter parameter = new Parameter(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Datapoints[] points = { new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(0, 1),
                new Datapoints(1, 1),
                new Datapoints(2, 2),
        };
        LIC lic = new LIC(parameter, points);

        // for these points then smallest radius is 0.5 and biggest 1.15
        parameter.RADIUS1 = 1;
        parameter.RADIUS2 = 1;
        assertTrue(lic.Condition13());

        parameter.RADIUS1 = 2;
        parameter.RADIUS2 = 1;
        assertFalse(lic.Condition13());
    }

    @Test
    @DisplayName("Condition 14: smaller and bigger triangle area")
    void condition14() {

        Parameter parameter = new Parameter(0,0,0,10.0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1.0);

        Datapoints[] point = { new Datapoints(0, 0) };
        LIC lic = new LIC(parameter, point);

        // less than 5 data-points
        assertFalse(lic.Condition14());

        // first 3 points create triangle bigger than 10, last 3 points smaller than 1
        Datapoints[] points = { new Datapoints(0, 0),
                                new Datapoints(5, 0),
                                new Datapoints(0, 5),
                                new Datapoints(1, 5),
                                new Datapoints(1, 6),
        };
        lic = new LIC(parameter, points);
        assertTrue(lic.Condition14());

        // 3 first data points have the biggest area but still < 15
        parameter.AREA1 = 15;
        assertFalse(lic.Condition14());

        // last 3 data points have the smallest are but > 0
        parameter.AREA1 = 10;
        parameter.AREA2 = 0;
        assertFalse(lic.Condition14());
    }


}