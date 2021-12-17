package InterfaceAndAbstractClass;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // We are assigning a class to an interface.
        // This works because when we created our class, we implemented the interface.
        CarInterface electricCarInterface = new ElectricCar("Tesla");

        electricCarInterface.start();
        electricCarInterface.move(100);

       CarInterface dieselCarInterface = new DieselCar("Mercedes");

       dieselCarInterface.start();
       dieselCarInterface.move(100);




    }
}
