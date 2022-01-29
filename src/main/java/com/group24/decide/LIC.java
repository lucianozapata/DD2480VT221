package java.com.group24.decide;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
/**
 * A class for the LIC conditions
 *
 * @author
 *
 */
public class LIC {

    Parameter parameters;
    Datapoints[] datapoints;

    /**
     *
     * @param parameters Contains the inputs for LIC.
     */
    public LIC(Parameter parameters, Datapoints[] points){
        this.parameters = parameters;
        this.datapoints = points;
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


        return CMV;
    }
    /**
     *
     * @return Return true if there exists at least one set
     * of two consecutive data points that are a distance greater
     * than the length, LENGTH1, apart. (0 ≤ LENGTH1), else return false.
     */
    public boolean Condition0(){
        // How many datapoints we are going to check.
        int steps = this.datapoints.length;

        for(int index = 0; index < steps; index++ ) {
           double datapoint1X = this.datapoints[index].x;
           double datapoint1Y = this.datapoints[index].y;
           double datapoint2X = this.datapoints[index+1].x;
           double datapoint2Y = this.datapoints[index+1].y;

           // TODO: Use a help function to calculate this distance!
           if((sqrt(pow(datapoint1X - datapoint2X, 2) + pow(datapoint1Y -datapoint2Y, 2)) < this.parameters.LENGTH1)) {
                return true;
            }

        }
        return false;

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
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition3(){
        return true;
    }    /**
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
    }    /**
     *
     * @return Return true if the condition is met, other
     * returns false.
     */
    public static boolean Condition14(){
        return true;
    }














}
