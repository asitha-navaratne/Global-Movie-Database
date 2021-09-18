package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.Actor;

public interface ActorEao {

	void saveActor(Actor actor);

	Actor getActor(long actorId);

	List<Actor> getActor(String firstName, String lastName);

	List<Actor> getAllActors();

	void deleteActor(String firstName, String lastName);

}
