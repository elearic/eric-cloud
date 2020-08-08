package net.eric.zz.consumer.utils;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: CsvUtil.java, v 0.1 2019/9/23 12:32 AM zz_huns Exp $$
 *
 */
public class CsvUtil {

    public static List<String[]> getTestData(String fileName) throws IOException {
        List<String[]> records = Lists.newArrayList();
        String record;
        try (BufferedReader file = new BufferedReader(
            new InputStreamReader(new FileInputStream(fileName), "UTF-8"));) {
            //跳过表头所在的行
            file.readLine();
            // 遍历数据行并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
            while ((record = file.readLine()) != null) {
                String[] fields = record.split(",");
                records.add(fields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }



}
