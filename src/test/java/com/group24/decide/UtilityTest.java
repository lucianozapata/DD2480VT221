package com.group24.decide;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void calcEuclideanDistance() {
        Datapoints p1 = new Datapoints(0,0);
        Datapoints p2 = new Datapoints(10,0);
        double distance = Utility.calcEuclideanDistance(p1, p2);
        assertEquals(10, distance);
    }

    @Test
    void calcTriangleArea() {
        Datapoints p1 = new Datapoints(0,0);
        Datapoints p2 = new Datapoints(10,0);
        Datapoints p3 = new Datapoints(0, 10);

        double area = Utility.calcTriangleArea(p1,p2,p3);
        assertEquals(50, area);
    }

    @Test
    void chooseQuadrant() {
        Datapoints p1 = new Datapoints(0, 0);
        Datapoints p2 = new Datapoints(-1, 0);
        Datapoints p3 = new Datapoints(0, -1);
        Datapoints p4 = new Datapoints(1, -1);

        assertEquals(1, Utility.chooseQuadrant(p1));
        assertEquals(2, Utility.chooseQuadrant(p2));
        assertEquals(3, Utility.chooseQuadrant(p3));
        assertEquals(4, Utility.chooseQuadrant(p4));
    }

    /**
     *
     */
    @Test
    void lineDistPoints() {
        // Test a valid input
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

    /**
     *
     */
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

}