package com.springcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.repository.UsersRepository;
import com.springcloud.service.UsersService;

/**
 * 模型层的实现类，用于实现用户模块的方法(调用持久化层repository中的方法)
 * 
 * @author XY
 *
 */
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Users login(Integer userId, String userPwd, Integer jdictionId) {
		return this.usersRepository.login(userId, userPwd, jdictionId);
	}

	@Transactional
	@Override
	public Users insert(Users users) {
		return usersRepository.save(users);
	}

	@Override
	public Page<Users> select(Users users, Integer pageNumber) {
		// 根据查询数据，创建动态条件
		@SuppressWarnings("serial")
		Specification<Users> specification = new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// 创建一个LIst集合，用于保存所有的where条件
				List<Predicate> whereList = new ArrayList<>();

				// 根据users实体类中的查询数据，动态创建查询条件
				if (users.getUserName() != null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"), "%" + users.getUserName() + "%"));
				}
				if (users.getUserStatus() != -1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				// 将所有的条件以and关系连接在一起，并返回
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}

		};
		// 创建JPA的分页信息
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);

		return this.usersRepository.findAll(specification, of);
	}

	@Transactional // 事务
	@Override
	public Integer updateStatus(Integer userId, Integer userStatus) {
		return this.usersRepository.updateStatus(userId, userStatus);
	}

	@Override
	public Users selectById(Integer userId) {
		return this.usersRepository.findById(userId).get();
	}

	@Transactional // 事务(增删改需要事务)
	@Override
	public Integer update(Users users) {
		return this.usersRepository.update(users);
	}

	@Transactional // 事务(增删改需要事务)
	@Override
	public Integer updateMessage(Users users) {
		if (users.getUserHphoto() != null && !users.getUserHphoto().equals("")) {
			return this.usersRepository.updateHphoto(users);
		} else if (users.getUserPwd() != null && !users.getUserPwd().equals("")) {
			return this.usersRepository.updateUserPwd(users);
		} else if (users.getUserName() != null && !users.getUserName().equals("")) {
			return this.usersRepository.updateUserName(users);
		}
		return 0;
	}

}
