package com.group24.decide;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the LIC conditions.
 */
class LICTest {

    /**
     * Create random parameter settings for test purposes
     * @return randomized parameter
     */
    Parameter createRandomParameter() throws IllegalAccessException {

        int randInt;
        double randDouble;
        Parameter parameters = new Parameter();

        // Set random parameter values
        for (Field f : parameters.getClass().getDeclaredFields()) {
            Class<?> tmp = f.getType();
            if (tmp.equals(Integer.TYPE)) {
                // create Integers between 0 and 100
                randInt =ThreadLocalRandom.current().nextInt(0, 101);
                f.set(parameters, randInt);
            } else {
                // create Doubles between 0 and 100
                randDouble =ThreadLocalRandom.current().nextDouble(0, 100);
                f.set(parameters, randDouble);
            }
        }
        return  parameters;
    }


    /**
     * Test LIC conditions with randomized data points and parameters
     */
    @Test
    void runLICConditions() throws IllegalAccessException {

        int NUMBER_TESTS = 10000;
        int numberDataPoints, counter;
        double xAxis, yAxis;
        LIC testLIC;
        Parameter parameters;

        for (int i = 0; i < NUMBER_TESTS; i++) {
            parameters = createRandomParameter();
            // create between 2 and 100 data points
            numberDataPoints= ThreadLocalRandom.current().nextInt(2, 100 + 1);
            Datapoints[] testDataPoints = new Datapoints[numberDataPoints];
            counter = 0;
            while(counter < numberDataPoints) {
                // data points between -100 and 100
                xAxis = ThreadLocalRandom.current().nextDouble(-100, 100);
                yAxis = ThreadLocalRandom.current().nextDouble(-100, 100);
                testDataPoints[counter] = new Datapoints(xAxis, yAxis);
                counter++;
            }
            testLIC = new LIC(parameters, testDataPoints);
            testLIC.runLICConditions(15);
        }
    }

    /**
     * Checks if the CMV vector is the same as the expected vector (expectedCMV).
     */
    @Test
    void checkCMV(){
        Parameter parameters = new Parameter(1,1,0,4,4,3,2,3,1,1,1,1,1,1,1,2,1,2,1);
        Datapoints[] testDataPoints = new Datapoints[5];

        testDataPoints[0] = new Datapoints(0,0);
        testDataPoints[1] = new Datapoints(2,0);
        testDataPoints[2] = new Datapoints(5,0);
        testDataPoints[3] = new Datapoints(0,5);
        testDataPoints[4] = new Datapoints(3,1);
        LIC testLIC = new LIC(parameters, testDataPoints);

        boolean[] expectedCMV = {
                true, true, true, true, false,
                true, true, true, true, true,
                false, false, false, false, false
        };
        boolean[] CMV = testLIC.runLICConditions(15);
        assertArrayEquals(expectedCMV, CMV);
    }

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

        // Negative test because of LENGTH1 is less than 0
        parameters = new Parameter(-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        LIC negLIC = new LIC(parameters,testDataPoints);
        assertFalse(negLIC.Condition0());

        //Negative test because of only having 1 datapoint
        Datapoints[] oneDataPoint = new Datapoints[1];
        oneDataPoint[0] = new Datapoints(1, 4);
        LIC onePointLIC = new LIC(parameters, oneDataPoint);
        assertFalse(onePointLIC.Condition0());

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

        // Negative test where RADIUS1 is less than 0
        parameters = new Parameter(0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testLIC = new LIC(parameters, testDataPoints);
        assertFalse(testLIC.Condition1());

        // Negative test where the number of datapoints is less than 3.
        testDataPoints = new Datapoints[2];
        testDataPoints[0] = new Datapoints(0,1);
        testDataPoints[1] = new Datapoints(1,0);
        testLIC = new LIC(parameters,testDataPoints);
        assertFalse(testLIC.Condition1());
    }

    @Test
    void condition2() {

        // Positive test where angle is less than PI - EPSILON where EPSILON equals PI/4
        Parameter parameters = new Parameter(0,0,Math.PI/4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Datapoints[] testDataPoints = new Datapoints[3];
        // This creates a triangle where (0,0) is the vertex datapoint and the angle is 90 degrees (PI/2)
        testDataPoints[0] = new Datapoints(1,0);
        testDataPoints[1] = new Datapoints(0,0);
        testDataPoints[2] = new Datapoints(0,1);

        LIC testLIC = new LIC(parameters, testDataPoints);
        // angle is PI/2 and (PI-EPSILON) is 3PI/4
        assertTrue(testLIC.Condition2());

        // Positive test where angle is greater than PI + EPSILON where EPSILON equals 0
        parameters = new Parameter(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testDataPoints[0] = new Datapoints(1,0);
        testDataPoints[1] = new Datapoints(0,0);
        testDataPoints[2] = new Datapoints(-1,1);

        testLIC = new LIC(parameters,testDataPoints);
        assertTrue(testLIC.Condition2());

        //Negative test where angle is equal to (PI-EPSILON) where EPSILON = 0
        parameters = new Parameter(0,0,Math.PI/2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testDataPoints[0] = new Datapoints(1,0);
        testDataPoints[1] = new Datapoints(0,0);
        testDataPoints[2] = new Datapoints(0,1);
        testLIC = new LIC(parameters,testDataPoints);

        assertFalse(testLIC.Condition2());

        // Negative test where datapoints coincede. Epsilon is whatever
        parameters = new Parameter(0,0,Math.PI/2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        testDataPoints[0] = new Datapoints(0,0);
        testDataPoints[1] = new Datapoints(0,0);
        testDataPoints[2] = new Datapoints(0,1);
        testLIC = new LIC(parameters,testDataPoints);

        assertFalse(testLIC.Condition2());

    }

    @Test
    void condition3() {
        // check max area of 3 consecutive data-points against threshold
        Parameter parameter = new Parameter();
        // Area given by the three data points is 12.5
        Datapoints[] points = { new Datapoints(0, 0),
                                new Datapoints(5, 0),
                                new Datapoints(0, 5),};
        LIC lic = new LIC(parameter, points);

        // Positive test because default value for AREA is 0.
        assertTrue(lic.Condition3());

        parameter.AREA1 = 10;
        // Positive test because 12.5 is greater than AREA1 (10)
        assertTrue(lic.Condition3());

        parameter.AREA1 = 15;
        // Negative test because 12.5 is smaller than AREA1 (15)
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

        // Positive test because points1[1], points1[2], points1[3], points1[4] are in three different quadrants.
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition4());

        // Negative test 1: invalid input for parameter Q_PTS (Q_PTS should be bigger than 1)
        parameters.Q_PTS = 1;
        LIC lic2 = new LIC(parameters, points1);
        assertFalse(lic2.Condition4());

        // Negative test 2: invalid input for parameter Q_PTS (Q_PTS should not exceed the number of points)
        parameters.Q_PTS = 7;
        LIC lic3 = new LIC(parameters, points1);
        assertFalse(lic3.Condition4());

        // Negative test 3: invalid input for parameter QUADS (QUADS should be bigger than 0)
        parameters.QUADS = 0;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition4());

        // Negative test 4: invalid input for parameter QUADS (QUADS should be smaller than 4)
        parameters.QUADS = 4;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition4());

        // Negative test 5: all inputs are valid but the result is false, since all points are in two quadrants.
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
        //There exist at least one set of two consecutive points such that X[j] - X[i] &lt 0 where i = j-1, LIC5 should return true.
        assertTrue(lic.Condition5());

        Datapoints[] points1 = { new Datapoints(0,0),
                                new Datapoints(1,0),
                                new Datapoints(2,0),};
        LIC lic1 = new LIC(parameter,points1);
        //There exist no set of two consecutive points such that X[j] - X[i] &lt 0 where i = j-1, LIC5 should return false.
        assertFalse(lic1.Condition5()); 

        Datapoints[] points2 = { new Datapoints(1,1)};
        LIC lic2 = new LIC(parameter,points2);
        //Testing with a valid input that contains less than two data points. LIC5 should return false.
        assertFalse(lic2.Condition5());



    }

    @Test
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
        // The distance between the line made up of point 2 and 4, and to that of point 3 is 1.01 which is > DIST
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
        // None of the points 2,3 has DIST>line from point 1 and 4. Which is the same for points 3,4 to the line made up from 2 and 5
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

        // Positive test: return true since points[0] and points[3] has a distance greater than 3.
        parameters.K_PTS = 2;
        parameters.LENGTH1 = 3;
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition7());

        // Negative test 1: invalid input for points (points less than 3)
        Datapoints[] points2 = {
                new Datapoints(-1, 5),
                new Datapoints(-1, -1)
        };
        LIC lic2 = new LIC(parameters, points2);
        assertFalse(lic2.Condition7());

        // Negative test 2: invalid input for parameter K_PTS (K_PTS should be bigger than 0)
        parameters.K_PTS = 0;
        LIC lic3 = new LIC(parameters, points1);
        assertFalse(lic3.Condition7());

        // Negative test 3: invalid input for parameter K_PTS (K_PTS should not exceed the number of points - 2)
        parameters.K_PTS = 4;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition7());

        // Negative test 4: all inputs are valid but the result is false since no pair of points with interval of two points
        // has distance greater than 5.1.
        parameters.K_PTS = 2;
        parameters.LENGTH1 = 5.1;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition7());
    }

    @Test
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

        // Positive test: return true since points1[1], points1[3], points1[5] form a circle with radius = 2, larger than 1.9.
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.RADIUS1 = 1.9;
        LIC lic1 = new LIC(parameters, points1);
        assertTrue(lic1.Condition8());

        // Negative test 1: all inputs are valid but the result is false, since points1[1], points1[3], points1[5]
        // form a circle with radius = 2, smaller than 3.
        parameters.RADIUS1 = 3;
        LIC lic2 = new LIC(parameters, points1);
        assertFalse(lic2.Condition8());

        // Negative test 2: invalid input for points (points should be no less than 5)
        Datapoints[] points2 = {
                new Datapoints(-1, 5),
                new Datapoints(-1, -1)
        };
        LIC lic3 = new LIC(parameters, points2);
        assertFalse(lic3.Condition8());

        // Negative test 3: invalid inputs for parameters A_PTS and B_PTS (A_PTS or B_PTS < 1)
        parameters.A_PTS = 0;
        LIC lic4 = new LIC(parameters, points1);
        assertFalse(lic4.Condition8());
        parameters.B_PTS = 0;
        parameters.A_PTS = 1;
        LIC lic5 = new LIC(parameters, points1);
        assertFalse(lic5.Condition8());

        // Negative test 4: invalid inputs for parameters A_PTS and B_PTS (A_PTS + B_PTS > number of points - 3)
        parameters.A_PTS = 3;
        parameters.B_PTS = 1;
        LIC lic6 = new LIC(parameters, points1);
        assertFalse(lic6.Condition8());

    }

    @Test
    void condition9() {
            
        Parameter parameter = new Parameter();
        Datapoints[] points = {
                new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(0, 1.0),
                new Datapoints(0, 0),
                new Datapoints(1.0, 1.0)};
        
        parameter.EPSILON = Math.PI/2.1;
        parameter.C_PTS =1;
        parameter.D_PTS=1;
        LIC lic = new LIC(parameter,points);
        ////For the 5 NUMPOINTS there exist at least one set of three points separated by exactly 1 and 1 
        //consecutive intervening points respectively that form an angle that is either smaller than PI - (Math.PI/2.1)
        // or bigger than PI + (Math.PI/2.1), the result return true.
        assertTrue(lic.Condition9());

        Datapoints[] points1 = {
                new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(0, 1.0),
                new Datapoints(0, 0),
                new Datapoints(1.0,1.0)};
        parameter.EPSILON = Math.PI/2;
        parameter.C_PTS = 1;
        parameter.D_PTS = 1;
        
        LIC lic1 = new LIC(parameter,points1);
        ////For the 5 NUMPOINTS exist no set of three points separated by exactly 1 and 1 
        //consecutive intervening points respectively that form an angle that is either smaller than PI - (Math.PI/2)
        // or bigger than PI + (Math.PI/2), the result return false.
        assertFalse(lic1.Condition9());

        Datapoints[] points2 = {
                new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(0, 1.0),
                new Datapoints(0, 0)};
        parameter.EPSILON = Math.PI/2;
        parameter.C_PTS = 1;
        parameter.D_PTS = 1;
        
        LIC lic2 = new LIC(parameter,points2);
        //Should return false if NUMPOINTS less than 5, the result return false.
        assertFalse(lic2.Condition9());

    }

    @Test
    void condition10() {
            Parameter parameter = new Parameter();
            Datapoints[] points = {
                new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(1.0, 2.0),
                new Datapoints(0, 0),
                new Datapoints(2.0, 0)};
           parameter.AREA1 = 1.95;
           parameter.E_PTS =1;
           parameter.F_PTS =1;
         //For the 5 NUMPOINTS there exist at least one set of three points separated by exactly 1 and 1 consecutive
         // intervening points respectively that are the vertices of a triangle with area greater than 1.95
         // the result return true.
           LIC lic = new LIC(parameter,points);
           assertTrue(lic.Condition10());

           Datapoints[] points1 = {
                new Datapoints(-2.0, 0),
                new Datapoints(0, 0),
                new Datapoints(-1.0, -2.0),
                new Datapoints(0, 0),
                new Datapoints(0, 0)};
           parameter.AREA1 = 2.0;
           parameter.E_PTS =1;
           parameter.F_PTS =1;
          //For the 5 NUMPOINTS there exist no set of three points separated by exactly 1 and 1 consecutive intervening points
          //intervening points respectively that are the vertices of a triangle with area greater than 2.
          // the result return false.
           LIC lic1 = new LIC(parameter,points1);
           assertFalse(lic1.Condition10());

            
            Datapoints[] points2 = {
                new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(1.0, 2.0),
                new Datapoints(0, 0)};
           parameter.AREA1 = 1.95;
           parameter.E_PTS =1;
           parameter.F_PTS =1;
         //Should return false if NUMPOINTS less than 5, the result return false.
           LIC lic2 = new LIC(parameter,points2);
           assertFalse(lic2.Condition10());
    }

    @Test
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
        // Should be true since x[3] - x[0] = 0 - 1 < 0.
        assertTrue(lic.Condition11());

        // Negative test
        parameter.G_PTS = 1;

        Datapoints[] points1 = { new Datapoints(1, 1),
                new Datapoints(1, 0),
                new Datapoints(2, 0),
                new Datapoints(1.01, 0),
        };
        lic = new LIC(parameter, points1);
        // Should be false since x[2] - x[0] = 2 - 1 > 0 and x[3] - x[1] = 1.01 - 1 > 0
        assertFalse(lic.Condition11());

        // Invalid input since G_PTS>NUMPOINTS-2
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
        parameter.LENGTH2 = 2;
        parameter.K_PTS = 2;
        Datapoints[] points = { new Datapoints(0,0),
                new Datapoints(0,0),
                new Datapoints(0,0.01),
                new Datapoints(1,1),
        };
        LIC lic = new LIC(parameter, points);
        // Dist between first and last point is 1 < srt(2) < 2
        assertTrue(lic.Condition12());

        // Negative test
        parameter = new Parameter();
        parameter.LENGTH1 = 2;
        parameter.LENGTH2 = 3;
        parameter.K_PTS = 2;

        lic = new LIC(parameter, points);
        // Dist between first and last point is sqrt(2) < 2 (length1)
        assertFalse(lic.Condition12());

        // Invalid test (K_PTS>NUMPOINTS-2)
        parameter = new Parameter();
        parameter.LENGTH1 = 1;
        parameter.LENGTH2 = 2;
        parameter.K_PTS = 3;

        lic = new LIC(parameter, points);
        assertFalse(lic.Condition12());

        // Invalid test (NUMPOINTS<3)
        parameter = new Parameter();
        Datapoints[] points1 = { new Datapoints(0,0),
                new Datapoints(1,1),
        };
        parameter.LENGTH1 = 0;
        parameter.LENGTH2 = 1;
        parameter.K_PTS = 1;

        lic = new LIC(parameter, points1);
        assertFalse(lic.Condition12());



    }

    @Test
    void condition13() {
        Parameter parameter = new Parameter();

        // Manual calculated radius for the following data points
        // Smallest enclosing radius: 0.5
        // Biggest enclosing radius: 1.15
        Datapoints[] points = { new Datapoints(0, 0),
                new Datapoints(0, 0),
                new Datapoints(0, 1),
                new Datapoints(1, 1),
                new Datapoints(2, 2),
        };
        LIC lic = new LIC(parameter, points);

        parameter.RADIUS1 = 1;
        parameter.RADIUS2 = 1;
        // Smallest radius smaller than 1 and biggest bigger than 1
        assertTrue(lic.Condition13());

        parameter.RADIUS1 = 2;
        parameter.RADIUS2 = 1;
        // No radius that can not be contained within a circle with radius 2
        assertFalse(lic.Condition13());
    }

    @Test
    void condition14() {

        Parameter parameter = new Parameter(0,0,0,10.0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1.0);

        Datapoints[] point = { new Datapoints(0, 0) };
        LIC lic = new LIC(parameter, point);

        // less than 5 data-points
        assertFalse(lic.Condition14());

        // First 3 points create triangle bigger than 10
        // Last 3 points create triangle smaller than 1
        Datapoints[] points = { new Datapoints(0, 0),
                                new Datapoints(5, 0),
                                new Datapoints(0, 5),
                                new Datapoints(1, 5),
                                new Datapoints(1, 6),
        };
        lic = new LIC(parameter, points);
        // AREA1 = 10, AREA2 = 1 therefore true
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