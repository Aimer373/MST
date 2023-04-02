package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.mts.dao.EmpProjectRelaMapper;
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.service.EmpProjectRelalService;

/**
 * @ClassName: EmpProjectRelaServiceImpl
 * @Description: 任务Service实现类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:33:48
 */
@Service
public class EmpProjectRelaServiceImpl implements EmpProjectRelalService {
	@Autowired
	EmpProjectRelaMapper empProjectRelaMapper;

	/**
	 * @Title: insertEmpProRela
	 * @Description: 任务信息追加
	 * @param: @param empProjectRela 任务对象
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#insertEmpProRela(com.sony.mts.entity.EmpProjectRela)
	 */
	@Override
	public int insertEmpProRela(EmpProjectRela empProjectRela) {
		return empProjectRelaMapper.insert(empProjectRela);
	}

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 任务信息删除
	 * @param: @param taskId 任务编号
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String taskId) {
		return empProjectRelaMapper.deleteByPrimaryKey(taskId);
	}

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 任务信息更新
	 * @param: @param empProjectRela 任务对象
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#updateByPrimaryKey(com.sony.mts.entity.EmpProjectRela)
	 */
	@Override
	public int updateByPrimaryKey(EmpProjectRela empProjectRela) {
		return empProjectRelaMapper.updateByPrimaryKey(empProjectRela);
	}

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 任务信息取得
	 * @param: @param taskId 任务编号
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#findByPrimaryKey(java.lang.String)
	 */
	@Override
	public EmpProjectRela findByPrimaryKey(String taskId) {
		return empProjectRelaMapper.findByPrimaryKey(taskId);
	}

	/**
	 * @Title: findByEmpId
	 * @Description: 任务信息取得
	 * @param: @param empId 员工编号
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#findByEmpId(java.lang.String)
	 */
	@Override
	public List<EmpProjectRela> findByEmpId(String empId) {
		return empProjectRelaMapper.findByEmpId(empId);
	}

	/**
	 * @Title: findByProNum
	 * @Description: 任务信息取得
	 * @param: @param proNum 项目编号
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#findByProNum(java.lang.String)
	 */
	@Override
	public List<EmpProjectRela> findByProNum(String proNum) {
		return empProjectRelaMapper.findByProNum(proNum);
	}

	/**
	 * @Title: findAllEmpProjectRelas
	 * @Description: 全部任务信息取得
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#findAllEmpProjectRelas()
	 */
	@Override
	public List<EmpProjectRela> findAllEmpProjectRelas() {
		return empProjectRelaMapper.findAllEmpProjectRelas();
	}

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 任务信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return
	 * @see com.sony.mts.service.EmpProjectRelalService#findByEmpIdLike(java.lang.String)
	 */
	@Override
	public List<EmpProjectRela> findByEmpIdLike(String Id) {
		return empProjectRelaMapper.findByEmpIdLike(Id);
	}

}
