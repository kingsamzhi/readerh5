package com.show.sign.entity;


import com.show.sign.utils.JsonUtils;

/**
 * 返回分页数据对象 
 * @author xianyl
 * @date 2020年2月11日
 */
public class Result{
	/**操作码，正确**/
	public final static String CODE_SUCCESS = "0";	
	/**操作码，错误**/
	public final static String CODE_ERROR = "1";
	
	private String code;//错误编码
	private String msg;//消息
	private Long count = 0l;//记录总数
	//private Integer totalPage = 0;//总页数
	//private Integer page = 1;//当前页
	//private Integer current = 0;//当前数据数
	
	private Object data;//返回的数据
	


	public static Result of(String code, String msg, Long count, Object data) {
		Result pageData = new Result();
		pageData.setCode(code);
		pageData.setMsg(msg);
		pageData.setCount(count);
		pageData.setData(data);
		return pageData;
	}
	
	
	public static Result success(Object data) {
		return Result.success("操作成功", data);
	}
	public static Result success(String msg, Object data) {
		return Result.success(msg, 0l, data);
	}
	public static Result success(String msg, Long count, Object data) {
		return Result.of(CODE_SUCCESS, msg, count, data);
	}
	
	public static Result error(String msg) {
		return Result.error(msg, null);
	}
	public static Result error(String msg, Object data) {
		return Result.error(msg, 0l, data);
	}
	public static Result error(String msg, Long count, Object data) {
		return Result.of(CODE_ERROR, msg, count, data);
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson(){
		return JsonUtils.toJson(this);
	}

}
