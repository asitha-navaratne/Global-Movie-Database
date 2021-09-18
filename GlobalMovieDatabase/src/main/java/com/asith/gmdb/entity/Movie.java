package com.asith.gmdb.entity;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GMDB_MOVIES")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	private long movieId;
	private String movieName;
	private Year movieYear;
	private int movieRuntimeHours;
	private int movieRuntimeMinutes;
	private float movieRating;
	private List<Genre> genres;
	private List<Director> directors;
	private List<Actor> actors;
	private List<Rating> ratings;
	private List<User> users;

	public Movie() {
	}

	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	@Column(name = "movie_name")
	@NotBlank(message = "Required!")
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Column(name = "movie_year")
	@NotNull(message = "Required!")
	public Year getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(Year movieYear) {
		this.movieYear = movieYear;
	}

	@Column(name = "runtime_hours")
	@Min(value = 0L, message = "Must be a valid value!")
	public int getMovieRuntimeHours() {
		return movieRuntimeHours;
	}

	public void setMovieRuntimeHours(int movieRuntimeHours) {
		this.movieRuntimeHours = movieRuntimeHours;
	}

	@Column(name = "runtime_minutes")
	@Min(value = 0L, message = "Must be a valid value!")
	public int getMovieRuntimeMinutes() {
		return movieRuntimeMinutes;
	}

	public void setMovieRuntimeMinutes(int movieRuntimeMinutes) {
		this.movieRuntimeMinutes = movieRuntimeMinutes;
	}

	@Transient
	public float getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(float movieRating) {
		this.movieRating = movieRating;
	}

	@ManyToMany
	@JoinTable(name = "GMDB_MOVIES_GENRES",
				joinColumns = {@JoinColumn(name = "movie_id")},
				inverseJoinColumns = {@JoinColumn(name = "genre_id")})
	@NotNull(message = "Required!")
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@ManyToMany
	@JoinTable(name = "GMDB_MOVIES_DIRECTORS", 
				joinColumns = {@JoinColumn(name = "movie_id")},
				inverseJoinColumns = {@JoinColumn(name = "director_id")})
	@NotNull(message = "Required!")
	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	@ManyToMany
	@JoinTable(name = "GMDB_MOVIES_ACTORS",
				joinColumns = {@JoinColumn(name = "movie_id")},
				inverseJoinColumns = {@JoinColumn(name = "actor_id")})
	@NotNull(message = "Required!")
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@ManyToMany(mappedBy = "movies")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieId, movieName, movieYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return movieId == other.movieId && Objects.equals(movieName, other.movieName)
				&& Objects.equals(movieYear, other.movieYear);
	}

	@Override
	public String toString() {
		return movieName + " (" + movieYear + ")";
	}
}
