package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.ActorMapper;
import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ActorService {
	@Autowired ActorMapper actorMapper;
	public int addActor(Actor actor) {
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorService.addActor 매개변수 actor : " + actor);
		return actorMapper.insertActor(actor);
	}

	public Map<String, Object> getActorList(Map<String,Object> map) {

		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorService.getActorList 매개변수 map : " + map);

		int currentPage = (int)map.get("currentPage");
		int rowPerPage = (int)map.get("rowPerPage");
		String searchWord = (String)map.get("searchWord");

		Page page = new Page();
		page.setBeginRow((currentPage - 1 )*rowPerPage);
		page.setRowPerPage(rowPerPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorService.getActorList page객체 : " + page);

		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("page", page);
		parmMap.put("searchWord", searchWord);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorService.getActorList parmMap : " + parmMap);

		int actorTotal = actorMapper.selectActorTotal(searchWord);
		int lastPage = (int)Math.ceil((double)actorTotal/ rowPerPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ FilmService.getFilmList filmTotal :" + actorTotal);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ FilmService.getFilmList lastPage :" + lastPage);

		//어차피 검색어 등등 다른것때문에 맵으로 넘겨주는데 굳이 page객체를 만들어서 전달 해야될까?? 그냥 page객체없이 전부 map으로 넘겨주면 안될까? --> 우선 기존 형식을 유지하기위해서 page객체 만들어서 진행
		List<Map<String, Object>> actorList = actorMapper.selectActorInfoList(parmMap);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorService.getActorList actorList : " + actorList);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("actorList", actorList);
		returnMap.put("lastPage", lastPage);


		return returnMap;
	}
}