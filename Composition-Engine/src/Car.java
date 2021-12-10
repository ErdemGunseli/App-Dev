public class Car {

    private String name;
    private int doors;
    private String colour;
    private Engine myEngine;

    public Car(String name, int doors, String colour, Engine myEngine) {
        this.name = name;
        this.doors = doors;
        this.colour = colour;
        this.myEngine = myEngine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

//        The following will be shown when ctrl + q is pressed on the name of the method:

    /**
     * Returns the engine of the car.
     * @return
     */

    public Engine getMyEngine() {
        return myEngine;
    }

    public void setMyEngine(Engine myEngine) {
        this.myEngine = myEngine;
    }
}
