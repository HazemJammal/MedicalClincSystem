import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class AppointmentFile {

    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriter;
    private String file = "appointments.txt";
    public void addAppointmentToFile(Appointment appointment){
        try {
            fileWriter = new FileWriter(file,true);

            String patientObjToString = objectMapper.writeValueAsString(appointment);
            System.out.println(patientObjToString);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(patientObjToString);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Appointment Added Successfully");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Appointment> getAppointmentFromFile(int nationalNumber){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    Appointment appointment = objectMapper.readValue(line,Appointment.class);
                    appointments.add(appointment);
                }
            }
            reader.close();
            return appointments;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Appointment> getAppointmentsFromFile(){

        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                    Appointment appointment = objectMapper.readValue(line,Appointment.class);
                    appointments.add(appointment);
            }
            reader.close();
            return appointments;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
