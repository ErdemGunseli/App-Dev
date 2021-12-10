import java.util.Scanner;

public class Organ {
    private String name;
    private String medicalCondition;


    public Organ(String name, String medicalCondition) {
        this.name = name;
        this.medicalCondition = medicalCondition;
    }


    public String getName() {return name;}

    public void setName (String name) { this.name = name;}


    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {this.medicalCondition = medicalCondition;}


    public void displayMedicalDetails(){
        System.out.println("Organ: " + this.name + "\nProblem: " + this.medicalCondition + "\n");


        if (this.medicalCondition != "None") {
            System.out.println("1. Cure " + this.name + "\n2. Back \n");

            Scanner myScanner = new Scanner(System.in);
            int optionChosen = myScanner.nextInt();

            if (optionChosen == 1) {
                this.fixMedicalCondition();
            }
        }
    }




    public void fixMedicalCondition(){
        System.out.println("The patient's " + this.name + " is no longer " + this.medicalCondition + "! \n");
        this.setMedicalCondition("None");

    }

}
