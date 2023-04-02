package com.sony.mts.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sony.mts.entity.EmpProjectRela;

/**
 * @ClassName: EmpProjectRelalService
 * @Description: 任务Service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:32:10
 */
public interface EmpProjectRelalService {
	/**
	 * @Title: insertEmpProRela
	 * @Description: 任务信息追加
	 * @param: @param empProjectRela 任务对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insertEmpProRela(EmpProjectRela empProjectRela);

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
	 * @Description: 任务信息更新
	 * @param: @param empProjectRela 任务对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(EmpProjectRela empProjectRela);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 任务信息取得
	 * @param: @param taskId 任务编号
	 * @param: @return 任务对象
	 * @return: EmpProjectRela
	 */
	EmpProjectRela findByPrimaryKey(String taskId);

	/**
	 * @Title: findByEmpId
	 * @Description: 任务信息取得
	 * @param: @param empId 员工编号
	 * @param: @return 任务List
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByEmpId(String empId);

	/**
	 * @Title: findByProNum
	 * @Description: 任务信息取得
	 * @param: @param proNum 项目编号
	 * @param: @return 任务List
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByProNum(String proNum);

	/**
	 * @Title: findAllEmpProjectRelas
	 * @Description: 全部任务信息取得
	 * @param: @return 任务List
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findAllEmpProjectRelas();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 任务信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 任务List
	 * @return: List<EmpProjectRela>
	 */
	List<EmpProjectRela> findByEmpIdLike(@Param("Id") String Id);

}
