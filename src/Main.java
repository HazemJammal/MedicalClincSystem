import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReceptionistSingleton receptionist = ReceptionistSingleton.getInstance();
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();
        Scanner scanner = new Scanner(System.in);

        String job ="";
        int input = 0;
        while (true){
            System.out.println("enter job description(doctor, nurse,receptionist)");
            job = scanner.next();
            if (job.equals("doctor")){
                System.out.println("enter 1 to add medicine\n" +
                        "enter 2 to add treatment");
                input = scanner.nextInt();
                if (input == 1){
                    System.out.println("enter patient national number");
                    int nationalNumber = scanner.nextInt();
                    System.out.println("enter medicine name");
                    String name = scanner.next();
                    System.out.println("enter does");
                    double dose = scanner.nextDouble();

                    doctor.addMedicine(name,dose,nationalNumber);
                }
                else if (input == 2){
                    System.out.println("enter patient national number");
                    int nationalNumber = scanner.nextInt();
                    System.out.println("enter treatment description");
                    String description = scanner.next();

                    doctor.addTreatment(description,nationalNumber);
                }
                else {
                    System.out.println("wrong input");
                }
            }
            else if (job.equals("nurse")){
                System.out.println("enter 1 for individual report\n" +
                        "enter 2 for all reports");
                input = scanner.nextInt();
                if (input == 1){
                    System.out.println("enter patient national number");
                    int nationalNumber = scanner.nextInt();
                    nurse.issueReport(nationalNumber);
                }
                else if (input == 2){
                    nurse.issueReport();
                }
                else
                    System.out.println("wrong input");
            }
            else if (job.equals("receptionist")){
                System.out.println("enter 1 to add patient\n" +
                        "enter 2 to add appointment\n" +
                        "enter 3 to print bill\n" +
                        "enter 4 to print appointment");
                input = scanner.nextInt();
                if (input == 1){
                    System.out.println("enter patient name");
                    String name = scanner.next();
                    System.out.println("enter patient address");
                    String address = scanner.next();
                    System.out.println("enter patient type (ordinary, student, consultant");
                    String type = scanner.next();
                    PatientType type1 = (type.equals("ordinary"))? PatientType.ORDINARY: (type.equals("student"))?
                            PatientType.DENTIST_STUDENT:
                            (type.equals("consultant"))? PatientType.DENTAL_CONSULTANT : PatientType.ORDINARY;
                    System.out.println("enter national number");
                    int nationalNumber = scanner.nextInt();
                    System.out.println("enter contact number");
                    String number = scanner.next();

                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setNationalNumber(nationalNumber);
                    patient.setAddress(address);
                    patient.setContactNumber(number);
                    patient.setPatientType(type1);

                    receptionist.addPatient(patient);
                }
                else if(input ==2 ){
                    System.out.println("enter patient national number");
                    int nationalNumber = scanner.nextInt();
                    System.out.println("enter year");
                    int year = scanner.nextInt();
                    System.out.println("enter month 1-12");
                    int month = scanner.nextInt();
                    System.out.println("enter day 1-31");
                    int day = scanner.nextInt();
                    System.out.println("enter hour 24 hour system");
                    int hour = scanner.nextInt();
                    System.out.println("enter minute ");
                    int minute = scanner.nextInt();
                    receptionist.reserveAppointment(nationalNumber,year,month,day,hour,minute);
                }
                else if (input == 3){
                    System.out.println("enter patient national number");
                    int nationalNumber = scanner.nextInt();
                    receptionist.printBill(nationalNumber);
                }
                else if (input == 4){
                    receptionist.printAllAppointment();
                }
                else
                    System.out.println("Wrong input");
            }
            else
                System.out.println("Wrong Job type");
        }
    }
}
