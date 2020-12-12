# Basketball Statistics BY SANJA ORGANIZATOR

Basketball statistics is a project that can bring you all the latest information about your favorite teams. Check out the latest results and be proud  of your home team. Learn about which player scored the most, who had the most assists and who jumps the best. Stats aggregated per game and per season.
Project is inside sanjaorganizator folder. There is also vue.js source code used to build static pages and documentation.

# Requirement
Basketball is one of the most popular sports in the world and in line with the needs of watching games and statistics a large number of software solutions are being developed. It also belongs to national sports and thus states have their own basketball leagues. Currently, the world strives for each league to have its own system for monitoring results. As the strongest league in the world considered the American NBA League. Based on their match tracking system and team / player statistics we will build our prototype. The plan is for our application to support statistical processing as well as event handling.

Backend
The task is to create a Java web application to display matches and details of individual matches. The following queries need to be implemented, supported:

QUERY_ID: 1 - Show all matches with current results. Both active and completed are considered matches.
QUERY_ID: 2 - Display player details for the selected match, ie player statistics for that match match (points, assists, rebounds)
QUERY_ID: 3 - Show statistics for the selected player for all matches (total and average points, jumps, assists)
QUERY_ID: 4 - Show the players who scored the most points, rebounds and assists at all match (one player per category, if more players have achieved the same maximum number show them all)
QUERY_ID: 5 - Show the top 5 players with the most “double-doubles” (any 2 statistics categories for which the player has achieved a double-digit performance)
QUERY_ID: 6 - Show team rankings - calculated based on the percentage of wins in all matches (if the teams have the same percentage, the advantage is given to the team that has a better overall basket difference)
Frontend
The initial frontend should contain the following visualizations (QUERY_ID: 1, QUERY_ID: 2):

View all matches with results
View details of one selected match with player statistics for that match (number points, assists and rebounds per player)
JSON files
Entries to the application are:

Configuration JSON file with data on teams and players
JSON file with a list of events that describe the course of matches
Team:

Id: long,
name: string,
city: string
Player:

Id: long,
teamId: long,
name: string, -surname: string,
number: int,
height: int,
age: int, -position: enum (POINT_GUARD, SHOOTING_GUARD, SMALL_FORWARD, POWER_FORWARD, CENTERE)
Event:

game: long,
type: enum (START, END, ASSIST, JUMP, POINT),
payload: object
Payload:

hostId: long,
guestId: long,
playerId: long,
value: int}Polje “type” je tipa ENUM i prihvata sledeće vrednosti:1.START2.END3.ASSIST4.JUMP5.POIN
Event constraints
When loading a JSON data file, skip loading events that do not meet the rules and log them:

The finished match must be covered between START and END events.
It is also important to emphasize the possibility of a lack of END event, which therefore means that it is a match still active, ie in progress.
After a ASSIST event, only a POINT event can occur, with a value of 2 or 3 points.
We have no major restrictions for POINT and JUMP events and they can happen in any order and to repeat themselves.
A player to whom any type of event is added must be a member of the team participating in that match.
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
