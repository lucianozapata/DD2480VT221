package com.group24.decide;

/**
 * Class to store all given parameters.
 */
public class Parameter {
    /**
     * All predefined parameters.
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
     *Initializing all the parameters with given values.
     * @param LENGTH1 length1
     * @param RADIUS1 radius1
     * @param EPSILON epsilon
     * @param AREA1 area1
     * @param Q_PTS q_pts
     * @param QUADS quads
     * @param DIST dist
     * @param N_PTS n_pts
     * @param K_PTS k_pts
     * @param A_PTS a_pts
     * @param B_PTS b_pts
     * @param C_PTS c_pts
     * @param D_PTS d_pts
     * @param E_PTS e_pts
     * @param F_PTS f_pts
     * @param G_PTS g_pts
     * @param LENGTH2 length2
     * @param RADIUS2 radius2
     * @param AREA2 area2
     */
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

    /**
     *Initializing all the parameters with 0.
     */
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