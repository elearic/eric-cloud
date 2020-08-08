package net.eric.zz.consumer.utils;

import net.eric.zz.consumer.dto.DuoBeiExportExcelDTO;
import net.eric.zz.consumer.dto.JpushDTO;
import net.eric.zz.consumer.dto.UserLoginInfoDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建excel，写入数据，然后下载到本地
 */
public class DownExcel {


    /**
     * 初始化数据，将会被存储到excel表格中
     *
     * @return
     * @throws Exception
     */
    public static Map<String, List<String>> getData(List<DuoBeiExportExcelDTO> duoBeiExportExcelDTOList) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        for (int i = 0; i < duoBeiExportExcelDTOList.size(); i++) {
            ArrayList<String> members = new ArrayList<String>();
            members.add(duoBeiExportExcelDTOList.get(i).getCoureName() + "");
            members.add(duoBeiExportExcelDTOList.get(i).getScheduleName());
            members.add(duoBeiExportExcelDTOList.get(i).getTeacherName() + "");
            members.add(duoBeiExportExcelDTOList.get(i).getStartTime() + "");
            members.add(duoBeiExportExcelDTOList.get(i).getEndTime() + "");
            members.add(duoBeiExportExcelDTOList.get(i).getRoomId() + "");

            members.add(duoBeiExportExcelDTOList.get(i).getMax());
            members.add(duoBeiExportExcelDTOList.get(i).getTottal());

            map.put(duoBeiExportExcelDTOList.get(i) + "", members);
        }

        return map;
    }


    /**
     * 初始化数据，将会被存储到excel表格中
     *
     * @return
     * @throws Exception
     */
    public static Map<String, List<String>> getUserLoginInfo(List<UserLoginInfoDTO> userLoginInfoDTOList) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        for (int i = 0; i < userLoginInfoDTOList.size(); i++) {
            ArrayList<String> members = new ArrayList<>();
            members.add(userLoginInfoDTOList.get(i).getUserId());
            members.add(userLoginInfoDTOList.get(i).getUserName());
            members.add(userLoginInfoDTOList.get(i).getPassword());
            members.add(userLoginInfoDTOList.get(i).getState());
            members.add(userLoginInfoDTOList.get(i).getRegisterTime());
            members.add(userLoginInfoDTOList.get(i).getRegisterType());
            members.add(userLoginInfoDTOList.get(i).getChannelName());
            members.add(userLoginInfoDTOList.get(i).getUserStatus());
            members.add(userLoginInfoDTOList.get(i).getBindPhone());
            members.add(userLoginInfoDTOList.get(i).getBizType());
            members.add(userLoginInfoDTOList.get(i).getInviterId());
            members.add(userLoginInfoDTOList.get(i).getRegId());

            map.put(userLoginInfoDTOList.get(i) + "", members);
        }

        return map;
    }


    /**
     * 初始化数据，将会被存储到excel表格中
     *
     * @return
     * @throws Exception
     */
    public static Map<String, List<String>> getJpushDTOList(List<JpushDTO> jpushDTOList) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        for (int i = 0; i < jpushDTOList.size(); i++) {
            ArrayList<String> members = new ArrayList<>();
            members.add(jpushDTOList.get(i).getRegId());
            members.add(jpushDTOList.get(i).getAlias());
            map.put(jpushDTOList.get(i) + "", members);
        }

        return map;
    }


    /**
     * 创建excel title
     */
    public static String[] excelUserLoginInfoTitle() {
        String[] strArray = {"user_id", "user_name", "password", "state", "register_time", "register_type", "channel_name", "user_status","bind_phone","biz_type","inviter_id","reg_id"};
        return strArray;
    }

    /**
     * 创建excel title
     */
    public static String[] excelTitle() {
        String[] strArray = {"课程", "课时", "老师", "开始时间", "结束时间", "班级号", "最大", "总共"};
        return strArray;
    }

    /**
     * 极光regId 与 alias 别名绑定关系
     * @return
     */
    public static String[] excelJpushTital(){
        String[] strArray = {"reg_id","alias"};
        return strArray;
    }
}
