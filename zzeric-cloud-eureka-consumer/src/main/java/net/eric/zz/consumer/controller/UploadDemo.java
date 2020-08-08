package net.eric.zz.consumer.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.device.TagAliasResult;
import com.alibaba.fastjson.JSON;
import net.eric.zz.consumer.dto.JpushDTO;
import net.eric.zz.consumer.utils.CsvUtil;
import net.eric.zz.consumer.utils.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: UploadDemo.java, v 0.1 2020/6/19 6:54 PM zz_huns Exp $$
 *
 */
public class UploadDemo {

    public static void main(String[] args) {
        Map header = new HashMap<>();
        header.put("token","48d770e498b5c3e42fd92d335de5bm9dl29");
        header.put("Content-Type","application/json;charset=UTF-8");
        Map<String,String> body = new HashMap<>();

        List<String[]> data = null;
        try {
            data = CsvUtil.getTestData("/Users/zz_huns/Desktop/1031/0619-1.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> mmap = new HashMap<>();

        data.stream().forEach(itemArr -> {
            String goodsId = itemArr[0];
            String categoryId = itemArr[1];
            String subjectId = itemArr[2];
            String tagId = itemArr[3];

            body.put("id",null);
            body.put("categoryId",categoryId);
            body.put("goodsId",goodsId);
            body.put("subjectId",subjectId);
            body.put("subjectTagId",tagId);



            String post = HttpUtil.post("https://dls-api-v3.danglaoshi.info/manage/chili/subjecttaggoods", header, body);

            System.out.println("======="+post.toString());


        });
    }
}
