import java.time.LocalDateTime;

public class Appointment {
    private int nationalNumber;
    private String patientName;
    private String appointmentDate;

    public int getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(int nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(int year,int month,int day,int hour,int minute) {
        String time = String.valueOf(LocalDateTime.of(year, month, day, hour, minute));

        this.appointmentDate = time;
    }
}
