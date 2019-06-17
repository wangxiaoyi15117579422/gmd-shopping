package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;


public interface OrderDetailService {
	
	/**订单明细模块的模型层，用于定义订单明细模块的方法
	 * @param orderId 订单编号
	 * @param pageNumber 页数
	 * @return 返回com.github.pagehelper.PageInfo<OrderDetail>类型的实例
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);

}
