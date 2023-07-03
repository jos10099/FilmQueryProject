package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String userName = "student";
	private static final String password = "student";


	public DatabaseAccessorObject() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, actorId);
			ResultSet actorResult = pstmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}
			actorResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) throws SQLException {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT film.id, title, description, release_year, language_id, "
				+ "rental_duration, rental_rate, length, replacement_cost, rating, special_features "
				+ "FROM film JOIN film_actor ON film.id = film_actor.film_id " + "WHERE actor_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(getLanguageName(rs.getInt("language_id")));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				films.add(film);
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "SELECT film.id, title, description, release_year, language_id, " +
	             "rental_duration, rental_rate, length, replacement_cost, rating, special_features, " +
	             "actor.first_name, actor.last_name " +
	             "FROM film " +
	             "JOIN film_actor ON film.id = film_actor.film_id " +
	             "JOIN actor ON film_actor.actor_id = actor.id " +
	             "WHERE film.id = ?";

		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(getLanguageName(rs.getInt("language_id")));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return film;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT film.id, title, description, release_year, language_id, "
				+ "rental_duration, rental_rate, length, replacement_cost, rating, special_features "
				+ "FROM film JOIN language ON film.language_id = language.id "
				+ "WHERE title LIKE ? OR description LIKE ?";
		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguage(getLanguageName(rs.getInt("language_id")));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				films.add(film);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name " + "FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("actor.id"));
				actor.setFirstName(rs.getString("actor.first_name"));
				actor.setLastName(rs.getString("actor.last_name"));

				List<Film> films = findFilmsByActorId(actor.getId());
				actor.setFilms(films);
				actors.add(actor);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return actors;
	}

	private String getLanguageName(int languageId) throws SQLException {
		String languageName = "";
		String sql = "SELECT name FROM language WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, userName, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, languageId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				languageName = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return languageName;
	}
}
