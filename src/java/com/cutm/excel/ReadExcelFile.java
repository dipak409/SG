package com.cutm.excel;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

    public static void main(String[] args) {
        String fileName;
        fileName = "F:\\sgrade.xlsx";
        Vector dataHolder = read(fileName);
        saveToDatabase(dataHolder);
    }

    public static Vector read(String fileName) {
        Vector cellVectorHolder = new Vector();
        try {
            FileInputStream myInput = new FileInputStream(fileName);
            //POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // Iterator<Row> rowIter= new mySheet.Iterator;  
            Iterator<Row> rowIter = null;

            rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                //Vector cellStoreVector = new Vector();
                List list = new ArrayList();
                while (cellIter.hasNext()) {
                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    list.add(myCell);
                }
                cellVectorHolder.addElement(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }

    private static void saveToDatabase(Vector dataHolder) {
        String REGISTRATIONNUMBER;
        REGISTRATIONNUMBER = "";
        String STUDENTNAME;
        STUDENTNAME = "";
        String SUBJECT1;
        SUBJECT1 = "";
        String SUBJECT2;
        SUBJECT2 = "";
        String SUBJECT3;
        SUBJECT3 = "";
        String OVERALL;
        OVERALL = "";

        System.out.println(dataHolder);

        for (Iterator iterator = dataHolder.iterator(); iterator.hasNext();) {
            List list = (List) iterator.next();
            REGISTRATIONNUMBER = list.get(0).toString();
            STUDENTNAME = list.get(1).toString();
            SUBJECT1 = list.get(2).toString();
            SUBJECT2 = list.get(3).toString();
            SUBJECT3 = list.get(4).toString();
            OVERALL = list.get(5).toString();

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentDB", "dipak", "dipak");
                System.out.println("connection made...");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO STUDENTGRADES(REGISTRATIONNUMBER,STUDENTNAME,SUBJECT1,SUBJECT2,SUBJECT3,OVERALL) VALUES(?,?,?,?,?,?)");
                stmt.setString(1, REGISTRATIONNUMBER);
                stmt.setString(2, STUDENTNAME);
                stmt.setString(3, SUBJECT1);
                stmt.setString(4, SUBJECT2);
                stmt.setString(5, SUBJECT3);
                stmt.setString(6, OVERALL);

                stmt.executeUpdate();

                System.out.println("Data is inserted");
                stmt.close();
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
