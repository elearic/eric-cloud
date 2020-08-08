package net.eric.zz.consumer.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.eric.zz.consumer.dto.UserLoginInfoDTO;
import net.eric.zz.consumer.utils.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: JPushData.java, v 0.1 2020/6/2 1:13 PM zz_huns Exp $$
 *
 */
public class JPushData {


    public static void main1(String[] args) {
        JPushData jPushData = new JPushData();

        String path1 = "/Users/zz_huns/Desktop/1031/push/3.json";
        String path2 = "/Users/zz_huns/Desktop/1031/push/4.json";
        String path3 = "/Users/zz_huns/Desktop/1031/push/7.json";


        String exportPath1 = "/Users/zz_huns/Desktop/1031/push/3.xls";
        String exportPath2 = "/Users/zz_huns/Desktop/1031/push/4.xls";
        String exportPath3 = "/Users/zz_huns/Desktop/1031/push/7.xls";


//        ExportThread exportThread1 = new ExportThread(path1,exportPath1,jPushData);
//        ExportThread exportThread2 = new ExportThread(path2,exportPath2,jPushData);
        ExportThread exportThread3 = new ExportThread(path3,exportPath3,jPushData);

//        Thread t1 = new Thread(exportThread1);
//        Thread t2 = new Thread(exportThread2);
        Thread t3 = new Thread(exportThread3);

//        t1.start();
//        t2.start();
        t3.start();

        try {
//            t1.join();
//            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class ExportThread implements Runnable{

        private String path;

        private String exportPath;

        private JPushData jPushData;


        public ExportThread(String path,String exportPath,JPushData jPushData){
            this.path = path;
            this.exportPath = exportPath;
            this.jPushData = jPushData;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            jPushData.exportData(path,exportPath);
        }
    }

    private void exportData(String path,String exportPath){
        JPushUtil jPushUtil = new JPushUtil("160312ba2cf405ac60e4d925","09874ff602ab3b936bdb5229");


        JSONObject root = JSONObject.parseObject(getJson(path));
        Object records = root.get("RECORDS");
        JSONArray jsonArray = JSONArray.parseArray(records.toString());
        List<Object> objects = jsonArray.toJavaList(Object.class);


        List<UserLoginInfoDTO> uInfoList = new LinkedList<>();
        for (Object object : objects) {
            JSONObject object1 = JSONObject.parseObject(object.toString());
            Object user_id = object1.get("user_id");
            Object user_name = object1.get("user_name");
            Object password = object1.get("password");
            Object state = object1.get("state");
            Object register_time = object1.get("register_time");
            Object register_type = object1.get("register_type");
            Object user_status = object1.get("user_status");
            Object channel_name = object1.get("channel_name");
            Object bind_phone = object1.get("bind_phone");
            Object biz_type = object1.get("biz_type");
            Object inviter_id = object1.get("inviter_id");


            UserLoginInfoDTO dto = new UserLoginInfoDTO();
            dto.setUserId(user_id.toString());
            dto.setUserName(user_name.toString());
            dto.setPassword(password.toString());
            dto.setState(state.toString());
            dto.setRegisterTime(register_time.toString());
            dto.setRegisterType(register_type.toString());
            dto.setUserStatus(user_status.toString());
            dto.setChannelName(channel_name.toString());
            dto.setBindPhone(bind_phone.toString());
            dto.setBizType(biz_type.toString());
            dto.setInviterId(inviter_id.toString());


            String desUserId = SecurityUtil.encryptDes(user_id.toString());
            String md5UserId = Md5Util.MD5Encode(desUserId, "UTF-8");

            try {
                AliasDeviceListResult aliasDeviceList = jPushUtil.getAliasDeviceList(md5UserId);

                if (null != aliasDeviceList){
                    List<String> registration_ids = aliasDeviceList.registration_ids;
                    dto.setRegId(registration_ids.toString());
                }

            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (APIRequestException e) {
                e.printStackTrace();
            }
            uInfoList.add(dto);
        }
        ExcelUtils.createExcel(DownExcel.getUserLoginInfo(uInfoList), DownExcel.excelUserLoginInfoTitle(),exportPath);
    }



    public static String getJson(String path) {
        String jsonStr = "";
        try {
            File file = new File(path);
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


    public static void main(String[] args) {
        System.out.println("==="+System.getProperty("java.io.tmpdir") );
    }


}
