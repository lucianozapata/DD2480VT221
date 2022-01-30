package com.group24.decide;

/**
 * @author
 */
public class Parameter {
    /**
     * All parameters available.
     */
    double LENGTH1;
    double RADIUS1;
    double EPSILON;
    double AREA1;
    int Q_PTS;
    int QUADS;
    double DIST;
    int N_PTS;
    int K_PTS;
    int A_PTS;
    int B_PTS;
    int C_PTS;
    int D_PTS;
    int E_PTS;
    int F_PTS;
    int G_PTS;
    double LENGTH2;
    double RADIUS2;
    double AREA2;


    /**
     *
     */
    // Initializing all the parameters to ZERO when creating the PARAMETER-object.
    public Parameter(double LENGTH1, double RADIUS1, double EPSILON, double AREA1, int Q_PTS, int QUADS, double DIST,
                     int N_PTS, int K_PTS, int A_PTS, int B_PTS, int C_PTS, int D_PTS, int E_PTS, int F_PTS,
                     int G_PTS, double LENGTH2, double RADIUS2, double AREA2
    ) {
        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.G_PTS = G_PTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }

    public Parameter() {
        this.LENGTH1 = 0;
        this.RADIUS1 = 0;
        this.EPSILON = 0;
        this.AREA1 = 0;
        this.Q_PTS = 0;
        this.QUADS = 0;
        this.DIST = 0;
        this.N_PTS = 0;
        this.K_PTS = 0;
        this.A_PTS = 0;
        this.B_PTS = 0;
        this.C_PTS = 0;
        this.D_PTS = 0;
        this.E_PTS = 0;
        this.F_PTS = 0;
        this.G_PTS = 0;
        this.LENGTH2 = 0;
        this.RADIUS2 = 0;
        this.AREA2 = 0;
    }
}