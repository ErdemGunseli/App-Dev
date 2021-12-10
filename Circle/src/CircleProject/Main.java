package CircleProject;

import java.util.Scanner;

public class Main {

    public static Circle createCircle(){
        System.out.print("\nEnter the radius of the circle: ");
        Scanner myScanner = new Scanner(System.in);
        return (new Circle(myScanner.nextDouble()));
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        boolean done = false;
        double theta;
        double r;

        Circle myCircle = createCircle();

        while (!done) {
            int menuChoice = 0;
            while (menuChoice < 1 || menuChoice > 6) {
                System.out.println("\n1. Area \n2. Circumference \n3. Sector Area \n4. Arc Length \n5. Change Radius \n6. Quit \n");
                menuChoice = myScanner.nextInt();
            }

            switch (menuChoice) {

                case 1:
                    System.out.println("Area: " + myCircle.area());
                    break;
                case 2:
                    System.out.println("Circumference: " + myCircle.circumference());
                    break;
                case 3:
                    System.out.print("Angle: ");
                    theta = myScanner.nextDouble();
                    System.out.println("Sector Area: " + myCircle.sectorArea(theta));
                    break;
                case 4:
                    System.out.print("Angle: ");
                    theta = myScanner.nextDouble();
                    System.out.println("Arc Length: " + myCircle.arcLength(theta));
                    break;
                case 5:
                    myCircle = createCircle();
                    break;
                default:
                    done = true;
                    break;
            }
        }

    }


}
