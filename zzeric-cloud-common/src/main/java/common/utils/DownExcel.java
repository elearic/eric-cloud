package common.utils;

/**
 * 创建excel，写入数据，然后下载到本地
 */
public class DownExcel {


//    /**
//     * 初始化数据，将会被存储到excel表格中
//     *
//     * @return
//     * @throws Exception
//     */
//    public static Map<String, List<String>> getData(List<TestDTO> testDTOList) {
//        Map<String, List<String>> map = new HashMap<String, List<String>>();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//
//        for (int i = 0; i < testDTOList.size(); i++) {
//            ArrayList<String> members = new ArrayList<String>();
//            members.add(testDTOList.get(i).getCoureName() + "");
//            members.add(testDTOList.get(i).getScheduleName());
//            members.add(testDTOList.get(i).getTeacherName() + "");
//            members.add(testDTOList.get(i).getStartTime() + "");
//            members.add(testDTOList.get(i).getEndTime() + "");
//            members.add(testDTOList.get(i).getRoomId() + "");
//
//            members.add(testDTOList.get(i).getMax());
//            members.add(testDTOList.get(i).getTottal());
//
//            map.put(testDTOList.get(i) + "", members);
//        }
//
//        return map;
//    }

    /**
     * 创建excel title
     */
    public static String[] excelTitle() {
        String[] strArray = {"课程", "课时", "老师", "开始时间", "结束时间", "班级号", "最大", "总共"};
        return strArray;
    }
}
