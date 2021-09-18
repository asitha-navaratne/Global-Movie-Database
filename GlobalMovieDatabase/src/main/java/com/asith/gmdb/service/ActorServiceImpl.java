package com.asith.gmdb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.ActorEao;
import com.asith.gmdb.entity.Actor;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorEao actorEao;

	@Override
	@Transactional
	public boolean checkActorExists(Actor actor) {
		List<Actor> actorList = actorEao.getActor(actor.getActorFirstName().toUpperCase(), actor.getActorLastName().toUpperCase());
		
		return actorList.isEmpty();
	}

	@Override
	@Transactional
	public boolean saveActor(Actor actor) {
		if(checkActorExists(actor)) {
			String actorFirstName = actor.getActorFirstName().toUpperCase();
			String actorLastName = actor.getActorLastName().toUpperCase();
			
			actor.setActorFirstName(actorFirstName);
			actor.setActorLastName(actorLastName);
			actorEao.saveActor(actor);
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Actor> getActorsFromList(String actorList) {
		List<String> stringList = Arrays.asList(actorList.split(","));
		List<Actor> actors = new ArrayList<>();
		
		Actor actor;
		
		for(String id : stringList) {
			actor = actorEao.getActor(Long.parseLong(id));
			actors.add(actor);
		}
		
		return actors;
	}

	@Override
	@Transactional
	public List<Actor> getAllActors() {
		List<Actor> actorList = actorEao.getAllActors();
		
		return actorList;
	}

	@Override
	@Transactional
	public boolean deleteActor(Actor actor) {
		if(checkActorExists(actor)) {
			return false;
		}
		else {
			actorEao.deleteActor(actor.getActorFirstName().toUpperCase(), actor.getActorLastName().toUpperCase());
			
			return true;
		}
	}
}
