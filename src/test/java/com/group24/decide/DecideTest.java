package com.group24.decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * A class for testing the Decide class.
 *
 * @author
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
                LCM[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }
        LCM[0][0] = Decide.CONNECTORS.ANDD;
        LCM[0][1] = Decide.CONNECTORS.ORR;
        LCM[1][0] = Decide.CONNECTORS.ANDD;
        LCM[1][1] = Decide.CONNECTORS.ORR;

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
}