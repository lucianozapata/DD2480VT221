package com.group24.decide;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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
     * Calculate the relative angle between two data points
     * @param a First datapoint
     * @param b Second datapoint
     * @return angle between these points
     */
    public static double calcAngle(Datapoints a, Datapoints b) {
        double angleRadians = Math.atan2(a.y - b.y, a.x - b.x);
        // radians -> degrees
        double angleDegrees = Math.toDegrees(angleRadians);
        // Transform negative angles into positive angles
        if (angleDegrees < 0) angleDegrees += 360;
        return angleDegrees;
    }

    /**
     * Calculate the minimum radius of a circle which encloses the given data points
     * @param a First datapoint
     * @param b Second datapoint
     * @param c Third datapoint
     * @return minimum enclosing radius
     */
    public static double calcMinEnclosingRadius(Datapoints a, Datapoints b, Datapoints c) {

        double distanceAB = Utility.calcEuclideanDistance(a,b);
        double distanceAC = Utility.calcEuclideanDistance(a,c);
        double distanceBC = Utility.calcEuclideanDistance(b,c);

        double angleAB = Utility.calcAngle(a,b);
        double angleAC = Utility.calcAngle(a,c);
        double angleBC = Utility.calcAngle(b,c);

        // case 1: acute triangle (all angles smaller than 90 deg)
        // the smallest radius is circumcircle
        if ( angleAB < 90 || angleAC < 90 || angleBC < 90) {
            // Circumcircle reference: https://www.mathopenref.com/trianglecircumcircle.html
           double denominator = (distanceAB*distanceAB*distanceBC);
           double divider = (distanceAB + distanceAC + distanceBC) * (distanceAC + distanceBC - distanceAB) * (distanceBC + distanceAB - distanceAC)*(distanceAB + distanceAC - distanceBC);
           return denominator / Math.sqrt(divider);
        }

        // case 2: obtuse triangle (one angle bigger than 90 deg)
        // the smallest radius is the longest distance
        return Math.max(distanceAB, Math.max(distanceAC, distanceBC));
    }
}
