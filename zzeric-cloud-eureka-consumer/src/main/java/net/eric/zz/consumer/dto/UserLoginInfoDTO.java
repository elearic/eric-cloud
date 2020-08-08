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
public class UserLoginInfoDTO {

	@Excel(name = "user_id")
	private String userId;

	@Excel(name = "user_name")
	private String userName;

	@Excel(name = "password")
	private String password;

	@Excel(name = "state")
	private String state;

	@Excel(name = "register_time")
	private String registerTime;

	@Excel(name = "register_type")
	private String registerType;

	@Excel(name = "channel_name")
	private String channelName;

	@Excel(name = "user_status")
	private String userStatus;

	@Excel(name = "bind_phone")
	private String bindPhone;

	@Excel(name = "biz_type")
	private String bizType;

	@Excel(name = "inviter_id")
	private String inviterId;

	@Excel(name = "reg_id")
	private String regId;
}