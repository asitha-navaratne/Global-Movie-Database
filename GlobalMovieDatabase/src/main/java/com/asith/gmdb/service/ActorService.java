package com.asith.gmdb.service;

import java.util.List;

import com.asith.gmdb.entity.Actor;

public interface ActorService {

	boolean checkActorExists(Actor actor);

	boolean saveActor(Actor actor);

	List<Actor> getActorsFromList(String actorList);

	List<Actor> getAllActors();

	boolean deleteActor(Actor actor);

}
