package ma.youcode.Models;

public class Agent {
    private String Email;
    private String Password;
    private int Validation;

    public Agent(String Email, String Password, String Validation) {
        this.Email = Email;
        this.Password = Password;
        this.Validation = Integer.parseInt(Validation);
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public int getValidation() {
        return Validation;
    }

}
