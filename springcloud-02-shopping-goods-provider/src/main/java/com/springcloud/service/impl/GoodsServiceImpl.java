package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.springcloud.common.PageUtils;
import com.springcloud.dao.GoodsMapper;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;

/** 商品模型层的实现类，用于实现商品模块的方法
 * @author XY
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	
	//调用持久化层的方法，创建持久化层的对象
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Transactional//事务
	@Override
	public Integer insert(Goods goods) {
		return this.goodsMapper.insert(goods);
	}

	@Override
	public PageInfo<Goods> select(Goods goods,Integer pageNumber) {
		//商品名称两端加%%
		goods.setGoodName("%" + goods.getGoodName() + "%");
		//设置每页的信息
		//,PageUtils.PAGE_ROW_COUNT每页显示的个数
		PageHelper.startPage(pageNumber + 1,12);//'PageUtils.PAGE_ROW_COUNT'替换为12
		//查询满足条件的商品信息
		List<Goods> list = this.goodsMapper.select(goods);
		//返回分页信息
		return new PageInfo<>(list);
	}

	@Transactional//事务
	@Override
	public Integer updateById(Goods goods) {
		return this.goodsMapper.updateGoodsById(goods);
	}

	@Override
	public Integer update(Goods goods) {
		return this.goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public List<Goods> selectGroup() {
		return this.goodsMapper.selectGroup();
	}
	


}
