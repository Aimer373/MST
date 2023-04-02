package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.EmpProjectRela;

/**
 * @ClassName: EmpProjectRelaMapper
 * @Description: 任务Mapper
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:27:58
 */
@Mapper
public interface EmpProjectRelaMapper {

	/**
	 * @Title: insert
	 * @Description: 任务信息追加
	 * @param: @param empProjectRela 任务对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insert(EmpProjectRela empProjectRela);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 任务信息删除
	 * @param: @param taskId 任务编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int deleteByPrimaryKey(String taskId);

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 任务信息修改
	 * @param: @param empProjectRela 任务对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(EmpProjectRela empProjectRela);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 任务信息取得
	 * @param: @param taskId 任务编号
	 * @param: @return 返回任务对象
	 * @return: EmpProjectRela
	 */
	EmpProjectRela findByPrimaryKey(String taskId);

	/**
	 * @Title: findByEmpId
	 * @Description: 任务信息取得
	 * @param: @param empId 员工编号
	 * @param: @return 返回任务集合
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByEmpId(String empId);

	/**
	 * @Title: findByProNum
	 * @Description: 任务信息取得
	 * @param: @param proNum 职位编号
	 * @param: @return 返回任务集合
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByProNum(String proNum);

	/**
	 * @Title: findAllEmpProjectRelas
	 * @Description: 全部任务信息取得
	 * @param: @return 返回任务集合
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findAllEmpProjectRelas();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 任务信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 返回任务集合
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByEmpIdLike(String Id);

}