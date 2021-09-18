package com.asith.gmdb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "GMDB_DIRECTORS")
public class Director implements Serializable {

	private static final long serialVersionUID = 1L;

	private long directorId;
	private String directorFirstName;
	private String directorLastName;
	private List<Movie> movies;

	public Director() {
	}

	@Id
	@Column(name = "director_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(long directorId) {
		this.directorId = directorId;
	}

	@Column(name = "first_name")
	@NotBlank(message = "Required!")
	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	@Column(name = "last_name")
	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	@ManyToMany(mappedBy = "directors")
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return directorFirstName + " " + directorLastName;
	}
}
