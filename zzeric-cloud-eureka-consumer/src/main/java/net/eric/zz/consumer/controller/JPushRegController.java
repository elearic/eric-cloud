package net.eric.zz.consumer.controller;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.device.TagAliasResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.eric.zz.consumer.dto.JpushDTO;
import net.eric.zz.consumer.dto.UserLoginInfoDTO;
import net.eric.zz.consumer.utils.*;
import org.testng.collections.Lists;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: JPushData.java, v 0.1 2020/6/2 1:13 PM zz_huns Exp $$
 *
 */
public class JPushRegController {




    public static void main(String[] args) {

        JPushClient jPushClient = new JPushClient("160312ba2cf405ac60e4d925", "09874ff602ab3b936bdb5229", null, ClientConfig.getInstance());


        List<JpushDTO> jpushDTOList = Lists.newLinkedList();

        String exportPath = "/Users/zz_huns/Desktop/1031/0618-jpush.xls";


        /**
         * load 数据
         */
        List<String[]> data = null;
        try {
            data = CsvUtil.getTestData("/Users/zz_huns/Desktop/1031/0618.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> mmap = new HashMap<>();

        data.stream().forEach(itemArr -> {
            String regId = itemArr[0];
            regId = regId.substring(1,regId.length()-1);
            try {
                TagAliasResult deviceTagAlias = jPushClient.getDeviceTagAlias(regId);
                String alias = deviceTagAlias.alias;
                JpushDTO jpushDTO = new JpushDTO();
                jpushDTO.setRegId(regId);
                jpushDTO.setAlias(alias);
                jpushDTOList.add(jpushDTO);
            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (APIRequestException e) {
                e.printStackTrace();
            }
        });

        ExcelUtils.createExcel(DownExcel.getJpushDTOList(jpushDTOList), DownExcel.excelJpushTital(),exportPath);


    }

}
