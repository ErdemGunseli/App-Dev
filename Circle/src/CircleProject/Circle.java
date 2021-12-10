package CircleProject;

import java.util.Scanner;

public class Circle {

// Static variables of objects are not the same as attributes as they are not used in constructors.
    private static double pi = 3.1415;
    private double r;



    public Circle(double r) {
        this.r = r;
    }


    public double circumference(){return (2 * pi * this.r);}

    public double area() {return (pi * Math.pow(this.r, 2));}

    public double sectorArea(double theta){return ((theta/360) * this.area());}

    public double arcLength(double theta){return ((theta/360) * this.circumference());}
}



