package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private String language;
	private Integer rentalDuration;
	private Double rentalRate;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;

	public Film() {
		
	}

	public Film(int id, String title, String description, Integer releaseYear, String language, Integer rentalDuration,
			Double rentalRate, String rating, String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		actors = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, language, rating, releaseYear, rentalDuration, rentalRate, specialFeatures,
				title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(language, other.language) && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear)
				&& Objects.equals(rentalDuration, other.rentalDuration) && Objects.equals(rentalRate, other.rentalRate)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", language=" + language + ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate
				+ ", rating=" + rating + ", specialFeatures=" + specialFeatures + ", actors=" + actors + "]";
	}

	public void setLength(int int1) {
	
	}
}
