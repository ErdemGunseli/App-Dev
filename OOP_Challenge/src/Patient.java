public class Patient {
    private String firstName;
    private int age;
    private String sex;
    private Eye leftEye;
    private Eye rightEye;
    private Heart heart;
    private Stomach stomach;
    private Skin skin;


    public Patient(String firstName, int age, String sex, Eye leftEye, Eye rightEye, Heart heart, Stomach stomach, Skin skin) {
        this.firstName = firstName;
        this.sex = sex;
        this.age = age;
        this.leftEye = leftEye;
        this.rightEye = rightEye;
        this.heart = heart;
        this.stomach = stomach;
        this.skin = skin;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public Eye getLeftEye() {
        return leftEye;
    }

    public Eye getRightEye() {
        return rightEye;
    }

    public Heart getHeart() {
        return heart;
    }

    public Stomach getStomach() {
        return stomach;
    }

    public Skin getSkin() {
        return skin;
    }


    public void displayDetails() {
        System.out.println("Patient Details:" + "\n" + this.firstName + "\n" + this.sex + "\n" + this.age + "\n");

    }

}

