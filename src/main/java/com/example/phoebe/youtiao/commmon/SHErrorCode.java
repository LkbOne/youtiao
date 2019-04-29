package com.example.phoebe.youtiao.commmon;

public enum SHErrorCode {
	
	// 成功
	SUCCESS(0, "成功"),
	FAILURE(1, "失败"),
	// 错误
	PARAMS_ERROR(-1, "参数错误"),
	SERVER_BUSY(-2, "系统繁忙"),
	SYSTEM_ERROR(-3, "系统错误"),
	OPERATE_DB_FAIL(-4, "数据库操作失败"),
	UNKNOWN(-5, "未知错误"),
	JSON_FAIL(-6, "JSON序列化失败"),
	NO_DATA(-7, "数据不存在"),
	PHONE_NUMBER_ILLEGAL(-8, "手机号码不合法"),
	SEND_PHONE_SMS_TOO_FREQUENTLY(-9, "发送短信太频繁"),
	NOT_APP_MANAGER(-10, "用户不是应用管理员"),
	NO_AUTH(-11, "暂未在灰度范围内,敬请期待"),
	JSON_DESERIALIZATION_FAIL(-12, "JSON反序列化失败"),
	THIRD_APPLICATION_ERROR(-13, "请求第三方响应异常"),
	ADD_FAIL(-14, "添加失败"),
	UPDATE_FAIL(-15, "更新失败"),
	DEL_FAIL(-16, "删除失败"),
	USER_ACCOUNT_NOT_EXIST(-17, "用户不存在"),
	USER_ACCOUNT_EXISTED(-18, "用户已经存在"),
	CHANGE_PASSWORD_FAIL(-19, "修改密码失败"),
	ACCOUNT_EXIST(-20, "账户已存在"),

	// 預算

	MORE_THAN_TOTAL_BUDGET(101, "预算金额超過总预算"),
	NO_IN_TOTAL_BUDGET_TIME(102, "开始与结束时间不位于预算時間区间内"),
	EXIST_TOTAL_BUDGET(103,"该账本存在总预算"),
	HAS_MIXED_THIS_CLASSIFICATION_BUDGET_TIME_INTERVAL(104, "与该种类的预算有时间重叠"),
	LESS_THAN_TOTAL_BUDGET(105, "预算金额低于总预算"),
	LESS_THAN_TOTAL_BUDGET_TIME(106, "预算开始时间太早"),
	MORE_THAN_TOTAL_BUDGET_TIME(107, "预算结束时间太晚"),
	// 微信(1000-1999)
	REQUEST_WX_FAILED(1000, "获取微信后台数据失败"),
	WX_RESPONSE_DATA_ERROR(1001, "微信后台数据错误"),
	DECRYPT_FAILED(1002, "解密失败"),
	WX_USER_NOFOUND(1003, "相关微信用户数据获取失败"),
	QRCODE_NOFOUND(1004, "找不到该小程序二维码"),
	
	// 小程序登录(2000-2999)
	LOGIN_TOKEN_INVALID(2000, "token无效"),
	LOGIN_NEED_RELOGIN(2001, "需要重新登录"),
	CHECK_LOGIN_PHONE_MESSAGE_FAILED(2002, "验证码错误"),
	LOGIN_BLACK_LIST(2003, "本账号已被禁用"),
	REGISTER_NEED_AGAIN(2004, "需要重新注册"),
	LOGIN_FAIL(2005, "登录失败"),


	// 图片
	TRANSFER_TPATH_FAIL(2800,"获取TPath失败"),
	TRANSFER_FPATH_FAIL(2801,"获取FPath失败");
	;


	SHErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	private int errorCode;
	private String errorMessage;
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static SHErrorCode getByCode(int code) {
		for (SHErrorCode shErrorCode : values()) {
			if (shErrorCode.getErrorCode() == code) {
				return shErrorCode;
			}
		}
		return null;
	}
}
