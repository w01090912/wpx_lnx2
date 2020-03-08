package com.wpx.test;

import com.wpx.WpxLnx2Application;
import com.wpx.pojo.Employees;
import com.wpx.service.EmployeesService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/5 12:27
 * @Version: V_1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpxLnx2Application.class)
public class POITest {


    @Autowired
    private EmployeesService employeesService;

    @Test
    public void poi() {
        List<Employees> employees = employeesService.selectAllEmployees2();

        HSSFWorkbook wb = new HSSFWorkbook(); //创建excel对象
        HSSFSheet sheet = wb.createSheet("员工信息汇总"); //创建工作表
        // 设置默认列宽
        sheet.setDefaultColumnWidth(18);

        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        HSSFCellStyle cellStyle3 = wb.createCellStyle();

        cellStyle3.setWrapText(true); //自动换行
        //创建字体
        HSSFFont font = wb.createFont();
        HSSFFont font2 = wb.createFont();
        HSSFFont font3 = wb.createFont();

        font.setFontHeightInPoints((short) 16); //设置字体大小
        font2.setFontHeightInPoints((short) 14); //设置字体大小
        font3.setFontHeightInPoints((short) 12); //设置字体大小

        font.setBold(true); //粗体显示
        font2.setBold(true); //粗体显示

        //将字体应用到单元格样式
        cellStyle.setFont(font);
        cellStyle2.setFont(font2);
        cellStyle3.setFont(font3);

        cellStyle.setAlignment(HorizontalAlignment.CENTER); //水平居中
        cellStyle2.setAlignment(HorizontalAlignment.CENTER); //水平居中
        cellStyle3.setAlignment(HorizontalAlignment.CENTER); //水平居中

        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
        // 设置边框
        cellStyle2.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle3.setBorderBottom(BorderStyle.THIN); //下边框

        cellStyle2.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle3.setBorderLeft(BorderStyle.THIN);//左边框

        cellStyle2.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle3.setBorderTop(BorderStyle.THIN);//上边框

        cellStyle2.setBorderRight(BorderStyle.THIN);//右边框
        cellStyle3.setBorderRight(BorderStyle.THIN);//右边框



        //创建第一行  标题
        HSSFRow row1 = sheet.createRow(0);
        //创建第二行  表头
        HSSFRow row2 = sheet.createRow(1);


//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列

        CellRangeAddress cellRangeAddress =new CellRangeAddress(0,0,0,5);

        sheet.addMergedRegion(cellRangeAddress);

//单元格赋值

        //设置行高

        row1.setHeight((short)(30*20));

        row1.createCell(0).setCellValue("北京***科技有限公司职员信息表");

        //设置单元格样式
        row1.getCell(0).setCellStyle(cellStyle);

       /* //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        //创建样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //创建字体样式
        cellStyle.setFont(font);*/



        //表头
        String[] s = {"ID", "姓名", "年龄", "性别", "职业", "部门"};

        for (int i = 0; i < s.length; i++) {
            //第二行赋值
            Cell cell = row2.createCell(i);
            cell.setCellValue(s[i]);
            cell.setCellStyle(cellStyle2);

            //创建第三行  内容
            HSSFRow row3 = sheet.createRow(i+2);
            //第三行赋值
            row3.createCell(0).setCellValue(employees.get(i).getId());  //ID
            row3.createCell(1).setCellValue(employees.get(i).getEmpName());  //name
            row3.createCell(2).setCellValue(employees.get(i).getEmpAge()); //age
            row3.createCell(3).setCellValue(employees.get(i).getEmpSex());  //sex
            row3.createCell(4).setCellValue(employees.get(i).getEmpPost());  //职位
            row3.createCell(5).setCellValue(employees.get(i).getSection().getSection_name());  //部门名称
            for (int j = 0; j <s.length ; j++) {
                row3.getCell(j).setCellStyle(cellStyle3);
            }

        }
        try {
            wb.write(new FileOutputStream("D:/user.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //读取文件内容并提交到数据库
    @Test
    public void poiImp() {
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream("D:/user.xls"));
            Sheet sheet = workbook.getSheet("员工");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
