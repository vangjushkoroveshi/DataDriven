package ExcelReader.Data;

public class Login_Data{
    private String Step;
    private String Execution;
    private String Username;
    private String Password;

    public Login_Data(){

    }

    public Login_Data(String step, String execution, String username, String password) {
        Step = step;
        Execution = execution;
        Username = username;
        Password = password;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        Step = step;
    }

    public String getExecution() {
        return Execution;
    }

    public void setExecution(String execution) {
        Execution = execution;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
