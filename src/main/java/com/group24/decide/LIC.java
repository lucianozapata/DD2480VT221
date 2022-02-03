package com.group24.decide;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class containing all the Launch Interceptor Conditions (LIC) conditions.
 */
public class LIC {

    Parameter parameters;
    Datapoints[] points;
    int numberPoints;

    /**
     * Constructor creating LIC object.
     * @param parameters configuration of the LICs.
     * @param points input points used for calculating the response.
     */
    public LIC(Parameter parameters, Datapoints[] points){
        this.parameters = parameters;
        this.points = points;
        this.numberPoints = points.length;
    }

    /**
     * Run all LICs on the given data points and save results the Conditions Met Vector.
     * @param size Defines how many conditions there are for LIC.
     * @return Returns the Conditions Met Vector, boolean values corresponding to each LIC condition.
     */
    public boolean[] runLICConditions(int size){
        boolean[] CMV = new boolean[size];
        CMV[0] = Condition0();
        CMV[1] = Condition1();
        CMV[3] = Condition3();
        CMV[4] = Condition4();
        CMV[5] = Condition5();
        CMV[6] = Condition6();
        CMV[7] = Condition7();
        CMV[8] = Condition8();
        CMV[11] = Condition11();
        CMV[12] = Condition12();
        CMV[13] = Condition13();
        CMV[14] = Condition14();

        return CMV;
    }

    /**
     * Return true if there exists at least one set
     * of two consecutive data points that are a distance greater
     * than the length, LENGTH1, apart. (0 ≤ LENGTH1), else return false.
     * @return Return true if the condition is met, other
     * returns false.
     */
        public boolean Condition0() {

            // If the length in a negative number.
            if(parameters.LENGTH1 < 0){
                return false;
            }
            // If the number of points is less than 2, we can't have two consecutive points.
            if(numberPoints < 2) {
                return false;
            }
            for (int index = 0; index < numberPoints-1; index++) {
                // The distance between the two data points
                double distance = Utility.calcEuclideanDistance(this.points[index], this.points[index + 1]);

                if ( distance > this.parameters.LENGTH1) {
                    return true;
                }

            }
            return false;
        }
     /**
     * There exists at least one set of three consecutive data points that cannot all be contained
     * within or on a circle of radius RADIUS1. (0 ≤ RADIUS1)
     * @return Return true if the condition is met, other
     * returns false.
     */
        public boolean Condition1(){
            if(parameters.RADIUS1 < 0){
                return false;
            }
            if(numberPoints < 3){
                return false;
            }

            for(int idx = 0; idx < numberPoints - 2; idx++){
               double radius = Utility.calcMinEnclosingRadius(points[idx], points[idx+1], points[idx+2]);
               if(radius > parameters.RADIUS1){
                   return true;
               }
            }
        return false;
    }
    /**
     *
     * There exists at least one set of three consecutive data points which form an angle such that:
     * angle &lt;  (PI − EPSILON) or angle  &gt; (PI + EPSILON) The second of the three consecutive points is always the vertex of the angle.
     * If either the first point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
     * is not satisfied by those three points. (0 &lt; EPSILON &lt; PI)
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition2(){

        // Input criteria for the method.
        if(this.parameters.EPSILON < 0 || this.parameters.EPSILON >= Math.PI || this.points.length < 3){
            return false;
        }
        for (int i = 0; i < points.length-2; i++) {
            Datapoints points1 = this.points[i];
            Datapoints vertexPoints = this.points[i+1];
            Datapoints points3 = this.points[i+2];
            
            // If datapoint 1 or 2 coincides with vertex, the angle is considered undefined and we skip this for-loop iteration.
            if(points1.x == vertexPoints.x && points1.y == vertexPoints.y || points3.x == vertexPoints.x && points3.y == vertexPoints.y){
                continue;
            }
        
            double angle = Utility.calculateAngle(points1, vertexPoints, points3);

            if(angle < Math.PI- parameters.EPSILON || angle > Math.PI + parameters.EPSILON){
                return true;
            }
        }

        return false;
    }

    /**
     * Check if there exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1 (parameters.AREA)
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
    }

    /**
     * There exists at least one set of two consecutive data points, that the difference
     * in the x-axis is &lt; 0.
     * @return Return true if the condition is met, other
     * returns false.
     */
    public boolean Condition5(){
        for(int idx=0; idx < this.numberPoints -1; idx++){
            Datapoints a = this.points[idx];
            Datapoints b = this.points[idx+1];
            double diff = Utility.differenceX(b,a);
            if(diff < 0){

             return true;
            }
        }
        return false;

    }


    /**
     * There exists one point belonging to a set of N_PTS
     * consecutive points, such that the distance between the line made
     * up by the first and last point of this set and one point in the set
     * is greater than DIST. If the first and last point is the same
     * then the distance is simply from this coincident point. It is assumed
     * that the line joining the first and last point is infinite rather
     * the finite.
     * @return Return true if the condition is met, otherwise it
     * returns false.
     */
    public boolean Condition6(){

        //3 ≤ N PTS ≤ NUMPOINTS
        if(numberPoints<3 || parameters.DIST<0 || !( (3<=parameters.N_PTS) && (parameters.N_PTS<=numberPoints)) ){return false;}

        for(int i=0;i<=numberPoints-parameters.N_PTS;i++){
            for(int j=i; j<i + parameters.N_PTS; j++){
                double distance = Utility.lineDistPoints(points[i], points[i+ parameters.N_PTS-1], points[j]);
                if (distance>parameters.DIST){return true;}
            }
        }

        return false;
    }



    /**
     * There exists at least one set of two data points seperated by exactly K_PTS consecutive intervening points
     * that are a distance greater than the length, LENGTH1, apart.
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
            if (Utility.calcMinEnclosingRadius(a, b, c) < parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

    /**
      * There exists at least one set of three data points separated by exactly C_PTS and D_PTS
      * consecutive intervening points, respectively, that form an angle such that:
      * angle &lt;  (PI−EPSILON) or angle &gt; (PI+EPSILON)
      * The second point of the set of three points is always the vertex of the angle. If either the first
      * point or the last point (or both) coincide with the vertex, the angle is undefined and the LIC
      * is not satisfied by those three points. When NUMPOINTS  &lt; 5, the condition is not met.
     * @return Return true if the condition is met, other
     * returns false.
     */
    
    public  boolean Condition9(){
        //There exists three points seperated by C_PTS and D_PTS apart which have PI - EPSILON &lt; angle and &gt;  PI + EPSILON.
        if(this.numberPoints < 5){
            return false;
        }

        if(parameters.C_PTS <1 || parameters.D_PTS < 1){
            return false;
        }

        if(parameters.C_PTS + parameters.D_PTS > this.numberPoints - 3){
            return false;
        }

        for(int idx=0; idx < this.numberPoints - parameters.C_PTS -parameters.D_PTS-2; idx++){
            Datapoints a1 = this.points[idx];
            Datapoints a2 = this.points[idx + parameters.C_PTS +1];
            Datapoints a3 = this.points[idx + parameters.C_PTS+ parameters.D_PTS+2];
            double angle = (Math.toDegrees((Math.atan2(Utility.differenceY(a1,a2), Utility.differenceX(a1,a2)) - Math.atan2(Utility.differenceY(a3,a2), Utility.differenceX(a3,a2)))) + 360) % 360;
            if((angle < Math.toDegrees(Math.PI - parameters.EPSILON)) || (angle > Math.toDegrees(Math.PI + parameters.EPSILON))){
                return true;
            }

        }
        return false;


    } 

     /**
     *There exists three points seperated by E_PTS and F_PTS which form a triangle with triangleArea &gt; AREA1.
     * @return Return true if the condition is met, other
     * returns false.
     */
    public  boolean Condition10(){
        
        if(this.numberPoints < 5){
            return false;
        }

        if(parameters.E_PTS <1 || parameters.F_PTS < 1){
            return false;
        }

        if(parameters.E_PTS + parameters.F_PTS > this.numberPoints - 3){
            return false;
        }

        for(int idx=0; idx < this.numberPoints - parameters.E_PTS -parameters.F_PTS-2; idx++){
            Datapoints a = this.points[idx];
            Datapoints b = this.points[idx+parameters.E_PTS+1];
            Datapoints c = this.points[idx+parameters.E_PTS+ parameters.F_PTS +2];

        double triangleArea = Utility.calcTriangleArea(a,b,c);
            if (triangleArea > parameters.AREA1){

                return true;
            } 

        }
        return false;

        
    }


    /**
     * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly G PTS consecutive intervening points, such that X[j] - X[i] less than 0. (where i is less than j ) The
     * condition is not met when NUMPOINTS less than 3.
     * (1 less or equal to G PTS less or equal to NUMPOINTS − 2)
     * @return Return true if the condition is met, otherwise returns false.
     */
    public boolean Condition11(){
        if (numberPoints<3 || !( (1<=parameters.G_PTS) && (parameters.G_PTS<= numberPoints -2) ) ){return false;}

        for(int i=0;i<numberPoints-parameters.G_PTS-1;i++ ){
            int j = i + parameters.G_PTS + 1;
            if( (points[j].x - points[i].x) < 0 ){
                return true;
            }
        }
        return false;
    }

    /**
     * There exists at least one set of data points, seperated by exactly K_PTS consecutive intervening points that:
     * SubCondition1: That distance strict greater than LENGTH1
     * SubCondition2: That distance strict greater than LENGTH2
     * @return Return true if the condition is met, otherwise
     * returns false.
     */
    public boolean Condition12(){
        // 1 ≤ K_PTS ≤ (NUMPOINTS − 2)
        if ( numberPoints<3 || !(0<=parameters.LENGTH1) || !(0<=parameters.LENGTH2) || !((1<= parameters.K_PTS) && (parameters.K_PTS<=numberPoints-2)) ) {return false;}

        boolean geLength1 = false, geLength2 = false;
        for(int i=0;i<numberPoints-parameters.K_PTS-1;i++){
            int j = i + parameters.K_PTS + 1;
            double dist = Utility.calcEuclideanDistance(points[i], points[j] );

            if(dist>parameters.LENGTH1){geLength1=true;}
            if(dist<parameters.LENGTH2){geLength2=true;}

            if(geLength1 && geLength2){return true;}
        }

        return false;
    }

    /**
     * Calculate the minimum enclosing radius for 3 data points, seperated by E_PTS and F_PTS points
     * SubCondition1: minimum enclosing radius greater than RADIUS1
     * SubCondition1: minimum enclosing radius smaller than RADIUS2
     * @return Return true if the both SubConditions are met, otherwise return false
     */
    public boolean Condition13(){

        if(this.numberPoints < 5) {return false;}

        boolean biggerRadius = false;
        boolean smallerRadius = false;

        int indexPoint1, indexPoint2;
        int maxIndex = this.numberPoints - 2 - parameters.A_PTS - parameters.B_PTS;

        for (int i = 0; i < maxIndex; i++) {

            indexPoint1 = i + 1 + parameters.A_PTS;
            indexPoint2 = i + 2 + parameters.A_PTS + parameters.B_PTS;

            double radius = Utility.calcMinEnclosingRadius(this.points[i],this.points[indexPoint1], this.points[indexPoint2]);

            if (radius > parameters.RADIUS1) {biggerRadius = true;}
            if (radius < parameters.RADIUS2) {smallerRadius = true;}

            if (biggerRadius && smallerRadius) {return true;}
        }
        return false;
    }

    /**
     * Calculate the area given by 3 data points, seperated by E_PTS and F_PTS points
     * SubCondition1: a set of 3 data points, seperated by E_PTS and F_PTS points, with area greater AREA1
     * SubCondition2 a set of 3 data points, seperated by E_PTS and F_PTS points, with area smaller AREA2
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
            indexPoint2 = i + 2 + parameters.E_PTS + parameters.F_PTS;

            double area = Utility.calcTriangleArea(this.points[i],this.points[indexPoint1], this.points[indexPoint2]);

            if (area > parameters.AREA1) {biggerArea = true;}
            if (area < parameters.AREA2) {smallerArea = true;}

            if (biggerArea && smallerArea) {return true;}
        }
        return false;
    }
}
