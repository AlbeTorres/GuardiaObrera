package com.albertorres.guardiaobrera.TrabajarExels;

import android.content.Context;

import com.albertorres.guardiaobrera.MainActivity;
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class TrabajarExel {
    Context context;
    String message;



    public TrabajarExel(Context context){
        this.context=context;

    }


    public String GuardarExel(){
        Workbook workbook = new HSSFWorkbook();

        Cell cell = null;
        Row row = null;
        Sheet sheet= null;

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


        sheet = workbook.createSheet("Plan de Guardia Obrera");


        row= sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue("Usuario");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Nombre");
        cell.setCellStyle(cellStyle);

        row= sheet.createRow(1);

        cell = row.createCell(0);
        cell.setCellValue("#1");


        cell = row.createCell(1);
        cell.setCellValue("Manolo");

        File file = new File(context.getExternalFilesDir(null), "Usuarios.xls");
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream= new FileOutputStream(file);
            workbook.write(fileOutputStream);
            message= "OK";

        }catch (IOException e){
            e.printStackTrace();
            message= "error";
        }

        return message;
    }


    public String CargarExel(ArrayList<PlanGuardiaObrera> planes){



        File file = new File(context.getExternalFilesDir(null),"Plan de Guardia Obrera");
        FileInputStream inputStream = null;

        String result = "";

        try {

            inputStream = new FileInputStream(file);
            POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowsterator= sheet.rowIterator();

            while (rowsterator.hasNext()){
                HSSFRow row = (HSSFRow) rowsterator.next();

                Calendar c= Calendar.getInstance();

                c.set(  Integer.parseInt(row.getCell(2).toString()),
                        Integer.parseInt(row.getCell(3).toString()),
                        Integer.parseInt(row.getCell(4).toString())
                     );

                PlanGuardiaObrera plan = new PlanGuardiaObrera(Integer.parseInt(row.getCell(0).toString()),
                                                                row.getCell(1).toString(),
                                                                 c,
                                                                row.getCell(5).toString());


                planes.add(plan);

            }

            result = "OK";

        } catch (Exception e) {
            e.printStackTrace();
            result = " error";
        }

        return result;
    }


}
