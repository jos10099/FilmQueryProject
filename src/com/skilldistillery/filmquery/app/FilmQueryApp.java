package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db;
	private Scanner input;

	public FilmQueryApp() throws ClassNotFoundException {
		db = new DatabaseAccessorObject();
		input = new Scanner(System.in);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		printWelcomeMessage();
		displayMenu();
		input.close();
	}

	private void printWelcomeMessage() {
		System.out.println("==== Welcome to Film Query App ====");
		System.out.println();
	}

	private void displayMenu() {
		boolean keepRunning = true;

		while (keepRunning) {
			System.out.println("Please choose an option:");
			System.out.println("1. Look up a film by its ID");
			System.out.println("2. Look up a film by a search keyword");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			input.nextLine();

			switch (choice) {
			case 1:
				lookUpFilmById();
				break;
			case 2:
				lookUpFilmByKeyword();
				break;
			case 3:
				keepRunning = false;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				System.out.println();
			}
		}
	}

	private void lookUpFilmById() {
		System.out.print("Enter the film ID: ");
		int filmId = input.nextInt();
		input.nextLine();

		try {
			Film film = db.findFilmById(filmId);
			if (film != null) {
				displayFilm(film);
			} else {
				System.out.println(
						"The film you are looking for doesn't seem to exist. Have you thought about giving up? (:");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the film: " + e.getMessage());
		}

		System.out.println();
	}

	private void lookUpFilmByKeyword() {
		System.out.print("Enter the search keyword: ");
		String keyword = input.nextLine();

		try {
			List<Film> films = db.findFilmsByKeyword(keyword);
			if (!films.isEmpty()) {
				System.out.println("Films matching the keyword:");
				for (Film film : films) {
					displayFilm(film);
					System.out.println();
				}
			} else {
				System.out.println(
						"This database is HUUUGE and you managed to find put in a keyword that doesn't exis....impressive.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the films: " + e.getMessage());
		}

		System.out.println();
	}

	private void displayFilm(Film film) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Title: " + film.getTitle());
		System.out.println("~                  ~");
		System.out.println("Year: " + film.getReleaseYear());
		System.out.println("~                  ~");
		System.out.println("Rating: " + film.getRating());
		System.out.println("~                  ~");
		System.out.println("Description: " + film.getDescription());
		System.out.println("~                  ~");
		System.out.println("Language: " + film.getLanguage());
		System.out.println("~                  ~");

		try {
			List<Actor> actors = db.findActorsByFilmId(film.getId());
			if (!actors.isEmpty()) {
				System.out.println("====Cast=====");
				for (Actor actor : actors) {
					System.out.println(actor.getFirstName() + " " + actor.getLastName());
					System.out.println("~                  ~");

				}
			} else {
				System.out.println("No cast information available.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the cast information: " + e.getMessage());
		}
	}
}
