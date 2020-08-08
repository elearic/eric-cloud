package net.eric.zz.consumer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.eric.zz.consumer.dto.DuoBeiExportExcelDTO;
import net.eric.zz.consumer.utils.DownExcel;
import net.eric.zz.consumer.utils.ExcelUtils;
import net.eric.zz.consumer.utils.HttpUtil;
import net.eric.zz.consumer.utils.Md5Util;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: DuoBeiExportData.java, v 0.1 2020/6/2 12:46 PM zz_huns Exp $$
 *
 */
public class DuoBeiExportData {

    public static void main(String[] args) throws FileNotFoundException {


        String url = "https://api.duobeiyun.com/api/v3/statistics/zhibo/room";


        Long time = System.currentTimeMillis() ;

        Map body = new HashMap<>();

        List<DuoBeiExportExcelDTO> duoBeiExportExcelDTOS = new LinkedList<>();

        JSONObject root = JSONObject.parseObject(getJson());
        Object records = root.get("RECORDS");
        JSONArray jsonArray = JSONArray.parseArray(records.toString());
        List<Object> objects = jsonArray.toJavaList(Object.class);

        for (Object object : objects) {
            JSONObject object1 = JSONObject.parseObject(object.toString());
            Object courseName = object1.get("课程");
            Object scheduleName = object1.get("课时");
            Object startTime = object1.get("开始时间");
            Object endTime = object1.get("结束时间");
            Object teacherName = object1.get("老师");
            Object rommId = object1.get("room_id");

            DuoBeiExportExcelDTO duoBeiExportExcelDTO = new DuoBeiExportExcelDTO();
            duoBeiExportExcelDTO.setCoureName(courseName.toString());
            duoBeiExportExcelDTO.setScheduleName(scheduleName.toString());
            duoBeiExportExcelDTO.setTeacherName(teacherName.toString());
            duoBeiExportExcelDTO.setStartTime(startTime.toString());
            duoBeiExportExcelDTO.setEndTime(endTime.toString());
            duoBeiExportExcelDTO.setRoomId(rommId.toString());

            String encode = "partner=20170314113946324198"+"&roomId="+rommId.toString()+"&timestamp="+time+"ad94e8a8bdc84db0b9ac3f339854442c";

            final String sign = Md5Util.MD5Encode(encode, "UTF-8");

            body.put("partner","20170314113946324198");
            body.put("sign",sign);
            body.put("timestamp",time+"");
            body.put("roomId",rommId);
//			body.put("start_time",startTime.toString());
//			body.put("end_time",endTime.toString());

            final String response = HttpUtil.post(url, null, body);

            JSONObject jsonObject = JSONObject.parseObject(response);


            Object data = jsonObject.get("roomZhiboDetail");
            if (null != data){
                JSONObject roomZhiboDetailJsonObject = JSONObject.parseObject(data.toString());
                Object peakUser = roomZhiboDetailJsonObject.get("statisticEntity");
                if (null != peakUser){
                    JSONObject jsonObject1 = JSONObject.parseObject(peakUser.toString());
                    Map map = jsonObject1.toJavaObject(Map.class);

                    duoBeiExportExcelDTO.setMax(map.get("maxStudents").toString());
                    duoBeiExportExcelDTO.setTottal(map.get("totalStudentNum").toString());
                }

            }
            duoBeiExportExcelDTOS.add(duoBeiExportExcelDTO);
        }

        ExcelUtils.createExcel(DownExcel.getData(duoBeiExportExcelDTOS), DownExcel.excelTitle(),"/Users/zz_huns/Desktop/1031/ss.xls");
    }


    public static String getJson() {
        String jsonStr = "";
        try {
            File file = new File("/Users/zz_huns/Desktop/1031/ss.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            return null;
        }
    }
}
