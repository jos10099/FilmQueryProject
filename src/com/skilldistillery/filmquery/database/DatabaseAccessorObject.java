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
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DatabaseAccessorObject() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, userName, password);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, actorId);
		ResultSet actorResult = pstmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setFirstName(actorResult.getString("last_name"));
		}
		actorResult.close();
		pstmt.close();
		conn.close();
		return actor;
	}

	public Actor findActorById1(int actorId) {
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
	            actor.setFilms(findFilmsByActorId(actorId)); 
	        } } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return actor;
	}

	@Override

	public List<Film> findFilmsByActorId(int actorId) throws SQLException	 {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, userName, password);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("film.id");
				String desc = rs.getString("film.description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "Select * FROM file WHERE id = ?";
		 Connection conn = DriverManager.getConnection(URL, userName, password);
		 PreparedStatement pstmt = conn.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		    
		    
		    if(rs.next()) {
		    	film = new Film();
		    	film.setId(rs.getInt("id"));
		    	film.setTitle(rs.getString("title"));
		    	film.setDescription(rs.getString("description"));
		    	film.setReleaseYear(rs.getInt("release_year"));
		    	film.setLanguage(rs.getString("language"));
		    	film.setRentalDuration(rs.getInt("rental_duration"));
		    	film.setRentalRate(rs.getDouble("rental_rate"));
		    	film.setRating(rs.getString("rating"));
		    	film.setSpecialFeatures(rs.getString("speacial_features"));
		    	
		    	
		    	
		    }
		    rs.close();
			pstmt.close();
			conn.close();
			return film;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actor = new ArrayList<>();
		String sql
		
		
		
		
		
		return null;
	}

}
