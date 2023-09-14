public class Doctor extends Staff{

    private CheckPatient checkPatient = new CheckPatient();
    private CheckAppointment checkAppointment = new CheckAppointment();
    private PatientFile patientFile = new PatientFile();
    private MedicineFile medicineFile = new MedicineFile();
    private CheckMedicine checkMedicine = new CheckMedicine();
    private TreatmentFile treatmentFile = new TreatmentFile();
    private CheckTreatment checkTreatment = new CheckTreatment();

    public Patient getPatient(int nationalNumber){
        if (checkPatient.checkPatientExists(nationalNumber)){
            Patient patient = patientFile.getPatientFromFile(nationalNumber);
            return patient;
        }
        return null;
    }

    public void addMedicine(String name,double dose,int nationalNumber){
        if (checkPatient.checkPatientExists(nationalNumber)){
            if (checkAppointment.checkPatientAppointment(nationalNumber)) {
                Medicine medicine = new Medicine();
                medicine.setDose(dose);
                medicine.setName(name);
                medicine.setNationalNumber(nationalNumber);
                if (!checkMedicine.checkMedicine(medicine))
                    medicineFile.addMedicineToFile(medicine);
            }
        }
    }
    public void addTreatment(String description, int nationalNumber){
        if (checkPatient.checkPatientExists(nationalNumber)) {
            if (checkAppointment.checkPatientAppointment(nationalNumber)) {
                Treatment treatment = new Treatment();
                treatment.setDescription(description);
                treatment.setNationalNumber(nationalNumber);

                if (!(checkTreatment.checkTreatment(treatment)))
                    treatmentFile.addTreatment(treatment);

            }
        }
    }
}
