package com.springcloud.vo;

import java.util.Map;

import lombok.Data;

/**
 * 定义本项目所有controller返回的结果类型
 * @author XY
 *
 */
@Data
public class ResultValue implements java.io.Serializable{

	private static final long serialVersionUID = -7333058567132357663L;
	
	/**
	 *设置当前返回结果的状态：0表示成功，1表示失败
	 */
	private Integer code;
	
	/**
	 * 设置返回信息，显示失败原因
	 */
	private String message;
	
	/**
	 * 设置返回的数据
	 */
	private Map<String,Object> dataMap;

}
