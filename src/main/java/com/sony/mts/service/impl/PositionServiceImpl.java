package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.mts.dao.PositionMapper;
import com.sony.mts.entity.Position;
import com.sony.mts.service.PositionService;

/**
 * @ClassName: PositionServiceImpl
 * @Description: 职位Service实现类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:34:03
 */
@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	PositionMapper positionMapper;

	/**
	 * @Title: insertPosition
	 * @Description: 职位信息追加
	 * @param: @param position 职位对象
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#insertPosition(com.sony.mts.entity.Position)
	 */
	@Override
	public int insertPosition(Position position) {
		return positionMapper.insert(position);
	}

	/**
	 * @Title: delPositionById
	 * @Description: 职位信息删除
	 * @param: @param posNum 职位编号
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#delPositionById(java.lang.String)
	 */
	@Override
	public int delPositionById(String posNum) {
		return positionMapper.deleteByPrimaryKey(posNum);
	}

	/**
	 * @Title: updatePositionByNo
	 * @Description: 职位信息更新
	 * @param: @param position 职位对象
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#updatePositionByNo(com.sony.mts.entity.Position)
	 */
	@Override
	public int updatePositionByNo(Position position) {
		return positionMapper.updateByPrimaryKey(position);
	}

	/**
	 * @Title: findAllPositions
	 * @Description: 全部职位信息取得
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#findAllPositions()
	 */
	@Override
	public List<Position> findAllPositions() {
		return positionMapper.findAllPositions();
	}

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 职位信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#findByPrimaryKey(java.lang.String)
	 */
	@Override
	public Position findByPrimaryKey(String posNum) {
		return positionMapper.findByPrimaryKey(posNum);
	}

	/**
	 * @Title: findByDepNum
	 * @Description: 职位信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#findByDepNum(java.lang.String)
	 */
	@Override
	public List<Position> findByDepNum(String depNum) {
		return positionMapper.findByDepNum(depNum);
	}

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 职位信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return
	 * @see com.sony.mts.service.PositionService#findByEmpIdLike(java.lang.String)
	 */
	@Override
	public List<Position> findByEmpIdLike(String Id) {
		return positionMapper.findByEmpIdLike(Id);
	}

}
