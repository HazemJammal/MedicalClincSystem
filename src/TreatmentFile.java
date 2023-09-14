import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class TreatmentFile {

    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriter;
    private String file = "treatments.txt";
    public void addTreatment(Treatment treatment){
        try {
            fileWriter = new FileWriter(file,true);

            String treatmentObjToString = objectMapper.writeValueAsString(treatment);
            System.out.println(treatmentObjToString);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(treatmentObjToString);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Treatment Added Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Treatment> getTreatmentsFromFile(int nationalNumber){
        ArrayList<Treatment> treatments = new ArrayList<>();
        String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    Treatment treatment = objectMapper.readValue(line,Treatment.class);
                    treatments.add(treatment);
                }
            }
            reader.close();
            return treatments;
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
