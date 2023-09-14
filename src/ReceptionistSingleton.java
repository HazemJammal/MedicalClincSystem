
import java.util.ArrayList;


public class ReceptionistSingleton extends Staff{
    private static ReceptionistSingleton instance = null;

    public static ReceptionistSingleton getInstance(){
        if (instance == null)
            instance = new ReceptionistSingleton();

        return instance;
    }


    private CheckPatient checkPatient = new CheckPatient();
    private PatientFile patientFile = new PatientFile();
    private CheckAppointment checkAppointment = new CheckAppointment();
    private AppointmentFile appointmentFile = new AppointmentFile();

    public void addPatient(Patient patient) {
        if (checkPatient.checkPatientExists(patient.getNationalNumber())) {
            System.out.println("Patient already exists in the system");
        } else {
            patientFile.addPatientToFile(patient);
        }
    }

    public void reserveAppointment(int nationalNumber, int year, int month, int day, int hour, int minute) {
        if (checkPatient.checkPatientExists(nationalNumber)) {
            if (!checkAppointment.checkAppointmentTime(year, month, day, hour, minute)) {
                Appointment appointment = new Appointment();

                Patient patient = patientFile.getPatientFromFile(nationalNumber);

                appointment.setNationalNumber(nationalNumber);
                appointment.setPatientName(patient.getName());
                appointment.setAppointmentDate(year, month, day, hour, minute);

                appointmentFile.addAppointmentToFile(appointment);
            }
        }
    }



    public void printAppointment(int nationalNumber) {
        if (checkPatient.checkPatientExists(nationalNumber)) {

            if ((checkAppointment.checkPatientAppointment(nationalNumber))) {
                ArrayList<Appointment> appointments = appointmentFile.getAppointmentFromFile(nationalNumber);

                for (int i = 0; i < appointments.size(); i++) {
                    System.out.println("Patient:" + appointments.get(i).getPatientName() + ", National Number: " + appointments.get(i).getNationalNumber() + ", Date:" + appointments.get(i).getAppointmentDate());
                }
            }
        }

    }

    public void printAllAppointment() {
        ArrayList<Appointment> appointments = appointmentFile.getAppointmentsFromFile();
        if (appointments.isEmpty()) {
            System.out.println("System doesn't have any appointments");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println("Patient:" + appointments.get(i).getPatientName() + ", National Number: " + appointments.get(i).getNationalNumber() + ", Date:" + appointments.get(i).getAppointmentDate());
            }
        }
    }


    public double calcBill(int nationalNumber) {
        if (checkPatient.checkPatientExists(nationalNumber)) {
            if (checkAppointment.checkPatientAppointment(nationalNumber)) {
                Patient patient = patientFile.getPatientFromFile(nationalNumber);
                double billAmount = 0;

                double discount = (patient.getPatientType() == PatientType.DENTAL_CONSULTANT) ? 0.2
                        : (patient.getPatientType() == PatientType.DENTIST_STUDENT) ? 0.5 : 1;

                ArrayList<Appointment> patientAppointments = appointmentFile.getAppointmentFromFile(nationalNumber);

                int appointmentsCount = patientAppointments.size();
                billAmount = (appointmentsCount * 30) * discount;
                System.out.println("Total bill is: " + billAmount +"JD");
                return billAmount;
            }
        }
        return 0;
    }

    public void printBill(int nationalNumber) {
        if (checkPatient.checkPatientExists(nationalNumber)) {
            if (checkAppointment.checkPatientAppointment(nationalNumber)) {
                Patient patient = patientFile.getPatientFromFile(nationalNumber);
                ArrayList<Appointment> appointments = appointmentFile.getAppointmentFromFile(nationalNumber);

                System.out.println("HTU Dental Care Clinic");
                System.out.println("Patient Bill");
                System.out.println("Patient Name:" + patient.getName() + ", National Number:" + patient.getNationalNumber());
                int discountPercent = (patient.getPatientType() == PatientType.DENTAL_CONSULTANT) ? 80
                        : (patient.getPatientType() == PatientType.DENTIST_STUDENT) ? 50 : 0;


                for (int i = 0; i < appointments.size(); i++) {
                    System.out.println("Appointment:" + (i + 1) + ", Date:" + appointments.get(i).getAppointmentDate() + ", Price:" + 30);
                }
                System.out.println("Discount: " + discountPercent + "%");
                calcBill(nationalNumber);
            }
        }

    }

}
