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
        CMV[1] = Condition1();
        CMV[2] = Condition2();
        CMV[3] = Condition3();
        CMV[4] = Condition4();
        CMV[5] = Condition5();
        CMV[6] = Condition6();
        CMV[7] = Condition7();
        CMV[8] = Condition8();
        CMV[9] = Condition9();

        CMV[10] = Condition10();
        CMV[11] = Condition11();
        CMV[12] = Condition12();
        CMV[13] = Condition13();
        CMV[14] = Condition14();

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
    public boolean Condition5(){
        for(int idx=0; idx < this.numberPoints -1; idx++){
            Datapoints a = this.points[idx];
            Datapoints b = this.points[idx+1];
            double diff = Utility.difference(b,a);
            if(diff < 0){

             return true;
            }
        }
        return false;

    }


    /**
     * Checks if there exists one point belonging to a set of N_PTS
     * consecutive points, such that the distance between the line made
     * up by the first and last point of this set and one point in the set
     * is greater than DIST. If the the first and last point is the same
     * then the distance is simply from this coincident point. It is assumed
     * that the line joining the first and last point is infinite rather
     * the finite.
     *
     *
     * @return Return true if the condition is met, otherwise it
     * returns false.
     */
    public boolean Condition6(){
        if(numberPoints<3 || parameters.DIST<0 || points.length>100){return false;}

        for(int i=0;i<numberPoints-parameters.N_PTS;i++){
            for(int j=i+1; j<parameters.N_PTS; j++){
                double distance = Utility.lineDistPoints(points[i], points[i+ parameters.N_PTS], points[j]);
                if (distance>parameters.DIST){return true;}
            }
        }

        return false;
    }



    /**
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
