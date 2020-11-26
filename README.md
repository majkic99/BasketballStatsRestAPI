## Table of contents
* [Basketball Statistics BY SANJA ORGANIZATOR](#Basketball Statistics BY SANJA ORGANIZATOR)
* [Installation](#Installation)
* [Routes](#Routes)
* [Technologies](#Technologies)
* [Creators](#Creators)
* [Made for](#Made for)


# Basketball Statistics BY SANJA ORGANIZATOR

Basketball statistics is a project that can bring you all the latest information about your favorite teams. Check out the latest results and be proud  of your home team. Learn about which player scored the most, who had the most assists and who jumps the best. Stats aggregated per game and per season.
Project is inside sanjaorganizator folder. There is also vue.js source code used to build static pages and documentation.


## Installation
You will need to have JDK 1.8 on your PC, and IDE of your choice.

Before running this project you're going to have to do the following
```
1. Clone the repository on your machine
2. Inside Class Storage, inside loadEverything method you will have to replace three filepaths (arguments of functions):
loadTeams(  {absolute path to JSON file containing informations about Teams}  )
loadPlayers(  {absolute path to JSON file containing informations about Players}  )
loadEvents(  {absolute path to JSON file containing informations about Events}  )
3. Build with Maven
4. Execute   java -jar sanjaorganizator-0.0.1-SNAPSHOT.jar (that you will find inside the target folder).
The server is now running on port 8080 and you can find the site at localhost:8080/
After running jar, application will create tmp folder inside target with logs of failed Events.
**WON'T WORK WITHOUT CHANGING FILEPATHS SO THEY MATCH YOUR PC ABSOLUTE PATHING!**
```


## Routes
 Examples for each JSON that returns in response body is in Docs under JSON examples.


/api/matches (GET)  - Returns JSON object which contains information about both teams (name, city, list of players, id), id of the game, points for both teams.
Returns a list of Match JSON objects.

/api/matches/:id (GET) - Returns JSON object which contains detailed information about the game which has the id as the route. It contains information about teams, the score, id of the game along with stats table (points, assists and jumps) for each player.
Returns one FullMatch JSON object that the same id as in param. They have multiple Objects inside the playerList field. pointsMap, assistsMap and jumpsMap objects are filled with players from both playerLists.

/api/player/:id (GET)- Returns info about particular player (points, jumps, assists and games played) along with his name.
Returns one Player JSON object that has the same id as in param.

/api/best (GET) - Returns list of players with most points, jumps or assists in particular. If two or more players have same value for one of these, all are shown.
Returns a Best JSON object. Each field in this JSON objects can have multiple fields inside it if two or more players have the same value.

/api/dd (GET) - Returns a list of players with the highest number of Double Double games, along with how many they have.
Returns a dd JSON object which is a list of player names and surnames along with the amount of double double games they have.

/api/table (GET) - Detailed Table about Teams, how many points they've scored, how many they've received and their win percentage
Returns a list of Table JSON objects (they can have multiple subentites inside the playerList). This list is sorted in descending order.



_Event handling was done in a way that after an assist every subsequent event except 2-pointer or a 3-pointer from assistants teammate will be swallowed and not counted for._

## Technologies

RESTful API created with SpringBoot, used the initializr to create the API as fast as possible for this hackathon utilizing gson library for reading JSON files which are used as a database. 
Frontend Created with Vue.js, builded and imported as static HTML files as resources.
Built with Maven.

## Creators
We are two third year students from Racunarski Fakultet, Belgrade,
Ivan Djordjevic and Nebojsa Majkic.

## Made for
5 DAYS ON JAVA, HACKATHON MADE BY LEVI9