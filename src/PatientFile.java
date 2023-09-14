import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class PatientFile {

    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriter;
    private String file = "patientInfo.txt";

    public void addPatientToFile(Patient patient){
        try {
            fileWriter = new FileWriter(file,true);


            String patientObjToString = objectMapper.writeValueAsString(patient);
            System.out.println(patientObjToString);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(patientObjToString);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Patient Added Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatientFromFile(int nationalNumber){
        String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    break;
                }
            }

            Patient patient = objectMapper.readValue(line,Patient.class);
            reader.close();
            return patient;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Patient> getAllPatientsFromFile(){
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                Patient patient = objectMapper.readValue(line,Patient.class);
                patients.add(patient);
            }
            reader.close();
            return patients;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
