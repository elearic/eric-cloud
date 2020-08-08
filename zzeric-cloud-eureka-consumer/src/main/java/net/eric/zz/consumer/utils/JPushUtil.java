package net.eric.zz.consumer.utils;


import cn.hutool.core.collection.CollUtil;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import cn.jpush.api.report.MessagesResult;
import cn.jpush.api.report.ReportClient;
import cn.jpush.api.schedule.ScheduleResult;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.eric.zz.consumer.param.JPushParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


/**
 * Created by zwt09 on 2019/3/21.
 */
@Slf4j
public class JPushUtil {

    private static Logger logger = LoggerFactory.getLogger(JPushUtil.class);

    private String masterSecret;
    private String appKey;

    private JPushClient jPushClient = null;

    private ReportClient reportClient = null;


    public JPushUtil(String masterSecret, String appKey) {
         this.masterSecret = masterSecret;
         this.appKey = appKey;
         //极光统计
         this.reportClient = new ReportClient(masterSecret,appKey);
    }

    /**
     * jpush 通知业务类型code集合
     */
    private static final List<Integer> JPUSH_BIZ_TYPE_NOTIFY_LIST = Arrays.asList(6,7,8,9,10,11,12,13,18,19);
    /**
     * jpush 消息业务类型code集合
     */
    private static final List<Integer> JPUSH_BIZ_TYPE_MESSAGE_LIST = Arrays.asList(14);

    /**
     * 实时推送消息
     * @param jPushParam
     * @return
     */
    public PushResult pushMsg(JPushParam jPushParam){
        if (null == jPushClient) {
            jPushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        }
        PushResult result = null;
        try {
            //极光别名推送单笔最大支持1000条
            if (CollUtil.isNotEmpty(jPushParam.getAliasList())){
                List<String> aliasList = jPushParam.getAliasList();
                List<List<String>> subAliasList = Lists.partition(aliasList, 1000);
                for (List<String> list : subAliasList){
                    jPushParam.setAliasList(list);
                    PushPayload pushPayload = buildPayload(jPushParam);
                    logger.info("【JPushUtil.pushMsg , PushPayload:{}】极光推送入参:{}", JSON.toJSONString(pushPayload));
                    result = jPushClient.sendPush(pushPayload);
                    logger.info("【JPushUtil.pushMsg , result:{}】极光推送返回结果:{}", JSON.toJSONString(result));
                }
            }else {
                PushPayload pushPayload = buildPayload(jPushParam);
                logger.info("极光推送,参数，PushPayload:{}", JSON.toJSONString(pushPayload));
                result = jPushClient.sendPush(pushPayload);
                logger.info("极光推送返回结果:{}", JSON.toJSONString(result));
            }
        } catch (Exception e) {
            logger.error("极光API连接异常", e);
        } finally {
            jPushClient.close();
            return result;
        }
    }


    /**
     *  创建极光推送对象
     * @param jPushParam
     * @return
     */
    public PushPayload buildPayload(JPushParam jPushParam){
        String pnsProduction = jPushParam.getPnsProduction();
        Integer platform = jPushParam.getPlatform();
        String content = jPushParam.getContent();
        String title = jPushParam.getTitle();
        Integer businessType = jPushParam.getBusinessType();
        String pushTime = jPushParam.getPushTime();
        Integer all = jPushParam.getAll();
        List<String> aliasList = jPushParam.getAliasList();
        List<String> tagAndList = jPushParam.getTagAndList();
        List<String> tagList = jPushParam.getTagList();
        Map<String,String> result = new HashMap<>();
        result.put("pushTime",pushTime);
        result.put("businessType",businessType.toString());
        result.put("data", jPushParam.getData());
        String msg = JSON.toJSONString(result);
        boolean pnsFlag = false;
        if("1".equals(pnsProduction)){
            pnsFlag = true;
        }
        PushPayload.Builder builder = PushPayload.newBuilder();
        if(ObjectUtils.equals(platform,1)){//android
            builder.setPlatform(Platform.android());
            Map map = new HashMap();
            map.put("from",msg);
            builder.setNotification(Notification.android(content, title, map));
        }else if(ObjectUtils.equals(platform,2)){ //iOS
            builder.setPlatform(Platform.ios());
            builder.setNotification(Notification.newBuilder()
                    .addPlatformNotification(IosNotification.newBuilder()
                            .setAlert(content)
                            .addExtra("from", msg)
                            .build())
                    .build());
        }else if(ObjectUtils.equals(platform,3)){ //iOS & android
            builder.setPlatform(Platform.all());
            Notification.Builder builder1 = Notification.newBuilder();
            builder1.addPlatformNotification(
                IosNotification.newBuilder().setAlert(content).addExtra("from", msg).build());
            builder1.addPlatformNotification(
                AndroidNotification.newBuilder().setAlert(content).addExtra("from", msg).build());
            builder1.addPlatformNotification(
                WinphoneNotification.newBuilder().setAlert(content).addExtra("from", msg).build());
            builder.setNotification(builder1.build());
        }
        if(CollectionUtils.isNotEmpty(aliasList)){
            builder.setAudience(Audience.alias(aliasList));
        }else if(CollectionUtils.isNotEmpty(tagList) && CollectionUtils.isEmpty(tagAndList)){
            builder.setAudience(Audience.tag(tagList));
        }else if(CollectionUtils.isNotEmpty(tagAndList) && CollectionUtils.isEmpty(tagList)){
            builder.setAudience(Audience.tag_and(tagAndList));
        }else if(CollectionUtils.isNotEmpty(tagList) && CollectionUtils.isNotEmpty(tagAndList)){
            builder.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tagList)).
                    addAudienceTarget(AudienceTarget.tag_and(tagAndList)).build());
            //builder.setAudience(Audience.tag(tagList)).setAudience(Audience.tag_and(tagAndList));
        }else if(ObjectUtils.equals(all,1)){
            builder.setAudience(Audience.all());
        }

        builder.setMessage(Message.content(msg));

        wrapJpushParam(jPushParam,builder);

        log.info("builder,,,{}",JSON.toJSONString(builder));
        builder.setOptions(Options.newBuilder()
                .setApnsProduction(pnsFlag)
                .build());
        log.info("builder,,{}",JSON.toJSONString(builder));

        PushPayload build = builder.build();
        log.info("builder,{}",JSON.toJSONString(builder));

        return build;
    }

    /**
     * 根据业务类型判断推送为通知还是消息，充值消息或通知的内容体
     * @param jPushParam
     * @param builder
     */
    private void wrapJpushParam(JPushParam jPushParam,PushPayload.Builder builder){
        if (JPUSH_BIZ_TYPE_MESSAGE_LIST.contains(jPushParam.getBusinessType())){
            builder.setNotification(null);
        }
        if (JPUSH_BIZ_TYPE_NOTIFY_LIST.contains(jPushParam.getBusinessType())){
            builder.setMessage(null);
        }
    }






    /**
     * 定时推送消息
     * @param jPushParam
     * @return
     */
    public ScheduleResult pushScheduleMsg(JPushParam jPushParam){
        if (null == jPushClient) {
            jPushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        }
        PushPayload pushPayload = buildPayload(jPushParam);
        ScheduleResult result = null;
        try {
            result = jPushClient.createSingleSchedule(jPushParam.getTitle(), jPushParam.getScheduleTime(), pushPayload);
            logger.info(JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("极光API连接异常", e);
        } finally {
            jPushClient.close();
            return result;
        }
    }


    public MessagesResult getReceivedResult(String msgId){
        MessagesResult messagesResult = new MessagesResult();
        try {
            messagesResult = reportClient.getMessages(msgId);
            return messagesResult;
        }catch (Exception e){
            logger.error("根据消息ID获取推送统计信息失败",e);
            return messagesResult;
        }
    }



    public static void main(String[] args) {


        JPushUtil jPushUtil = new JPushUtil("160312ba2cf405ac60e4d925","09874ff602ab3b936bdb5229");
        jPushUtil.jPushClient = new JPushClient("160312ba2cf405ac60e4d925", "09874ff602ab3b936bdb5229", null, ClientConfig.getInstance());

        try {
            List<String[]> data = CsvUtil.getTestData("/Users/zz_huns/Desktop/1031/anhui.csv");
            List<String> newIds = Lists.newArrayList();
            List<String> userIds = new ArrayList();

            data.stream().forEach(itemArr -> {
                userIds.add(itemArr[0].toString());
            });

            userIds.stream().forEach(t -> {
                String desUserId = SecurityUtil.encryptDes(t);
                String md5UserId = Md5Util.MD5Encode(desUserId, "UTF-8");
                newIds.add(md5UserId);

                try {
                    AliasDeviceListResult aliasDeviceList = jPushUtil.jPushClient.getAliasDeviceList(md5UserId, "android,ios");
                    log.info("[user_id:{}],[md5userId:{}],[别名:{}]",t,md5UserId,JSON.toJSONString(aliasDeviceList.registration_ids));
                } catch (APIConnectionException e) {
                    e.printStackTrace();
                } catch (APIRequestException e) {
                    e.printStackTrace();
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public AliasDeviceListResult getAliasDeviceList(String alias) throws APIConnectionException, APIRequestException {
        if (null == jPushClient) {
            jPushClient = new JPushClient("160312ba2cf405ac60e4d925", "09874ff602ab3b936bdb5229", null, ClientConfig.getInstance());
        }
        return jPushClient.getAliasDeviceList(alias,"android,ios");

    }


}
