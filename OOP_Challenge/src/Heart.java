import java.util.Scanner;

public class Heart extends Organ{

    private int heartRate = 247;

    public Heart(String name, String medicalCondition, int heartRate) {
        super(name, medicalCondition);
        heartRate = this.heartRate;

    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    @Override
    public void displayMedicalDetails() {
        System.out.println("Organ: " + this.getName() + "\nProblem: " + this.getMedicalCondition() + "\n" + "Heart Rate: " + this.heartRate + "\n");

        if (this.getMedicalCondition() != "None") {
            System.out.println("1. Change Heart Rate " + "\n2. Back \n");

            Scanner myScanner = new Scanner(System.in);
            int optionChosen = myScanner.nextInt();

            if (optionChosen == 1) {
                System.out.print("Enter heart rate: ");
                int chosenHeartRate = myScanner.nextInt();
                if (chosenHeartRate < 90) {
                    System.out.println("The chosen heart rate is too low");
                }
                else if (chosenHeartRate > 120) {
                    System.out.println("The chosen heart rate is too high!");
                }
                else{
                    this.setHeartRate(chosenHeartRate);
                    this.fixMedicalCondition();

                }
            }

            }
        }
    }




