package com.wpx.controller;

import com.wpx.ACommonAPI.BaseNorms;
import com.wpx.pojo.EmpSection;
import com.wpx.pojo.Employees;
import com.wpx.service.EmployeesService;
import com.wpx.service.SectionService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wpx
 * @Date: 2020/3/4 17:55
 * @Version: V_1.0.0
 */
@Controller
@RequestMapping("/emp")
public class EmployeesContruller {

    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private SectionService SectionService;

    @RequestMapping("/show")
    @ResponseBody
    public Map<String, Object> showEmpAndSection(Integer page, Integer rows) {
        System.out.println("展示数据的action,page: " + page + " rows: " + rows);
        List<Employees> data = employeesService.selectAllEmployees(page, rows);// page页下的rows条数据
        Integer records = employeesService.selectCount();//当前数据源下的总数据数
        Integer total = records % rows != 0 ? (records / rows + 1) : records / rows;
        System.out.println(data);
        System.out.println(total);
        System.out.println(records);
        Map<String, Object> map = new BaseNorms().setResult(data, page, total, records);
        return map;
    }


    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(String oper, Employees employees) {
        Map<String, String> map = new HashMap<String, String>();
        if ("add".equals(oper)) {
            System.out.println("添加的信息：" + employees);
            employees.setId(UUID.randomUUID().toString());
            employees.setStatus("1");
            employees.setEmpSection(employees.getSection().getSection_name());
            System.out.println(employees);
            employeesService.insEmployees(employees);
            map.put("id", employees.getId());

        } else if ("del".equals(oper)) {
            employees.setStatus("0");
            System.out.println("删除的ID：" + employees.getId());
            employeesService.updateEmployees(employees);
            System.out.println("实现删除");
        } else {
            System.out.println(employees);
            employeesService.updateEmployees(employees);
            System.out.println("数据修改成功");
            map.put("id", employees.getId());
        }
        map.put("status", "ok");
        return map;
    }


    @RequestMapping("/upload")
    public void update(String id, MultipartFile empImage, HttpSession session) throws IOException {
        System.out.println("进入文件上传file：" + empImage);
        System.out.println("进入文件上传id：" + id);
        //获取要上传的文件夹
        String files = session.getServletContext().getRealPath("static/img");

        //上传文件
        empImage.transferTo(new File(files, empImage.getOriginalFilename()));
        //修改数据库中empImage的src
        Employees employees = new Employees();
        employees.setId(id);
        System.out.println("上传文件的文件名：" + empImage.getOriginalFilename());
        employees.setEmpImage(empImage.getOriginalFilename());
        employeesService.updateEmployees(employees);
    }

    @RequestMapping("/download")
    public void poiHSSFWB(HttpServletRequest request, HttpServletResponse response) {
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

        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 5);

        sheet.addMergedRegion(cellRangeAddress);
        //单元格赋值

        //设置行高

        row1.setHeight((short) (30 * 20));
        row2.setHeight((short) (30 * 18));

        row1.createCell(0).setCellValue("北京***科技有限公司职员信息表");

        //设置单元格样式
        row1.getCell(0).setCellStyle(cellStyle);

        //表头
        String[] s = {"ID", "姓名", "年龄", "性别", "职业", "部门"};

        for (int i = 0; i < s.length; i++) {
            //第二行赋值
            Cell cell = row2.createCell(i);
            cell.setCellValue(s[i]);
            cell.setCellStyle(cellStyle2);

        }
        //创建第三行  内容
        for (int i = 0; i < employees.size(); i++) {

            HSSFRow row3 = sheet.createRow(i + 2);
            row3.setHeight((short) (30 * 16));
            //第三行赋值
            row3.createCell(0).setCellValue(employees.get(i).getId());  //ID
            row3.createCell(1).setCellValue(employees.get(i).getEmpName());  //name
            row3.createCell(2).setCellValue(employees.get(i).getEmpAge()); //age
            row3.createCell(3).setCellValue(employees.get(i).getEmpSex());  //sex
            row3.createCell(4).setCellValue(employees.get(i).getEmpPost());  //职位
            row3.createCell(5).setCellValue(employees.get(i).getSection().getSection_name());  //部门名称
            for (int j = 0; j < s.length; j++) {
                row3.getCell(j).setCellStyle(cellStyle3);
            }
        }
        try {

            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = df.format(date);

            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(time + "北京***科技有限公司职员信息表", "UTF-8") + ".xls");

            OutputStream outputStream = response.getOutputStream();

            wb.write(outputStream);

            outputStream.flush();

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //所有用户信息
    @RequestMapping("/echartsEmp")
    @ResponseBody
    @Scheduled(fixedDelay = 5 * 1000)  //第一个任务完成后五秒进行资源调度
    public Map<String, List<Integer>> optionUserCount() {
        BaseNorms baseNorms = new BaseNorms();
        List<Integer> men = employeesService.selectSexCount("男");
        List<Integer> woMen = employeesService.selectSexCount("女");

       return baseNorms.setEchartsResult(men, woMen);
    }


}
