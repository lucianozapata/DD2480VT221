package com.group24.decide;

import org.junit.jupiter.api.Test;

import static com.group24.decide.Decide.CONNECTORS.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Decide class.
<<<<<<< HEAD
 *
 *
=======
>>>>>>> main
 */
public class DecideTest {
   
    
    @Test
    void calcPUM() {
        Decide decide = new Decide();

        boolean[] CMV = new boolean[15];
        for (int i = 0; i < 15; i++) {
            CMV[i] = false;
        }
        CMV[0] = true;

        Decide.CONNECTORS[][] LCM = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                LCM[i][j] = NOTUSED;
            }
        }
        LCM[0][0] = ANDD;
        LCM[0][1] = ORR;
        LCM[1][0] = ANDD;
        LCM[1][1] = ORR;

        decide.CMV = CMV;
        decide.LCM = LCM;
        decide.calcPUM();
        boolean[][] PUM = decide.PUM;

        assertTrue(PUM[0][0]);
        assertTrue(PUM[0][1]);
        assertFalse(PUM[1][0]);
        assertFalse(PUM[1][1]);
        assertTrue(PUM[1][2]);
    }

    @Test
    void calcFUV() {
        Decide decide = new Decide();
        boolean[] trueArray = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        // Positive test where the entire PUV has false values.
        boolean[] PUV = new boolean[15];
        boolean[][] PUM = new boolean[15][15];
        decide.PUV = PUV;
        decide.PUM = PUM;
        decide.calcFUV();
        boolean[] FUV = decide.FUV;
        
        assertArrayEquals(trueArray, FUV);

        // Negative test where the entire PUV is true but PUM false
        boolean[] newPUV = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        decide.PUV = newPUV;
        decide.calcFUV();
        FUV = decide.FUV;
        assertNotEquals(trueArray, FUV);

        // Positive test where entire PUV is false but PUV[4] is true. Entire PUM[4][i] is true for all i
        boolean[] secondPUV =  new boolean[15];
        secondPUV[4] = true;
        // Set all values for PUM[4][i] to true.
        for (int i = 0; i < PUM.length; i++) {
            PUM[4][i] = true;
        }
        decide.PUV = secondPUV;
        decide.calcFUV();
        FUV = decide.FUV;
        assertArrayEquals(trueArray, FUV);


        //Negative test where entire PUV is true but PUM[13][14] false.
        boolean[] thirdPUV = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        boolean[][] newPUM = new boolean[15][15];
        for (int i = 0; i < newPUM.length; i++) {
            for (int j = 0; j < newPUM.length; j++) {
                newPUM[i][j] = true;
            }
        }
        decide.PUV = thirdPUV;
        decide.PUM = newPUM;
        decide.calcFUV();
        // FUV should be true for all indexes since PUM is true for all rows and columns.
        FUV = decide.FUV;
        assertArrayEquals(trueArray, FUV);
        // Changed one value to false, should not be equal anymore.
        newPUM[13][14] = false;
        decide.calcFUV();
        FUV = decide.FUV;
        assertNotEquals(trueArray, FUV);

    }


    @Test
    /*
        The expected return value of running decide() should be false.
        All the calculations to reach this results has their own assertions.
     */
    void DecideExpectFalse(){
        Decide.CONNECTORS[][] LCM = {
                { ANDD, ORR, ORR, NOTUSED, NOTUSED, ORR, NOTUSED, NOTUSED, ANDD, ORR, NOTUSED, NOTUSED, NOTUSED, ORR, ANDD },
                { ORR, ANDD, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD, ORR, ORR, NOTUSED, ORR, ORR },
                { ORR, ANDD, ANDD, NOTUSED, NOTUSED, NOTUSED, ORR, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD },
                { NOTUSED, ORR, NOTUSED, ANDD, ANDD, ANDD, ANDD, ORR, ORR, ORR, ANDD, NOTUSED, NOTUSED, ORR, ORR },
                { NOTUSED, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, ANDD, NOTUSED, ORR, NOTUSED, ORR, ORR, ORR, ORR, ORR },
                { ORR, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD },
                { NOTUSED, ORR, ORR, ANDD, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, ORR, ORR, ANDD, NOTUSED, ANDD, NOTUSED },
                { NOTUSED, ANDD, ANDD, ORR, NOTUSED, ANDD, ANDD, ANDD, ORR, ANDD, ANDD, ORR, ORR, ANDD, NOTUSED },
                { ANDD, NOTUSED, ORR, ORR, ORR, ORR, NOTUSED, ORR, ANDD, NOTUSED, ANDD, ORR, ANDD, ANDD, ANDD },
                { ORR, ANDD, ANDD, ORR, NOTUSED, ANDD, ORR, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, NOTUSED },
                { NOTUSED, ORR, ANDD, ANDD, ORR, ANDD, ORR, ANDD, ANDD, NOTUSED, ANDD, NOTUSED, ORR, ORR, ANDD },
                { NOTUSED, ORR, ORR, NOTUSED, ORR, ORR, ANDD, ORR, ORR, ANDD, NOTUSED, ANDD, ORR, ANDD, ORR },
                { NOTUSED, NOTUSED, ANDD, NOTUSED, ORR, ANDD, NOTUSED, ORR, ANDD, ANDD, ORR, ORR, ANDD, NOTUSED, ANDD },
                { ORR, ORR, NOTUSED, ORR, ORR, NOTUSED, ANDD, ANDD, ANDD, NOTUSED, ORR, ANDD, NOTUSED, ANDD, NOTUSED },
                { ANDD, ORR, ANDD, ORR, ORR, ANDD, NOTUSED, NOTUSED, ANDD, NOTUSED, ANDD, ORR, ANDD, NOTUSED, ANDD },
        };
        // Same input as checkCMV()
        Parameter parameters = new Parameter(1,1,0,4,4,3,2,3,1,1,1,1,1,1,1,2,1,2,1);
        Datapoints[] testDataPoints = new Datapoints[5];

        testDataPoints[0] = new Datapoints(0,0);
        testDataPoints[1] = new Datapoints(2,0);
        testDataPoints[2] = new Datapoints(5,0);
        testDataPoints[3] = new Datapoints(0,5);
        testDataPoints[4] = new Datapoints(3,1);

        boolean[] PUV = new boolean[15];
        for(int i=0; i<PUV.length;i++){
            PUV[i] = true;
        }

        Decide dec = new Decide(testDataPoints, parameters, LCM, PUV);

        boolean[] expectedCMV = {
                true, true, true, true, false,
                true, true, true, false, true,
                false, false, false, false, false
        };

        dec.calcCMV();;

        boolean[] CMV = dec.CMV;

        // Assert that
        assertArrayEquals(expectedCMV, CMV);

        boolean[][] ExpectedPUM = {
                /* 0    1      2      3      4      5      6      7     8       9      10     11     12     13    14      */
                {true,  true,  true,  true,  true,  true,  true,  true, false,  true,  true,  true,  true,  true, false}, // 0
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true}, // 1
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true, false,  true, false}, // 2
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true, false,  true,  true,  true,  true}, // 3
                {true,  false, true, false, false,  true, false,  true, false,  true, false, false, false, false, false}, // 4
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true, false,  true, false}, // 5
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true,  true, false,  true, false,  true}, // 6
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true,  true, false,  true}, // 7
                {false, true,  true,  true, false,  true,  true,  true, false,  true, false, false, false, false, false}, // 8
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false, false,  true,  true}, // 9
                {true,  true, false, false, false, false,  true, false, false,  true, false,  true, false, false, false}, // 10
                {true,  true,  true,  true, false,  true, false,  true, false, false,  true, false, false, false, false}, // 11
                {true,  true, false,  true, false, false,  true,  true, false, false, false, false, false,  true, false}, // 12
                {true,  true,  true,  true, false,  true, false, false, false,  true, false, false,  true, false,  true}, // 13
                {false, true, false,  true, false, false,  true,  true, false,  true, false, false, false,  true, false} // 14
        };

        dec.calcPUM();
        boolean[][] PUM = dec.PUM;

        assertArrayEquals(ExpectedPUM, PUM);

        boolean[] ExpectedFUV = {
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false
        };

        dec.calcFUV();
        boolean[] FUV = dec.FUV;

        assertArrayEquals(ExpectedFUV, FUV);

        // Since not all values in FUV are true (in fact none) the expected value should be false.
        assertFalse(dec.decide());

    }

    @Test
    /*
        The expected return value of running decide() should be true.
        All the calculations to reach this results has their own assertions.
     */
    void DecideExpectTrue(){
        Decide.CONNECTORS[][] LCM = {
                { ANDD, ORR, ORR, NOTUSED, NOTUSED, ORR, NOTUSED, NOTUSED, ANDD, ORR, NOTUSED, NOTUSED, NOTUSED, ORR, ANDD },
                { ORR, ANDD, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD, ORR, ORR, NOTUSED, ORR, ORR },
                { ORR, ANDD, ANDD, NOTUSED, NOTUSED, NOTUSED, ORR, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD },
                { NOTUSED, ORR, NOTUSED, ANDD, ANDD, ANDD, ANDD, ORR, ORR, ORR, ANDD, NOTUSED, NOTUSED, ORR, ORR },
                { NOTUSED, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, ANDD, NOTUSED, ORR, NOTUSED, ORR, ORR, ORR, ORR, ORR },
                { ORR, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, ORR, ANDD, ANDD, ORR, ANDD, NOTUSED, ANDD },
                { NOTUSED, ORR, ORR, ANDD, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, ORR, ORR, ANDD, NOTUSED, ANDD, NOTUSED },
                { NOTUSED, ANDD, ANDD, ORR, NOTUSED, ANDD, ANDD, ANDD, ORR, ANDD, ANDD, ORR, ORR, ANDD, NOTUSED },
                { ANDD, NOTUSED, ORR, ORR, ORR, ORR, NOTUSED, ORR, ANDD, NOTUSED, ANDD, ORR, ANDD, ANDD, ANDD },
                { ORR, ANDD, ANDD, ORR, NOTUSED, ANDD, ORR, ANDD, NOTUSED, ANDD, NOTUSED, ANDD, ANDD, NOTUSED, NOTUSED },
                { NOTUSED, ORR, ANDD, ANDD, ORR, ANDD, ORR, ANDD, ANDD, NOTUSED, ANDD, NOTUSED, ORR, ORR, ANDD },
                { NOTUSED, ORR, ORR, NOTUSED, ORR, ORR, ANDD, ORR, ORR, ANDD, NOTUSED, ANDD, ORR, ANDD, ORR },
                { NOTUSED, NOTUSED, ANDD, NOTUSED, ORR, ANDD, NOTUSED, ORR, ANDD, ANDD, ORR, ORR, ANDD, NOTUSED, ANDD },
                { ORR, ORR, NOTUSED, ORR, ORR, NOTUSED, ANDD, ANDD, ANDD, NOTUSED, ORR, ANDD, NOTUSED, ANDD, NOTUSED },
                { ANDD, ORR, ANDD, ORR, ORR, ANDD, NOTUSED, NOTUSED, ANDD, NOTUSED, ANDD, ORR, ANDD, NOTUSED, ANDD },
        };
        // Same input as checkCMV()
        Parameter parameters = new Parameter(1,1,0,4,4,3,2,3,1,1,1,1,1,1,1,2,1,2,1);
        Datapoints[] testDataPoints = new Datapoints[5];

        testDataPoints[0] = new Datapoints(0,0);
        testDataPoints[1] = new Datapoints(2,0);
        testDataPoints[2] = new Datapoints(5,0);
        testDataPoints[3] = new Datapoints(0,5);
        testDataPoints[4] = new Datapoints(3,1);

        boolean[] PUV = new boolean[15];
        for(int i=0; i<PUV.length;i++){
            PUV[i] = false;
        }

        Decide dec = new Decide(testDataPoints, parameters, LCM, PUV);

        boolean[] expectedCMV = {
                true, true, true, true, false,
                true, true, true, false, true,
                false, false, false, false, false
        };

        dec.calcCMV();;

        boolean[] CMV = dec.CMV;

        assertArrayEquals(expectedCMV, CMV);

        boolean[][] ExpectedPUM = {
                /* 0    1      2      3      4      5      6      7     8       9      10     11     12     13    14      */
                {true,  true,  true,  true,  true,  true,  true,  true, false,  true,  true,  true,  true,  true, false}, // 0
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true}, // 1
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true, false,  true, false}, // 2
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true, false,  true,  true,  true,  true}, // 3
                {true,  false, true, false, false,  true, false,  true, false,  true, false, false, false, false, false}, // 4
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true, false,  true, false}, // 5
                {true,  true,  true,  true, false,  true,  true,  true,  true,  true,  true, false,  true, false,  true}, // 6
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false,  true,  true, false,  true}, // 7
                {false, true,  true,  true, false,  true,  true,  true, false,  true, false, false, false, false, false}, // 8
                {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true, false, false,  true,  true}, // 9
                {true,  true, false, false, false, false,  true, false, false,  true, false,  true, false, false, false}, // 10
                {true,  true,  true,  true, false,  true, false,  true, false, false,  true, false, false, false, false}, // 11
                {true,  true, false,  true, false, false,  true,  true, false, false, false, false, false,  true, false}, // 12
                {true,  true,  true,  true, false,  true, false, false, false,  true, false, false,  true, false,  true}, // 13
                {false, true, false,  true, false, false,  true,  true, false,  true, false, false, false,  true, false} // 14
        };

        dec.calcPUM();
        boolean[][] PUM = dec.PUM;

        assertArrayEquals(ExpectedPUM, PUM);

        boolean[] ExpectedFUV = {
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true
        };

        dec.calcFUV();
        boolean[] FUV = dec.FUV;

        assertArrayEquals(ExpectedFUV, FUV);

        // Should be true since all elements in FUV is true
        assertTrue(dec.decide());
    }

    @Test
    /*
        The expected return value of running decide() should be false since the input is invalid.
        All the calculations to reach this results has their own assertions.
     */
    void DecideExpectInvalid(){
        Decide.CONNECTORS[][] LCM = {
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
                { NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED, NOTUSED },
        };
        // Same input as checkCMV()
        Parameter parameters = new Parameter(1,1,0,4,4,3,2,3,1,1,1,1,1,1,1,2,1,2,1);
        Datapoints[] testDataPoints = new Datapoints[101];

        for(int i=0;i<testDataPoints.length;i++){
            testDataPoints[i] = new Datapoints(0,0);
        }

        boolean[] PUV = new boolean[15];
        for(int i=0; i<PUV.length;i++){
            PUV[i] = false;
        }

        Decide dec = new Decide(testDataPoints, parameters, LCM, PUV);

        // Should be false since NUMBERPOINTS>100
        assertFalse(dec.decide());

        Datapoints[] points = new Datapoints[]{
                new Datapoints(0,0)
        };

        Decide d = new Decide(points, parameters, LCM, PUV);

        // Should be false since NUMBERPOINTS<2
        assertFalse(d.decide());
    }



}