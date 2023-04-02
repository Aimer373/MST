package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Position;

/**
 * @ClassName: PositionMapper
 * @Description: 职位Mapper
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:28:26
 */
@Mapper
public interface PositionMapper {

	/**
	 * @Title: insert
	 * @Description: 职位信息追加
	 * @param: @param position 职位对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insert(Position position);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 职位信息删除
	 * @param: @param posNum 职位编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int deleteByPrimaryKey(String posNum);

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 职位信息更新
	 * @param: @param position 职位对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(Position position);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 职位信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return 返回职位对象
	 * @return: Position
	 */
	Position findByPrimaryKey(String posNum);

	/**
	 * @Title: findByDepNum
	 * @Description: 职位信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 返回职位集合
	 * @return: List<Position>
	 */
	List<Position> findByDepNum(String depNum);

	/**
	 * @Title: findAllPositions
	 * @Description: 全部职位信息取得
	 * @param: @return 返回职位集合
	 * @return: List<Position>
	 */
	List<Position> findAllPositions();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 职位信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 返回职位集合
	 * @return: List<Position>
	 */
	List<Position> findByEmpIdLike(String Id);

}