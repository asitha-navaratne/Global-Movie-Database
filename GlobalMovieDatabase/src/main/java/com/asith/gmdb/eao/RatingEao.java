package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.Rating;

public interface RatingEao {

	void saveRating(Rating rating);

	List<Rating> getRating(long userId, long movieId);

	void deleteRating(Rating rating);

}
