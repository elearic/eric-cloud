package net.eric.zz.consumer.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by zwt09 on 2019/3/28.
 */
@Data
@ApiModel("极光推送请求参数")
public class JPushParam {
    @ApiModelProperty("别名集合")
    private List<String> aliasList;
    @ApiModelProperty("tag or集合")
    private List<String> tagList;
    @ApiModelProperty("tag and集合")
    private List<String> tagAndList;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("消息内容")
    private String content;
    @ApiModelProperty("业务类型 1-模考 2-真题 3-课程(根据需求自己设计跳转详情,直播,录播) 4-打卡 5-推广web 如果不跳转页面 默认传0")
    private Integer businessType;
    @ApiModelProperty("推送时间")
    private String pushTime;
    @ApiModelProperty("发送平台 1-android 2-ios 3-android&iOS")
    private Integer platform;
    @ApiModelProperty("是否是全部 0-否 1-是")
    private Integer all;
    @ApiModelProperty("0-开发测试环境 1-正式环境 ")
    private String pnsProduction;
    @ApiModelProperty("定时推送消息的定时时间")
    private String scheduleTime;
    @ApiModelProperty("业务ID")
    private Long bizRefId;
    @ApiModelProperty("趣打卡挑战业务类型")
    private Integer challengeBizType;
    @ApiModelProperty("趣打卡期数")
    private Long challengePeriods;
    @ApiModelProperty("业务参数")
    private String data;
}
