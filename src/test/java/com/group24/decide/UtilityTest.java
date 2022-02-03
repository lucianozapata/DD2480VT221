package com.group24.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void calcEuclideanDistance() {
        // Test for the EuclidianDistance method where datapoint1 is x=0,y=0 and datapoints2 x=10,y=0. Distance should therefore be 10.
        Datapoints p1 = new Datapoints(0,0);
        Datapoints p2 = new Datapoints(10,0);
        double distance = Utility.calcEuclideanDistance(p1, p2);
        assertEquals(10, distance);

        // Test for the EuclidianDistance method where datapoint1 is x=0,y=10 and datapoints2 x=0,y=0. Distance should therefore be 10.
        Datapoints p3 = new Datapoints(0,10);
        Datapoints p4 = new Datapoints(0,0);
        distance = Utility.calcEuclideanDistance(p3, p4);
        assertEquals(10, distance);

    }

    @Test
    void calcTriangleArea() {
        // Test where a triangle with base = 10 and height = 10 gives the area of 50. (B*H/2)
        Datapoints p1 = new Datapoints(0,0);
        Datapoints p2 = new Datapoints(10,0);
        Datapoints p3 = new Datapoints(0, 10);

        double area = Utility.calcTriangleArea(p1,p2,p3);
        assertEquals(50, area);
    }

    @Test
    void chooseQuadrant() {
        // Test where we use trivial datapoints on the X and Y axis and see if they match the correct Quadrant.
       
        // Should be Quadrant 1
        Datapoints p1 = new Datapoints(0, 0);
        assertEquals(1, Utility.chooseQuadrant(p1));
         // Should be Quadrant 2
        Datapoints p2 = new Datapoints(-1, 0);
        assertEquals(2, Utility.chooseQuadrant(p2));
         // Should be Quadrant 3
        Datapoints p3 = new Datapoints(0, -1);
        assertEquals(3, Utility.chooseQuadrant(p3));
         // Should be Quadrant 4
        Datapoints p4 = new Datapoints(1, -1);
        assertEquals(4, Utility.chooseQuadrant(p4));
    }

    @Test
    void lineDistPoints() {
        // Distance between p3 and p2 should be 1.
        Datapoints p1 = new Datapoints(1, 1);
        Datapoints p2 = new Datapoints(1, 2);
        Datapoints p3 = new Datapoints(2, 2);
        assertEquals(1, Utility.lineDistPoints(p1, p2, p3) );

        // Test if the Datapoint is on the line
        p1 = new Datapoints(1, 1);
        p2 = new Datapoints(1, 2);
        p3 = new Datapoints(1, 0.5);
        assertEquals(0, Utility.lineDistPoints(p1, p2, p3) );

        // Test the distance if p1==p2
        p1 = new Datapoints(1, 1);
        p2 = new Datapoints(1, 1);
        p3 = new Datapoints(1, 2);
        assertEquals(1, Utility.lineDistPoints(p1, p2, p3) );

    }

    @Test
    void checkIfEqual(){
        // Check if true for two identical points
        Datapoints p1 = new Datapoints(1, 1);
        Datapoints p2 = new Datapoints(1, 1);
        assertTrue(Utility.checkIfEqual(p1,p2) );

        // Check if false for two different Datapoints
        p1 = new Datapoints(1, 1);
        p2 = new Datapoints(1, 2);
        assertFalse(Utility.checkIfEqual(p1,p2) );
    }

    @Test
    void calcMaxAngle() {
        // Test if a triangle with a rectangular edge gives the output of 90 degrees.
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(1, 0);
        Datapoints p3 = new Datapoints(0, 1);
        double distanceA = Utility.calcEuclideanDistance(p1,p2);
        double distanceB = Utility.calcEuclideanDistance(p1,p3);
        double distanceC = Utility.calcEuclideanDistance(p2,p3);
        double maxAngle = Utility.calcMaxAngle(distanceA, distanceB, distanceC);
        assertEquals(90, maxAngle, 0.01);

        // Testcase if the maximum angle is ~157.38 in triangle (0,0), (10,0), (5,1)
        p1 = new Datapoints(0, 0);
        p2 = new Datapoints(10, 0);
        p3 = new Datapoints(5, 1);

        distanceA = Utility.calcEuclideanDistance(p1,p2);
        distanceB = Utility.calcEuclideanDistance(p1,p3);
        distanceC = Utility.calcEuclideanDistance(p2,p3);

        maxAngle = Utility.calcMaxAngle(distanceA, distanceB, distanceC);

        assertEquals(157.38, maxAngle, 0.01);
    }

    @Test
    void calcMinEnclosingRadius() {
        // Test if middle point of three points is equal to sqrt(2)/2
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(1, 0);
        Datapoints p3 = new Datapoints(0, 1);

        double minRadius = Utility.calcMinEnclosingRadius(p1, p2, p3);
        assertEquals(Math.sqrt(2) / 2, minRadius, 0.01);

        // Test if the minimum radius for a circle that covers all points for three datapoints in a line with x distance between each point,  is exactly x.
        p1 = new Datapoints(0, 0);
        p2 = new Datapoints(0, 1);
        p3 = new Datapoints(0, 2);

        minRadius = Utility.calcMinEnclosingRadius(p1, p2, p3);
        assertEquals(1, minRadius, 0.01);
    }

    @Test
    void calcAngleInRadians() {
        // P1 is the vertex datapoint and the angle between P2 and P3 is 180 degrees PI radians.
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(1, 0);
        Datapoints p3 = new Datapoints(-1, 0);
        double angle = Utility.calculateAngle(p2, p1, p3);
    
        assertEquals(angle, Math.PI) ;
    }

}