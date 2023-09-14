import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CheckAppointment {
    private File file = new File("appointments.txt");
    private CheckPatient checkPatient = new CheckPatient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public boolean checkAppointmentTime(int year,int month,int day,int hour,int minute) {
        String datetime = String.valueOf(LocalDateTime.of(year, month, day, hour, minute));

        String pattern = "yyyy-MM-dd'T'HH:mm";


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(datetime,formatter);

        LocalTime appointmentTime = dateTime.toLocalTime();

        LocalTime startTime = LocalTime.of(9, 59);
        LocalTime endTime = LocalTime.of(18, 31);


        if (appointmentTime.isBefore(startTime) || appointmentTime.isAfter(endTime)){
            System.out.println("Appointment time outside working hours");
            return true;
        }

        try {
            String checkAppointmentTime = "appointmentDate\":\""+datetime+"\"";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                Appointment appointment = objectMapper.readValue(line,Appointment.class);
                LocalDateTime checkTime = LocalDateTime.parse(appointment.getAppointmentDate(),formatter);

                long minutesDifference = ChronoUnit.MINUTES.between(appointmentTime, checkTime);
                if (line.contains(checkAppointmentTime) || Math.abs(minutesDifference) < 30) {
                    System.out.println("Appointment already booked by another patient");
                    return true;
                }

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkPatientAppointment(int nationalNumber){
        String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
        if (!(checkPatient.checkPatientExists(nationalNumber))){
            System.out.println("Patient doesn't exist in the system");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    return true;
                }
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Patient doesn't have any appointments");
        return false;
    }
}
