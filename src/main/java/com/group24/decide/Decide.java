package com.group24.decide;


/**
 * Add description for javaDoc
 *
 * @author Pontus
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
     *
     * @param args
     */
    public static void main(String[] args){

        Parameter parameters = new Parameter(0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0);

        Datapoints[] Points = new Datapoints[NUMPOINTS];
        Points[0] = new Datapoints(1, 2); // Input the data points

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
