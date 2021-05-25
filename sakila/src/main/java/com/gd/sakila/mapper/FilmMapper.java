package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.Page;

@Mapper
public interface FilmMapper {
	List<FilmView> selectFilmList(Page page);
	int selectFilmTotal(Page page);
	List<Integer> selectFilmInStock(Map<String, Object> map);
	Map<String, Object> selectFilmOne(int filmId);
	List<String> selectCategoryList();
	List<String> selectRatingList();
}