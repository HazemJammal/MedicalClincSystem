import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckMedicine {
    private String file = "medicines.txt";
    public boolean checkMedicine(Medicine medicine){

        String checkNationalNumber = "{\"name\":\""+medicine.getName()+"\""+",\"dose\":"+medicine.getDose()+
                ",\"nationalNumber\":"+medicine.getNationalNumber()+ "}";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(checkNationalNumber)) {
                    System.out.println("Medicine already exists in system");
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkMedicine(int nationalNumber){
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
        System.out.println("Patient doesn't have any medicines");
        return false;
    }


}
