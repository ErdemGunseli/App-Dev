package FirstIntellij;

public class Main {

    public static void main(String[] args) {
        //    When instantiating a class, use the following syntax:

        Phone Samsung = new Phone("Galaxy Z Fold 3", 7.6, 12);

        Samsung.playMusic("Song");

//        If we wanted to prevent direct access to the field itself, we could use the following syntax:
        Samsung.setCamera(5);

//         To get the value of the field, we could do the following:
        System.out.println(Samsung.getCamera());
    }
}
