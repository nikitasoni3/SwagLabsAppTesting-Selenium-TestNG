package Utilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelReader {
    static XSSFWorkbook workbook;
    ArrayList ls;
    public ExcelReader() {
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\nikita.soni\\IdeaProjects\\PageObjectModel\\src\\test\\resources\\Excel\\TestData_SwagLabs.xlsx");
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ls = getData("Purchase1");

    }
    public String getUsername() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        return String.valueOf(sheet.getRow(1).getCell(0));
    }

    public String getPassword() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(1);
        XSSFCell cell2 = row.getCell(1);
        return cell2.toString();
    }

    public ArrayList<String> getData(String testCaseName) {
        ArrayList<String> a = new ArrayList <String>();
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("UserInfo")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                Iterator<Cell> ce = firstrow.cellIterator();
                int k = 0;
                int coloumn = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Order Number")) {
                        coloumn = k;
                    }
                    k++;
                }
                System.out.println(coloumn);
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if(c.getCellType()==CellType.STRING){
                                a.add(c.getStringCellValue());
                            }else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }
            }
        }
        return a;
    }
    public String getFirstName(){
        return (String) ls.get(3);
    }
    public String getLastName(){
        return (String) ls.get(4);
    }
    public String getPostalCode(){
        return (String) ls.get(5);
    }
}


/*
if(cv.next().getCellType()== CellType.NUMERIC){
                                intList.add((int) cv.next().getNumericCellValue());
                            } else if (cv.next().getCellType()==CellType.STRING) {
                                stringList.add(cv.next().getStringCellValue());
                            }
        }*/
