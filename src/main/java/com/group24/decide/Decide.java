package com.group24.decide;


/**
 * Class to process data points based on given configuration.
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

    /**
     * Default constructor
     */
    public Decide(){}

    /**
     * Constructor to create Decide objects.
     * @param points input points used for calculating the response.
     * @param parameters configuration of the LICs.
     * @param LCM Logical connector matrix.
     * @param PUV primary unlocking vector.
     */
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
        if (numPoints < 2 || numPoints > 100) {
            return;
        }
        LIC lic = new LIC(parameters, points);
        CMV = lic.runLICConditions(15);
    }

    /**
     * Calculate PUM using CMV and LCM
     */
    public void calcPUM() {
        if (CMV == null || LCM == null) {
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
     * Results are stored in the FUV variable.
     */
    public void calcFUV(){
        if (PUM == null || PUV == null) {
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
     * Calculate the CMV and PUM, and combine results into the FUV.
     * @return True if the entire vector FUV contains true values. Else false.
     */
    public boolean decide() {
        calcCMV();
        calcPUM();
        calcFUV();
        if (FUV == null) {
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