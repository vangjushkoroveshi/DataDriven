package ExcelReader;

import ExcelReader.Data.Login_Data;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<Login_Data> getLogin(){
        int rowCount = ExcelReader.getRowCount(GlobalValues.MAIN_EXCEL, GlobalValues.LOGIN);
        List<Login_Data> chooseTest = new ArrayList<>(rowCount);
        for (int i = 1; i <= rowCount; i++) {
            Login_Data login = new Login_Data();
            login.setStep(ExcelReader.getCellValue(GlobalValues.MAIN_EXCEL, GlobalValues.LOGIN, i, 0));
            login.setExecution(ExcelReader.getCellValue(GlobalValues.MAIN_EXCEL, GlobalValues.LOGIN, i, 1));
            login.setUsername(ExcelReader.getCellValue(GlobalValues.MAIN_EXCEL, GlobalValues.LOGIN, i, 2));
            login.setPassword(ExcelReader.getCellValue(GlobalValues.MAIN_EXCEL, GlobalValues.LOGIN, i, 3));
            chooseTest.add(login);
        }
        return chooseTest;
    }
}






