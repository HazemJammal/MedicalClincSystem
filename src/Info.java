import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Info {
    private int nationalNumber;
    private String name;
    private String contactNumber;
    private String address;

    public int getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(int nationalNumber) {
        if (nationalNumber <= 0)
            System.out.println("National Number cannot be Minus or Zero");
        else if (nationalNumber <= 999 || nationalNumber > 9999)
            System.out.println("National Number is only 4 Digits");
        else
        this.nationalNumber = nationalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        String regex = "(077|078|079)\\d{7}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(contactNumber);

        if (matcher.matches()){
            this.contactNumber = contactNumber;
        }
        else
            System.out.println("Number is to short or not Jordanian");

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
