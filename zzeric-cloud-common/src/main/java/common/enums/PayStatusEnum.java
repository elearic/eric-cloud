package net.ele.common.enums;

/** 
 * 支付状态枚举类
  * @author zz_huns  
 * @version Id: PayStatusEnum.java, v 0.1 2019/1/23 2:58 PM zz_huns Exp $$
  */
public enum PayStatusEnum {

    WAIT_PAY("00","待支付"),
    PAY_SUCCESS("01","支付成功"),
    PAY_FAILURE("02","支付失败"),
    PAY_FINISH("03","支付完成");

    String code;
    String desc;

    PayStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
