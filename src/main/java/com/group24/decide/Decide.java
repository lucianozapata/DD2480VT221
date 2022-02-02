package com.group24.decide;


/**
 * Main class of this software package.
 * Here data points are read in and parameters can be configured.
 * Based on this data, the individual conditions are called and the final result of the program is calculated.
 */
public class Decide {

    static int NUMPOINTS = 5;

    static LIC lic;

    private static boolean[] CMV = new boolean[15];
    private static boolean[][] PUM = new boolean[15][15];
    private static boolean[] FUV = new boolean[15];

    private static Parameter parameters;

    static boolean LAUNCH;

    enum CONNECTORS {
        NOTUSED,
        ORR,
        ANDD
    }

    /**
     * Calculate PUM using CMV and LCM
     * @param CMV Conditions Met Vector
     * @param LCM Logical Connector Matrix
     * @return PUM (Preliminary Unlocking Matrix)
     */
    public boolean[][] calcPUM(boolean[] CMV, CONNECTORS[][] LCM) {
        boolean[][] PUM = new boolean[15][15];
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
        return PUM;
    }

    /**
     * Calculates a Final unlocking vector using the PUM and PUV. 
     * @param PUM Preliminary Unlocking Matrix
     * @param PUV Preliminary Unlocking Vector
     * @return Returns the FUV (Final unlocking vector) with boolean values.
     */
    public boolean[] calcFUV(boolean[][] PUM, boolean[] PUV){
        boolean[] FUV = new boolean[15];
        // Goes through the entire boolean array
        for (int index = 0; index < FUV.length; index++) {
            // If the PUV[index] is true then PUM[index][i] has to be true for all i in order for FUV[index] to be true.
            // All other cases makes FUV[index] false.
            if((PUV[index] && checkPUM(PUM, index)) || !PUV[index]){
                FUV[index] = true;
            }
        }
        return FUV;
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
     * Entry point to run the software as standalone program. Uses predefined data points and parameters.
     * @param args is not evaluated
     */
    public static void main(String[] args){

        Parameter parameters = new Parameter(1,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0);

        Datapoints[] Points = new Datapoints[NUMPOINTS];
        Points[0] = new Datapoints(1, 2); // Input the data points
        Points[1] = new Datapoints(1,2);
        lic = new LIC(parameters, Points);

        // Input all values for LCM.
        CONNECTORS[][] LCM = {
                {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED},
                {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED}
        };

        CMV = lic.runLICConditions(CMV.length); // Run the LIC conditions

        LAUNCH = true;
        for(int i=0;i<FUV.length;i++){
            if( !FUV[i] ){
                LAUNCH = false;
                break;
            }
        }
    }
}