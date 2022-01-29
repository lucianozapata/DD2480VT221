package com.group24.decide;

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
        for (int idx = 0; idx < this.numberPoints; idx++) {
            Datapoints a = this.points[idx];
            Datapoints b = this.points[idx+1];
            Datapoints c = this.points[idx+2];

            double triangleArea = Utility.calcTriangleArea(a,b,c);
            if (triangleArea > parameters.AREA1) return true;
        }
        return false;
    }

    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition4(){
        return true;
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
    public static boolean Condition7(){
        return true;
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
