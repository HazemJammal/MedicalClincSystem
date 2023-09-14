import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class MedicineFile {
    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriter;
    private String file = "medicines.txt";

    public void addMedicineToFile(Medicine medicine){
        try {
            fileWriter = new FileWriter(file,true);


            String medicineObjToString = objectMapper.writeValueAsString(medicine);
            System.out.println(medicineObjToString);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(medicineObjToString);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Medicine Added Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public ArrayList<Medicine> getMedicineFromFile(int nationalNumber){
       ArrayList<Medicine> medicines = new ArrayList<>();
       String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
       try {
           BufferedReader reader = new BufferedReader(new FileReader(file));
           String line;

           while ((line = reader.readLine()) != null) {
               if (line.contains(checkNationalNumber)) {
                   Medicine medicine = objectMapper.readValue(line,Medicine.class);
                   medicines.add(medicine);
               }
           }
           reader.close();
           return medicines;
       }

       catch (IOException e){
           e.printStackTrace();
       }
       return null;
   }
}
