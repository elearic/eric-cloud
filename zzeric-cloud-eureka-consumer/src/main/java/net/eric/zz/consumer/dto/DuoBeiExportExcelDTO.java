package net.eric.zz.consumer.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: DuoBeiExportExcelDTO.java, v 0.1 2020/3/9 7:44 PM zz_huns Exp $$
 *
 */
@Data
public class DuoBeiExportExcelDTO {

    @Excel(name = "课程")
    private String coureName;

    @Excel(name = "课时")
    private String scheduleName;

    @Excel(name = "老师")
    private String teacherName;

    @Excel(name = "开始时间")
    private String startTime;

    @Excel(name = "结束时间")
    private String endTime;

    @Excel(name = "班级ID")
    private String roomId;

    @Excel(name = "最大")
    private String max;

    @Excel(name = "总共")
    private String tottal;
}
