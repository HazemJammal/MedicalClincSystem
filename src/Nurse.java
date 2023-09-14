import java.util.ArrayList;

// Nurse is an Adapter of Receptionist, as we used print appointment method from the Receptionist class
public class Nurse extends NurseAdapter{
    private CheckPatient checkPatient = new CheckPatient();
    private PatientFile patientFile = new PatientFile();
    private CheckAppointment checkAppointment = new CheckAppointment();
    private AppointmentFile appointmentFile = new AppointmentFile();
    private CheckMedicine checkMedicine = new CheckMedicine();
    private MedicineFile medicineFile = new MedicineFile();
    private CheckTreatment checkTreatment = new CheckTreatment();
    private TreatmentFile treatmentFile = new TreatmentFile();
    public void issueReport(int nationalNumber) {
        if (checkPatient.checkPatientExists(nationalNumber)) {
            Patient patient = patientFile.getPatientFromFile(nationalNumber);
            System.out.println("Patient National Number:" + patient.getNationalNumber() + "," +
                    " Patient Name:" + patient.getName()+"\n");
            if (checkAppointment.checkPatientAppointment(nationalNumber)) {
                System.out.println("Patient Appointments");
                printPatientAppointments(nationalNumber);
                if (checkMedicine.checkMedicine(nationalNumber)) {
                    System.out.println("\nPatient Medicines");
                    ArrayList<Medicine> medicines = medicineFile.getMedicineFromFile(nationalNumber);
                    for (int i = 0; i < medicines.size(); i++) {
                        System.out.println("Medicine:" + (i + 1) + ",Medicine Name:" + medicines.get(i).getName()
                                + ",Dose:" + medicines.get(i).getDose());
                    }
                }
                if (checkTreatment.checkTreatment(nationalNumber)) {
                    System.out.println("\nPatient Treatments");
                    ArrayList<Treatment> treatments = treatmentFile.getTreatmentsFromFile(nationalNumber);
                    for (int i = 0; i < treatments.size(); i++) {
                        System.out.println("Treatment:" + (i + 1) + ",Treatment Name:" + treatments.get(i).getDescription());
                    }
                }
            }
        }
    }

    public void issueReport() {
        ArrayList<Patient> patients = patientFile.getAllPatientsFromFile();
        for (int i = 0; i < patients.size() ; i++) {
            issueReport(patients.get(i).getNationalNumber());
            System.out.println("----------------------------");
            }
        }
    }

