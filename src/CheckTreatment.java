import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckTreatment {
    private String file = "treatments.txt";
    public boolean checkTreatment(Treatment treatment){
        String checkNationalNumber = "{\"nationalNumber\":"+treatment.getNationalNumber()+
                ",\"description\":\""+treatment.getDescription()+ "\"}";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    System.out.println("Treatment already exists in system");
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkTreatment(int nationalNumber){
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
        System.out.println("Patient doesn't have any treatments");
        return false;
    }
}
