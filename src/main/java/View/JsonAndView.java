package View;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 刘杰
 * @time 2016�?2�?29�? 下午11:38:25
 * @description 接口接收请求后将结果放入此类，返回给请求者，返回形式为json格式字符�?
 */
public class JsonAndView implements Serializable {

	private static final long serialVersionUID = -8690687070450659428L;

	private Integer code = 0;// 错误代码�?0表示正确

	private String msg = "";// 错误信息

	private Map<String, Object> data;// 数据集合，键值对形式存在（注意：�?后得到的顺序可能不是加入的顺序）

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JsonAndView() {
	}

	/**
	 * 
	 * @param code
	 *            错误代码�?0表示正确
	 * @param message
	 *            错误信息
	 */
	public JsonAndView(Integer code, String message) {
		this.code = code;
		this.msg = message;
	}

	/**
	 * @param code
	 *            错误代码�?0表示正确
	 */
	public JsonAndView(Integer code) {
		this.code = code;
	}

	/**
	 * 
	 * @param msg
	 *            提示信息，注意此时code=0，仅用于设置正确返回后的提示信息
	 */
	public JsonAndView(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * @author LiuJie
	 * @time 2016�?2�?29�? 下午11:51:10
	 * @description 加入�?个数据，包括名字和具体对�?
	 * @param key
	 *            键名
	 * @param object
	 *            数据对象
	 * @return 返回仍是此JsonAndView
	 */
	public JsonAndView addData(String key, Object object) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.put(key, object);
		return this;
	}

	/**
	 * 
	 * @author LiuJie
	 * @time 2016�?2�?29�? 下午11:53:24
	 * @description 加入�?个Map里所有的数据
	 * @param objMap
	 *            �?要添加的map
	 * @return 返回仍是此JsonAndView
	 */
	public JsonAndView addAllData(Map<String, Object> objMap) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.putAll(objMap);
		return this;
	}

}
