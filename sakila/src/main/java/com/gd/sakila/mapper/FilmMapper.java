package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.Page;

@Mapper
public interface FilmMapper {
	List<FilmView> selectFilmList(Map<String, Object> map);
	int selectFilmTotal(Map<String, Object> map);
	List<Integer> selectFilmInStock(Map<String, Object> map);
	Map<String, Object> selectFilmOne(int filmId);
	List<Map<String, Object>> selectFilmActor(int filmId);
	int insertFilmActor(Map<String, Object> map);
	int deleteFilmActor(int filmId);
}