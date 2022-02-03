package com.group24.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Decide class.
 */
public class DecideTest {
   
    
    @Test
    void calcPUM() {
        // Creating a CMV where all values but CMV[0] are false 
        Decide decide = new Decide();
        boolean[] CMV = new boolean[15];
        CMV[0] = true;

        // Creating a LCM where all values are NOTUSED
        Decide.CONNECTORS[][] LCM = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                LCM[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }


        // Updating the CMV and LCM in the decide class
        decide.CMV = CMV;
        decide.LCM = LCM;
        // Evaluating the PUM
        decide.calcPUM();
        boolean[][] PUM = decide.PUM;

        // Positive test because CMV[0] is true and LCM[0][0] is ANDD
        LCM[0][0] = Decide.CONNECTORS.ANDD;
        decide.LCM = LCM;
        decide.calcPUM();
        PUM = decide.PUM;
        assertTrue(PUM[0][0]);

        // Positive test because CMV[0] is true and LCM[0][1] is ORR, PUM[0][1] is true.
        LCM[0][1] = Decide.CONNECTORS.ORR;
        decide.LCM = LCM;
        decide.calcPUM();
        PUM = decide.PUM;
        assertTrue(PUM[0][1]);

        // Negative test because CMV[1] is false and CMV[0] is true, but LCM[1][0] is ANDD
        LCM[1][0] = Decide.CONNECTORS.ANDD;
        decide.LCM = LCM;
        decide.calcPUM();
        PUM = decide.PUM;
        assertFalse(PUM[1][0]);

        // Negative test because CMV[1] is false and LCM[1][1] is ORR
        LCM[1][1] = Decide.CONNECTORS.ORR;
        decide.LCM = LCM;
        decide.calcPUM();
        PUM = decide.PUM;
        assertFalse(PUM[1][1]);

        // Positive test because CMV[1] and CMV[2] are false, but LCM[i][j] are NOTUSED
        LCM[1][2] = Decide.CONNECTORS.NOTUSED;
        decide.LCM = LCM;
        decide.calcPUM();
        PUM = decide.PUM;
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
}