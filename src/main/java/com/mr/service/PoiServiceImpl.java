package com.mr.service;

import com.mr.mapper.PoiMapper;
import com.mr.model.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.Main;

import javax.servlet.ServletOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodd on 2018/10/20.
 */
@Service
public class PoiServiceImpl implements  PoiService {

    @Autowired
    private UserService userService;


    @Override
    public void export(ServletOutputStream outputStream) {
        List<User> users = userService.list();


        Workbook wb = new XSSFWorkbook();

        //1：创建工作簿
        //2：创建sheet
        Sheet sheet = wb.createSheet("sheet1805");


        for (int i = 0; i < users.size(); i++) {
            Row row = sheet.createRow(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(users.get(i).getUserId());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(users.get(i).getUserName());
        }

        sheet.autoSizeColumn(1);
        //6：保存--流生成一个文件
        try {
            /*FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\poi\\1805-01.xls");*/
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件名
     * @param fileName  文件名称
     * @param file      文件
     */
    @Override
    public void importPoi(String fileName, MultipartFile file) {
        try {
            //根据文件名判断文件时03还是07
            int index = fileName.indexOf(".xlsx");
            Workbook wb = null;
            if(index == -1){//03
                wb = new HSSFWorkbook(file.getInputStream());
            }else{//07
                wb = new XSSFWorkbook(file.getInputStream());
            }

            //1：获取到excel文件
            //2：选择sheet文件
            Sheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();


            List<User> users = new ArrayList<>();

            for (int i = 0; i <= lastRowNum; i++) {

                Row row = sheet.getRow(i);

                int cellNum = row.getLastCellNum();

                User user = new User();

                for (int j = 0; j < cellNum; j++) {
                    Cell cell = row.getCell(j);

                    String val = getCellType(cell);

                    System.out.println(val);
                    if(j == 0){
                        user.setUserId(Integer.valueOf(val));
                    }else if(j == 1){
                        user.setUserName(val);
                    }
                }

                users.add(user);
            }

            System.out.println(users);
            userService.addUsers(users);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getCellType(Cell cell){

        String value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value=sdf.format(HSSFDateUtil.getJavaDate(cell.
                            getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }

        return value;
    }


}
