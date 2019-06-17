package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class2表对应的实体�?
 * 
 * @author XY
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class2 {
	

	/**
	 * 二级类别编号
	 */
	private Integer class2Id;

	/**
	 * 二级类别名称
	 */
	private String class2Name;

	/**
	 * �?级类�?
	 */
	private Integer class1Id;

	/**
	 * 备注
	 */
	private String remark;

	public Integer getClass2Id() {
		return class2Id;
	}

	public void setClass2Id(Integer class2Id) {
		this.class2Id = class2Id;
	}

	public String getClass2Name() {
		return class2Name;
	}

	public void setClass2Name(String class2Name) {
		this.class2Name = class2Name;
	}

	public Integer getClass1Id() {
		return class1Id;
	}

	public void setClass1Id(Integer class1Id) {
		this.class1Id = class1Id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}