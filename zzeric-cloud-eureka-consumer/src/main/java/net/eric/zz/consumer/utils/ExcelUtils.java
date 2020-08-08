/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package net.eric.zz.consumer.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * excel工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ExcelUtils {

    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                     Class<?> pojoClass,String title) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = System.currentTimeMillis() + "";
        }
        
        ExportParams ep =  new ExportParams();
//        ep.setTitle(title);
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        Workbook workbook = ExcelExportUtil.exportExcel(ep, pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                     Class<?> targetClass,String title) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass,title);
    }


    public static void createExcel(Map<String, List<String>> map, String[] strArray,String exportPath) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);

        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
        int i = 0;
        for (String str : map.keySet()) {
            row = sheet.createRow((int) i + 1);
            List<String> list = map.get(str);

            // 第四步，创建单元格，并设置值
            for (int j = 0; j < strArray.length; j++) {
                row.createCell((short) j).setCellValue(list.get(j));
            }

            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream(exportPath);
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
