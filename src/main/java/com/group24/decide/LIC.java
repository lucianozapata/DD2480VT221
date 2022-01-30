package com.group24.decide;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class for the LIC conditions
 *
 * @author
 *
 */
public class LIC {

    Parameter parameters;
    Datapoints[] points;
    int numberPoints;

    /**
     *
     * @param parameters Contains the inputs for LIC.
     */
    public LIC(Parameter parameters, Datapoints[] points){
        this.parameters = parameters;
        this.points = points;
        this.numberPoints = points.length;
    }

    /**
     *
     * @param size Definiens how many conditions there
     *             are for LIC.
     *
     * @return Returns the Conditions Met Vector,
     * which contains boolean values corresponding
     * to each LIC condition.
     *
     */
    public boolean[] runLICConditions(int size){
        boolean[] CMV = new boolean[size];
        CMV[0] = Condition0();
        CMV[3] = Condition3();
        CMV[4] = Condition4();
        CMV[7] = Condition7();

        return CMV;
    }

    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition0(){
        return true;
    }


    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition1(){
        return true;
    }
    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition2(){
        return true;
    }

    /**
     * Check if there exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1 (parameters.AREA)
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition3(){
        for (int idx = 0; idx < this.numberPoints - 2; idx++) {
            Datapoints a = this.points[idx];
            Datapoints b = this.points[idx+1];
            Datapoints c = this.points[idx+2];

            double triangleArea = Utility.calcTriangleArea(a,b,c);
            if (triangleArea > parameters.AREA1) return true;
        }
        return false;
    }

    /**
     *Check if there exists one set of Q_PTS consecutive data points that lie in more than QUADS quadrants
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition4(){
        if (parameters.Q_PTS < 2 || parameters.Q_PTS > numberPoints) {
            return false;
        }
        if (parameters.QUADS < 1 || parameters.QUADS > 3) {
            return false;
        }
        Queue<Integer> pointsQueue = new LinkedList<>();
        for (int idx = 0; idx < this.numberPoints; idx++) {
            pointsQueue.add(Utility.chooseQuadrant(this.points[idx]));
            if (pointsQueue.size() > parameters.Q_PTS) {
                pointsQueue.poll();
            }
            if (pointsQueue.stream().distinct().count() > parameters.QUADS) {
                return true;
            }
        }
        return false;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition5(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition6(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition7(){
        if (numberPoints < 3) {
            return false;
        }
        if (parameters.K_PTS < 1 || parameters.K_PTS > numberPoints - 2) {
            return false;
        }
        for (int idx = 0; idx < numberPoints - parameters.K_PTS - 1; idx++) {
            double distance = Utility.calcEuclideanDistance(points[idx], points[idx + parameters.K_PTS + 1]);
            if (distance > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition8(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition9(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition10(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition11(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition12(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition13(){
        return true;
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition14(){
        return true;
    }














}
