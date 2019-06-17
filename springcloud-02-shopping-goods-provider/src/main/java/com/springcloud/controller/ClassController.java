package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;

/**
 * һ������������� �Ŀ�����
 * 
 * @author XY
 *
 */
@RestController
@RequestMapping("class")
public class ClassController {

	@Autowired
	private ClassService classService;

	@RequestMapping(value = "/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();

		try {
			// ����service��Ӧ�ķ�����ѯ����һ��������Ϣ���������ѯ�Ľ��
			List<Class1> class1 = this.classService.selectAllClass1();
			// �����ѯ�ɹ�
			if (class1 != null && class1.size() > 0) {
				// ���ý����״̬���Ϊ0
				rv.setCode(0);
				// ����Map����
				Map<String, Object> map = new HashMap<>();
				// ����ѯ�������Map������
				map.put("class1List", class1);
				// ��Map���ϴ���ResultValue������
				rv.setDataMap(map);
				// ����ResultValue����
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		return rv;

	}

	/**
	 * ����һ�����ı�Ų�ѯ��Ӧ�Ķ���������Ϣ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectById")
	//@RequestParam:���ڽ�ָ�������������ֵ�������е��β�
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();

		try {
			// ����service��Ӧ�ķ�����ѯ����һ��������Ϣ���������ѯ�Ľ��
			List<Class2> class2 = this.classService.selectClass2ByClass1Id(class1Id);
			// �����ѯ�ɹ�
			if (class2 != null && class2.size() > 0) {
				// ���ý����״̬���Ϊ0
				rv.setCode(0);
				// ����Map����
				Map<String, Object> map = new HashMap<>();
				// ����ѯ�������Map������
				map.put("class2List", class2);
				// ��Map���ϴ���ResultValue������
				rv.setDataMap(map);
				// ����ResultValue����
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		return rv;

	}

}
