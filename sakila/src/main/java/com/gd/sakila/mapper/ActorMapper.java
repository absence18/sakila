package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Actor;

@Mapper
public interface ActorMapper {
	int insertActor(Actor actor);
	List<Map<String, Object>> selectActorInfoList(Map<String, Object> parmMap);
	int selectActorTotal(String searchWord);
}