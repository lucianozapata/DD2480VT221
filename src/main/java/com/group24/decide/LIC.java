package com.group24.decide;


import static java.lang.Math.*;
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

    public void setPoints(Datapoints[] points) {
        this.points = points;
    }

    public void setParameters(Parameter parameters){
        this.parameters = parameters;
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
        CMV[3] = Condition3();
        CMV[4] = Condition4();
        CMV[7] = Condition7();


        return CMV;
    }

    /**
     *
     * @return Return true if there exists at least one set
     * of two consecutive data points that are a distance greater
     * than the length, LENGTH1, apart. (0 ≤ LENGTH1), else return false.
     */
        public boolean Condition0() {
            // How many datapoints we are going to check.

            for (int index = 0; index < numberPoints-1; index++) {
                // The distance between the two datapoints
                double distance = Utility.calcEuclideanDistance(this.points[index], this.points[index + 1]);

                if ( distance > this.parameters.LENGTH1) {
                    return true;
                }

            }
            return false;
        }

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
    public  boolean Condition5(){
        for(int idx=0; idx < this.numberPoints -1; idx++){
                Datapoints a = this.points[idx];
                Datapoints b = this.points[idx+1];
                double diff = Utility.difference(b,a);
            if(diff < 0){

             return true;
            }
        }
        return false;

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
    }

    /**
     * Check if there exists at least one set of three data points separated by exactly A PTS and B PTS consecutive
     * intervening points, respectively, that cannot be contained within or on a circle of radius RADIUS1
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition8(){
        if (numberPoints < 5) {
            return false;
        }
        if (parameters.A_PTS < 1 || parameters.B_PTS < 1) {
            return false;
        }
        if (parameters.A_PTS + parameters.B_PTS > numberPoints - 3) {
            return false;
        }

        for (int idx = 0; idx < numberPoints - parameters.A_PTS - parameters.B_PTS - 2; idx++) {
            Datapoints a = points[idx];
            Datapoints b = points[idx + parameters.A_PTS + 1];
            Datapoints c = points[idx + parameters.A_PTS + parameters.B_PTS + 2];
            if (Utility.findSmallestCircle(a, b, c) < parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

    /**
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
    }

    /**
     * SubCondition1: a set of 3 data points, seperated by E_PTS and F_PTS points, with area > AREA1
     * SubCondition2 a set of 3 data points, seperated by E_PTS and F_PTS points, with area < AREA2
     *
     * @return Return true if the both SubConditions are met, otherwise return false
     */
    public boolean Condition14(){

        if(this.numberPoints < 5) {return false;}

        boolean biggerArea = false;
        boolean smallerArea = false;
        int indexPoint1, indexPoint2;
        int maxIndex = this.numberPoints - 2 - parameters.E_PTS - parameters.F_PTS;

        for (int i = 0; i < maxIndex; i++) {

            indexPoint1 = i + 1 + parameters.E_PTS;
            indexPoint2 = i + 2 + + parameters.E_PTS + parameters.F_PTS;

            double area = Utility.calcTriangleArea(this.points[i],this.points[indexPoint1], this.points[indexPoint2]);

            if (area > parameters.AREA1) {biggerArea = true;}
            if (area < parameters.AREA2) {smallerArea = true;}

            if (biggerArea && smallerArea) {return true;}
        }
        return false;
    }














}
