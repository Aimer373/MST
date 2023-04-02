package com.sony.mts.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sony.mts.entity.Position;

/**
 * @ClassName: PositionService
 * @Description: 职位Service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:32:30
 */
public interface PositionService {
	/**
	 * @Title: insertPosition
	 * @Description: 职位信息追加
	 * @param: @param position 职位对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insertPosition(Position position);

	/**
	 * @Title: delPositionById
	 * @Description: 职位信息删除
	 * @param: @param posNum 职位编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int delPositionById(@Param("posNum") String posNum);

	/**
	 * @Title: updatePositionByNo
	 * @Description: 职位信息更新
	 * @param: @param position 职位对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updatePositionByNo(Position position);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 职位信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return 职位对象
	 * @return: Position
	 */
	Position findByPrimaryKey(String posNum);

	/**
	 * @Title: findByDepNum
	 * @Description: 职位信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 职位List
	 * @return: List<Position>
	 */
	List<Position> findByDepNum(String depNum);

	/**
	 * @Title: findAllPositions
	 * @Description: 全部职位信息取得
	 * @param: @return 职位List
	 * @return: List<Position>
	 */
	List<Position> findAllPositions();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 职位信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 职位List
	 * @return: List<Position>
	 */
	List<Position> findByEmpIdLike(@Param("Id") String Id);

}