package InterfaceAndAbstractClass;

// We can implement multiple interfaces by separating each with a comma.
public class DieselCar implements CarInterface, TestInterface {
    private String name;

    public DieselCar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        System.out.println("Fuel Flow Started...");
    }

    @Override
    public void move(int speed) {
        System.out.println(this.getName() + " is moving at " + speed + "km/h.");

    }

    @Override
    public void printName(String name) {
        System.out.println(name);
    }
}
