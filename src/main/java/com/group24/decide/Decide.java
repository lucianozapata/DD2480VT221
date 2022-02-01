package com.group24.decide;


/**
 * Add description for javaDoc
 *
 * @author Pontus
 */
public class Decide {

    int numPoints;
    Datapoints[] points;
    Parameter parameters;
    boolean[] PUV;
    enum CONNECTORS {
        NOTUSED,
        ORR,
        ANDD
    }
    CONNECTORS[][] LCM;

    boolean[] CMV;
    boolean[][] PUM;
    boolean[] FUV;

    public Decide(){
    }

    public Decide(Datapoints[] points, Parameter parameters, CONNECTORS[][] LCM, boolean[] PUV) {
        this.points = points;
        this.numPoints = points.length;
        this.parameters = parameters;
        this.LCM = LCM;
        this.PUV = PUV;
    }

    /**
     * Calculate CMV using points and LIC conditions
     */
    public void calcCMV() {
        LIC lic = new LIC(parameters, points);
        CMV = lic.runLICConditions(15);
    }

    /**
     * Calculate PUM using CMV and LCM
     */
    public void calcPUM() {
        if (CMV.length == 0 || LCM.length == 0) {
            return;
        }
        PUM = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                CONNECTORS connector = LCM[i][j];
                switch (connector) {
                    case ORR :
                        PUM[i][j] = CMV[i] || CMV[j];
                        break;
                    case ANDD :
                        PUM[i][j] = CMV[i] && CMV[j];
                        break;
                    case NOTUSED :
                        PUM[i][j] = true;
                        break;
                }
            }
        }
    }

    /**
     * Calculates a Final unlocking vector using the PUM and PUV.
     */
    public void calcFUV(){
        if (PUM.length == 0 || PUV.length == 0) {
            return;
        }
        FUV = new boolean[15];
        // Goes through the entire boolean array
        for (int index = 0; index < FUV.length; index++) {
            // If the PUV[index] is true then PUM[index][i] has to be true for all i in order for FUV[index] to be true.
            // All other cases makes FUV[index] false.
            if((PUV[index] && checkPUM(PUM, index)) || !PUV[index]){
                FUV[index] = true;
            }
        }
    }

    /**
     * Check if the entire vector PUM[index][i] is true for all i's. Returns true if it is.
     * @param PUM Preliminary Unlocking Matrix
     * @param index The fixed row index.
     * @return True if the entire vector contains true values. Else false.
     */
    private boolean checkPUM(boolean[][] PUM, int index) {
        for (int i = 0; i < PUM.length; i++) {
            if(!PUM[index][i]){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return True if the entire vector FUV contains true values. Else false.
     */

    public boolean launch() {
        calcCMV();
        calcPUM();
        calcFUV();
        if (FUV.length == 0) {
            return false;
        }
        for (boolean b : FUV) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}