public class Medicine {
    private String name;
    private double dose;
    private int nationalNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(int patientNationalNumber) {
        this.nationalNumber = patientNationalNumber;
    }
}
