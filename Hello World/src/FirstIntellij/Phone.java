package FirstIntellij;

public class Phone {

//    Classes represent objects - in this case, we are representing a phone.
//    Objects can have different properties. A phone may have a screen size, name, etc.
//    For this reason, we  have properties for classes.

//    private, public or protected can be used as access modifiers. If none are used, the default is public.

    String name;
    double screenSize;
    int memoryRam;
    private int camera;


//    Generating a Constructor:
    public Phone(String name, double screenSize, int memoryRam) {
        this.name = name;
        this.screenSize = screenSize;
        this.memoryRam = memoryRam;

    }
//    Multiple constructors for a class can be implemented and this is called polymorphism.

//    'this' is the java equivalent of 'self' in python.

//    The following syntax is used for creating a method.
//    void can be replaced with a data type to be returned.

    public void playMusic (String trackName){
        System.out.println("Playing " + trackName);

    }

//    Instead of writing get and set methods ourselves, we can generate them by right-clicking on the property.
    public void setCamera (int cameraCount){
        this.camera = cameraCount;
    }

    public int getCamera (){

        return this.camera;
    }
}
