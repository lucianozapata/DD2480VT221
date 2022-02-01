package com.group24.decide;

/**
 * Add description for javaDoc
 *
 * @author
 */
public class Launcher {
    public static void main(String[] args){

        Parameter parameters = new Parameter(1,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0);

        // Create datapoints
        Datapoints[] points = {
                new Datapoints(0, 1),
                new Datapoints(-2, 0),
                new Datapoints(-1, 5),
                new Datapoints(0, 2),
                new Datapoints(-1, -1),
                new Datapoints(2, 0)
        };

        // Input all values for LCM.
        Decide.CONNECTORS[][] LCM = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                LCM[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }

        boolean[] PUV = new boolean[15];

        Decide decide = new Decide(points, parameters, LCM, PUV);
        System.out.println(decide.launch());
    }
}
