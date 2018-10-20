import com.mr.model.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodd on 2018/10/19.
 */
public class PoiTest {

    @Test
    public void test() {


        //1：创建 excel
        HSSFWorkbook wb = new HSSFWorkbook();
        //2：选择 sheet
        HSSFSheet sheet = wb.createSheet("sheet1805");

        //3：选择 行
        HSSFRow row = sheet.createRow(0);

        //4：选择单元格
        HSSFCell cell = row.createCell(0);

        //5：添加数据
        cell.setCellValue("1805导出excel");
        //6：保存--流生成一个文件
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\poi\\1805.xls");
            wb.write(fileOut);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态导出数据
     */
    @Test
    public void test2() {
        //从数据库中读取数据
        List<User> users = new ArrayList<>();
        users.add(new User(1, "zs"));
        users.add(new User(2, "ls"));
        users.add(new User(3, "ww"));

        //1：创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //2：创建sheet
        HSSFSheet sheet = wb.createSheet("sheet1805");


        for (int i = 0; i < users.size(); i++) {
            HSSFRow row = sheet.createRow(i);

            HSSFCell cell = row.createCell(0);
            cell.setCellValue(users.get(i).getUserId());
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(users.get(i).getUserName());
        }

        sheet.autoSizeColumn(1);
        //6：保存--流生成一个文件
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\poi\\1805-01.xls");
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入数据
     */
    @Test
    public void test3() {
        try {
            POIFSFileSystem fs=
                    new POIFSFileSystem(new FileInputStream("C:\\Users\\Administrator\\Desktop\\poi\\1805-01.xls"));
            //1：获取到excel文件
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //2：选择sheet文件
            HSSFSheet sheet = wb.getSheetAt(0);

            //3：选择行
            HSSFRow row = sheet.getRow(0);
            //4：选择单元格
            HSSFCell cell = row.getCell(0);

            //5：获取单元格中得数据
            double value = cell.getNumericCellValue();
            //6：打印数据
            System.out.println(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 动态导入数据
     */
    @Test
    public void test4() {
        try {
            POIFSFileSystem fs=
                    new POIFSFileSystem(new FileInputStream("C:\\Users\\Administrator\\Desktop\\poi\\1805-01.xls"));
            //1：获取到excel文件
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //2：选择sheet文件
            HSSFSheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();


            List<User> users = new ArrayList<>();

            for (int i = 0; i <= lastRowNum; i++) {

                HSSFRow row = sheet.getRow(i);

                int cellNum = row.getLastCellNum();

                User user = new User();

                for (int j = 0; j < cellNum; j++) {
                    HSSFCell cell = row.getCell(j);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getCellType(HSSFCell cell){

        String value = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
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
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }

        return value;
    }


    @Test
    public void test5(){
        String fileName = "用户名单.xls";

        int i = fileName.indexOf(".xlsx");
        System.out.println(i);
    }
}