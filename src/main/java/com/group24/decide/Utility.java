package com.group24.decide;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

/**
 * Class geometric utility functions used multiple times.
 */
public class Utility {

    /**
     * Calculate the euclidean distance of two data points.
     * @param a First datapoint
     * @param b Second datapoint
     * @return distance
     */
    public static double calcEuclideanDistance(Datapoints a, Datapoints b){
        return sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
    }


    /**
     * Calculate the difference in the x-axis.
     * @param a First datapoint
     * @param b Second datapoint
     * @return difference
     */
    public static double differenceX(Datapoints a, Datapoints b) {
         
       return a.x -b.x;
    }

    /**
     * Calculate the difference in the y-axis.
     * @param a First datapoint
     * @param b Second datapoint
     * @return difference
     */
    public static double differenceY(Datapoints a, Datapoints b) {
         
       return a.y -b.y;
    }



    /**
     * Calculate the area of a triangle (Heron's formula)
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
     * Determine which quadrant the point lies on.
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
     * Calculate the maximum angle in a triangle given by three points.
     * @param lengthA first length of triangle
     * @param lengthB second length of triangle
     * @param lengthC third length of triangle
     * @return maximum angle in this triangle
     */
    public static double calcMaxAngle(double lengthA, double lengthB, double lengthC) {

        // Square of lengths
        double lengthA2 = Math.pow(lengthA, 2);
        double lengthB2 = Math.pow(lengthB, 2);
        double lengthC2 = Math.pow(lengthC, 2);

        // Cosine law
        double alpha = Math.acos((lengthB2 + lengthC2 - lengthA2)/(2*lengthB*lengthC));
        double betta = Math.acos((lengthA2 + lengthC2 - lengthB2)/(2*lengthA*lengthC));
        double gamma = Math.acos((lengthA2 + lengthB2 - lengthC2)/(2*lengthA*lengthB));

        // Converting radius to degrees
        alpha = Math.toDegrees(alpha);
        betta = Math.toDegrees(betta);
        gamma = Math.toDegrees(gamma);

        // return maximum angle
        return Math.max(alpha, Math.max(betta, gamma));
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

        double biggestAngle = Utility.calcMaxAngle(distanceAB, distanceAC, distanceBC);

        // case 1: acute triangle (all angles smaller than 90 deg)
        // the smallest radius is circumcircle
        if (biggestAngle < 90) {
            // Circumcircle reference: https://www.mathopenref.com/trianglecircumcircle.html
           double denominator = (distanceAB * distanceAC *distanceBC);
           double divider = (distanceAB + distanceAC + distanceBC) * (distanceAC + distanceBC - distanceAB) * (distanceBC + distanceAB - distanceAC)*(distanceAB + distanceAC - distanceBC);
           return denominator / Math.sqrt(divider);
        }

        // case 2: obtuse triangle (one angle bigger than 90 deg)
        // the smallest radius is half of the longest distance
        return Math.max(distanceAB, Math.max(distanceAC, distanceBC)) / 2;
    }

    /**
     * Two Data points are equal if they have the same coordinates
     * @param point1 The first Datapoint to compare
     * @param point2 The second Datapoint to compare
     * @return True if x-and y-coordinate of the two data points are equal, otherwise
     *  returns false
     */
    public static boolean checkIfEqual(Datapoints point1, Datapoints point2){
        if(point1.x == point2.x && point1.y == point2.y){return true;}
        return false;
    }

    /**
     * Calculates the shortest distance between the line joining the data points first and last to that of cpr.
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

    /**
     * Calculate the angle between three data points where one is the Vertex points,
     * described as the vertexPoints in this method. The returned angle is in radians.
     * @param pointsA One data point
     * @param vertexPoints The data point which angle we are calculating
     * @param pointsB Second data point
     * @return The angle of vertexPoints in radians.
     */
    public static double calculateAngle(Datapoints pointsA, Datapoints vertexPoints, Datapoints pointsB){
        
        double b = calcEuclideanDistance(pointsA, vertexPoints);
        double c = calcEuclideanDistance(pointsA, pointsB);
        double a = calcEuclideanDistance(vertexPoints, pointsB);

        // Law of cosinus a2=b2+c2???2bc???cos??
        // ?? = aCos((b2 + c2 - a2) / 2bc)
        double angle = Math.acos((Math.pow(a, 2) +Math.pow(b, 2) - Math.pow(c, 2))/(2*b*a));

        return angle;
    }
}
