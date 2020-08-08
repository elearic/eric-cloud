package common.utils;

import net.eric.zz.consumer.dto.DuoBeiExportExcelDTO;

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
     * 创建excel title
     */
    public static String[] excelTitle() {
        String[] strArray = {"课程", "课时", "老师", "开始时间", "结束时间", "班级号", "最大", "总共"};
        return strArray;
    }
}
