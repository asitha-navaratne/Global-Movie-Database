package com.asith.gmdb.service;

import com.asith.gmdb.entity.Rating;

public interface RatingService {

	void saveRating(Rating rating);

	Rating getRating(long userId, long movieId);

	void deleteRating(Rating rating);

}
