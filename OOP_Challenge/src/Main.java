import java.util.Scanner;

public class Main {

    public static  boolean between(int num, int minimum, int maximum) {
        if ((num > maximum) || (num < minimum)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        Boolean cured = false;


        Patient patient = new Patient("John", 36, "Male",
                new Eye("Left Eye", "Infected"),
                new Eye("Right Eye", "Exploded"),
                new Heart("Heart", "Too Fast",245 ),
                new Stomach("Stomach", "Stabbed"),
                new Skin("Skin", "Burnt")
                );

        patient.displayDetails();


        while (!cured) {
            int optionChosen = 0;
            while (!between(optionChosen, 1, 6)) {

                System.out.println("Please select an organ to operate on: \n1) Left Eye \n2) Right Eye \n3) Heart \n4) Stomach \n5) Skin \n6) Quit \n");
                optionChosen = myScanner.nextInt();
            }
            System.out.println("You have chosen option " + optionChosen + ".");

            switch (optionChosen){
                case 1: {
                    patient.getLeftEye().displayMedicalDetails();
                    break;
                }
                case 2: {
                    patient.getRightEye().displayMedicalDetails();
                    break;
                }
                case 3: {
                    patient.getHeart().displayMedicalDetails();
                    break;
                }
                case 4: {
                    patient.getStomach().displayMedicalDetails();
                    break;
                }

                case 5: {
                    patient.getSkin().displayMedicalDetails();
                    break;
                }

                default: {
                    cured = true;
                }
            }


        }
}
}




