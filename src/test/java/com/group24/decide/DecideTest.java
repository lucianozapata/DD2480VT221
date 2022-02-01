package com.group24.decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Decide class.
 *
 * @author
 */
public class DecideTest {
    @Test
    @DisplayName("Calculate PUM")
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

        boolean[][] PUM = decide.calcPUM(CMV, LCM);

        assertTrue(PUM[0][0]);
        assertTrue(PUM[0][1]);
        assertFalse(PUM[1][0]);
        assertFalse(PUM[1][1]);
        assertTrue(PUM[1][2]);
    }
}
