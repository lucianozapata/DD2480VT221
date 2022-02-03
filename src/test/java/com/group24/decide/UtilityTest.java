package com.group24.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void calcEuclideanDistance() {
        // Euclidean distance for two data points which only differ on x-axis.
        // Therefore, result should be the delta on x-axis.
        Datapoints p1 = new Datapoints(0,0);
        Datapoints p2 = new Datapoints(10,0);
        double distance = Utility.calcEuclideanDistance(p1, p2);
        assertEquals(10, distance);

        // Euclidean distance for two data points which only differ on y-axis.
        // Therefore, result should be the delta on y-axis.
        Datapoints p3 = new Datapoints(0,10);
        Datapoints p4 = new Datapoints(0,0);
        distance = Utility.calcEuclideanDistance(p3, p4);
        assertEquals(10, distance);
    }

    @Test
    void calcTriangleArea() {
        // Rectangular triangle with width=10, height=10.
        // On rectangular triangle the area can be calculated with: width*height/2 = 50.
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
        // Rectangular triangle, max angle should therefore be 90 deg.
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(1, 0);
        Datapoints p3 = new Datapoints(0, 1);
        double distanceA = Utility.calcEuclideanDistance(p1,p2);
        double distanceB = Utility.calcEuclideanDistance(p1,p3);
        double distanceC = Utility.calcEuclideanDistance(p2,p3);
        double maxAngle = Utility.calcMaxAngle(distanceA, distanceB, distanceC);
        assertEquals(90, maxAngle, 0.01);

        // Obtuse triangle
        // Maximum angle is ~157.38 (calculated online).
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
        // Acute triangle
        // The minimum radius should be the circumcircle of the triangle
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(1, 0);
        Datapoints p3 = new Datapoints(0, 1);

        double minRadius = Utility.calcMinEnclosingRadius(p1, p2, p3);
        assertEquals(Math.sqrt(2) / 2, minRadius, 0.01);

        // Rectangular triangle
        // The minimum radius should be the half of the longest distance (p1 to p3)
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