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

/** ��Ʒģ�Ͳ��ʵ���࣬����ʵ����Ʒģ��ķ���
 * @author XY
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	
	//���ó־û���ķ����������־û���Ķ���
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Transactional//����
	@Override
	public Integer insert(Goods goods) {
		return this.goodsMapper.insert(goods);
	}

	@Override
	public PageInfo<Goods> select(Goods goods,Integer pageNumber) {
		//��Ʒ�������˼�%%
		goods.setGoodName("%" + goods.getGoodName() + "%");
		//����ÿҳ����Ϣ
		//,PageUtils.PAGE_ROW_COUNTÿҳ��ʾ�ĸ���
		PageHelper.startPage(pageNumber + 1,12);//'PageUtils.PAGE_ROW_COUNT'�滻Ϊ12
		//��ѯ������������Ʒ��Ϣ
		List<Goods> list = this.goodsMapper.select(goods);
		//���ط�ҳ��Ϣ
		return new PageInfo<>(list);
	}

	@Transactional//����
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
