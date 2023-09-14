
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckPatient {
    private File file = new File("patientInfo.txt");

    public boolean checkPatientExists(int nationalNumber){
        String checkNationalNumber = "\"nationalNumber\":"+nationalNumber;
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
        System.out.println("Patient doesn't exist in the system");
        return false;
    }
}
