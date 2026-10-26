# NFL Players Database

## Project Description

### Topic

The project was made to use a sample nfl player database to search through it using premade prompts to have some control over how things are done. The database has 150 nfl players and the driver program will allow the user to choose from a variety of premade paths that they can go down to find players that meet some requirements that they want. The API has 15 options with a lot of different way to go through the database. Everything that can be done in order: Display all players, search for a player by their ID, find a specific column in the database for a chosen team, display players that are in Pro Bowl status, count the players that play in a specific position, display players who went to a specific college, show the players who went undrafted but are in the league, give the players who were drafted in a certain year, display the average weight by position group, display the tallest players in the nfl, give the players born after a given date, display a teams roster by their jersey number, display number of players per team, trade a player to a new team - that actually moves someone from one team to another. 

## How to Run

The project uses the SQLite JDBC driver in `lib/`. From the repository root:

```
javac -d bin -cp "lib/*" src/*.java
java -cp "bin;lib/*" CreateDB
java -cp "bin;lib/*" Driver
```

`CreateDB` builds (or resets) `players.db` with the sample players. `Driver` starts the menu. On macOS/Linux, use `:` in place of `;` in the classpath.

The Javadoc is in [doc/index.html](doc/index.html).

## Example Test Run

```
===== NFL Players Database =====
1. Display all players
2. Find a player by ID
3. Display a column for a team
4. Display players at a position by Pro Bowl status
5. Count players at a position
6. Display players from a college
7. Display undrafted players
8. Display players by draft year
9. Display average weight by position
10. Display the tallest players
11. Display players born after a date
12. Display a team's roster by jersey number
13. Display number of players per team
14. Trade a player to a new team
15. Exit
Enter your choice: 2

Enter player ID: 1
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by org.sqlite.SQLiteJDBCLoader in an unnamed module (file:/C:/Schoolwork/Computer%20Science%20Classes/CS%2067/Assignment10/lib/sqlite-jdbc-3.53.4.0.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled

Name:     Patrick Mahomes
Team:     Chiefs
Position: QB
Jersey:   #15
Birthday: 1995-09-17
College:  Texas Tech
Height:   74 in
Weight:   225 lbs
Drafted:  2017
Pro Bowl: Yes

===== NFL Players Database =====
1. Display all players
2. Find a player by ID
3. Display a column for a team
4. Display players at a position by Pro Bowl status
5. Count players at a position
6. Display players from a college
7. Display undrafted players
8. Display players by draft year
9. Display average weight by position
10. Display the tallest players
11. Display players born after a date
12. Display a team's roster by jersey number
13. Display number of players per team
14. Trade a player to a new team
15. Exit
Enter your choice: 4

Enter position (e.g. QB): RB
Pro Bowl players? (y/n): y
James Cook
Derrick Henry
Jonathan Taylor
Saquon Barkley
Austin Ekeler
Josh Jacobs
Jahmyr Gibbs
Aaron Jones
Bijan Robinson
Alvin Kamara
Christian McCaffrey
Kyren Williams
James Conner

===== NFL Players Database =====
1. Display all players
2. Find a player by ID
3. Display a column for a team
4. Display players at a position by Pro Bowl status
5. Count players at a position
6. Display players from a college
7. Display undrafted players
8. Display players by draft year
9. Display average weight by position
10. Display the tallest players
11. Display players born after a date
12. Display a team's roster by jersey number
13. Display number of players per team
14. Trade a player to a new team
15. Exit
Enter your choice: 14

Enter player's full name: Davante Adams
Enter new team: Packers
1 row(s) updated

===== NFL Players Database =====
1. Display all players
2. Find a player by ID
3. Display a column for a team
4. Display players at a position by Pro Bowl status
5. Count players at a position
6. Display players from a college
7. Display undrafted players
8. Display players by draft year
9. Display average weight by position
10. Display the tallest players
11. Display players born after a date
12. Display a team's roster by jersey number
13. Display number of players per team
14. Trade a player to a new team
15. Exit
Enter your choice: 15

Goodbye!
```

## AI Usage Statement

Claude was used for the full program setup. I took the original pets api and plugged it into the claude app on my computer and asked it to make the same thing but tweaked so that it could set up the proper file setup and database run while allowing me to actually go through and make the code. I went into the api and made a couple different methods with basic comments and then talked to claude and had it make more. At that point claude adjusted my comments so that they were in proper javadoc form and added a bunch of new methods. As a way to make sure I understand how it all works so I put basic comments for them and then had it change them to javadoc form. I essentially did the same thing for the driver starting things out and then having claude finish and going back through with comments to make sure that I understood everything. When it came to testing the AI written stuff my first thing was just running through each line of code that AI wrote and after I did that I tested every input in the terminal to make sure that all worked. 