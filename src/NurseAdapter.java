public class NurseAdapter{
    public void printPatientAppointments(int nationalNumber){
        ReceptionistSingleton singleton = ReceptionistSingleton.getInstance();
        singleton.printAppointment(nationalNumber);
    }
}
