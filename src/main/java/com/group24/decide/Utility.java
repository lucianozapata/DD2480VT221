package java.com.group24.decide;

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
}
