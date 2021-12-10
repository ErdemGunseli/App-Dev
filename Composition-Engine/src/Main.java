public class Main {

    public static void main(String[] args) {

//        Using classes in different classes inside other classes is called COMPOSITION!

//        Engine myEngine = new Engine("V8", 8000);
//        Car Mercedes = new Car("R8", 2, "orange", myEngine );

//        We could create an engine and pass it as the parameter, or we can create it directly inside the constructor of the class


//        Car Mercedes = new Car("R8", 2, "orange", new Engine("V8", 8000));
//
//        System.out.println(Mercedes.getName());

//        Below, we are using 2 getter methods to firstly get the engine, and then the model of the engine.
//        System.out.println("Engine Model: " + Mercedes.getMyEngine().getModel());


//        Sometimes we may not want to instantiate a class immediately. For this, we can use null.

//        Car Mercedes = null;
//
//        if (Mercedes != null){
//            System.out.println(Mercedes.getName());
//        }
//        else{
//            System.out.println("The car was null");
//        }


//        Constants can be set using the following syntax:
//        final int a = 5;

        final Engine myEngine = new Engine("R8", 8000);
//        We cannot change this engine, however we can still change individual properties.

        myEngine.setRpm(10000);

        System.out.println(myEngine.getRpm());

    }

}
