#FilmQueryProject


#Description
In this project, I created a way to look into my DataServer storage and gave the user the ability to find a film based on the ID of a film and/or a keyword. When the user inputs a valid number of a film ID, what returns is the film name, description, year, language, rating, and the full cast list. This is the same when looking by a keyword, a list of possible film options shows up with each film's information. If the ID is not valid or the film keyword is not valid, the user will be met with an error message. The program can be exited at any time by using "3".

#Technologies
For my menu, I used a basic switch case. I used several methods and used SQL, which is automatically logged in for the user. I used ArrayLists for my actors/films. I used Prepared Statements to help protect against injection attacks (no one is changing my data or accessing my servers).

#Lessons Learned
In this exercise, I learned the value of patience and repetition. Don't try to fix something that is tried and true, not everything needs a special answer. Sometimes sticking to bare-bones code is the best way to solve a problem (and research in notes and elsewhere). But most importantly, I learned that Excel is not a database. 