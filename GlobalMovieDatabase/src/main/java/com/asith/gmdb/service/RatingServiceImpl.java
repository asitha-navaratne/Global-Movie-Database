package com.asith.gmdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.RatingEao;
import com.asith.gmdb.entity.Rating;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingEao ratingEao;

	@Override
	@Transactional
	public void saveRating(Rating rating) {
		ratingEao.saveRating(rating);
	}

	@Override
	@Transactional
	public Rating getRating(long userId, long movieId) {
		List<Rating> ratingList = ratingEao.getRating(userId, movieId);
		
		if(ratingList.isEmpty()) {
			return null;
		}
		else {
			return ratingList.get(0);
		}
	}

	@Override
	@Transactional
	public void deleteRating(Rating rating) {
		ratingEao.deleteRating(rating);
	}
}
