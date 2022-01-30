package com.group24.decide;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

public class Utility {

    /**
     * Calculate the euclidean distance of two data points
     * @param a First datapoint
     * @param b Second datapoint
     * @return distance
     */
    public static double calcEuclideanDistance(Datapoints a, Datapoints b){
        return sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
    }


    /**
     * Calculate the difference
     * @param a First datapoint
     * @param b Second datapoint
     * @return difference
     */
    public static double difference(Datapoints a, Datapoints b) {
         
       return a.x -b.x;
    }


    /**
     * Calculate the area of a triangle
     * @param a First datapoint
     * @param b Second datapoint
     * @param c Third datapoint
     * @return area
     */
    public static double calcTriangleArea(Datapoints a, Datapoints b, Datapoints c) {
       double lengthSide1 = calcEuclideanDistance(a, b);
       double lengthSide2 = calcEuclideanDistance(a, c);
       double lengthSide3 = calcEuclideanDistance(b, c);
       double p = (lengthSide1 + lengthSide2 + lengthSide3)/2;
       return Math.sqrt(p*(p-lengthSide1)*(p-lengthSide2)*(p-lengthSide3));
    }

    /**
     * Determine which quadrant the point lies on
     * @param x First datapoint
     * @return one of the four quadrants
     */
    public static int chooseQuadrant(Datapoints x) {
        if (x.x >= 0 && x.y >= 0){
            return 1;
        }
        else if (x.x < 0 && x.y >= 0){
            return 2;
        }
        else if (x.x <= 0 && x.y < 0){
            return 3;
        }
        else {
            return 4;
        }
    }

    /**
     * Two Datapoints should be considered equal if
     * they have the same coordinates
     *
     * @param point1 The first Datapoint to compare
     * @param point2 The second Datapoint to compare
     * @return True if the the x-and y-coordinate
     *  of the two datapoints are equal, otherwise
     *  returns false
     */
    public static boolean checkIfEqual(Datapoints point1, Datapoints point2){
        if(point1.x == point2.x && point1.y == point2.y){return true;}
        return false;
    }

    /**
     * Calculates the shortest distance between the line joining the
     * Datapoints first and last to that of cpr.
     *
     * @param first The first Datapoint, can not be identical to last
     * @param last The last Datapoint, can not be identical to first
     * @param cpr The Datapoint to check distance
     * @return Returns the shortest distance between cpr
     * and the line between first and last.
     */
    public static double lineDistPoints(Datapoints first, Datapoints last, Datapoints cpr){
        if(checkIfEqual(first, last)){
            return calcEuclideanDistance(first, cpr);
        }
        // (y1-y2)x + (x2-x1)y + (x1y2 -x2y1) = 0
        double a = first.y - last.y;
        double b = last.x - first.x;
        double c = (first.x * last.y) - (last.x * first.y);

        double distance = abs( (a*cpr.x) + (b*cpr.y) + c ) / (sqrt( pow(a,2)+pow(b,2) ) );
        return distance;

    }

}
