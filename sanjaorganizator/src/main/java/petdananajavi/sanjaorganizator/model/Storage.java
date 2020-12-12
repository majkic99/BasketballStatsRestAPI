package petdananajavi.sanjaorganizator.model;

import com.google.gson.Gson;
import petdananajavi.sanjaorganizator.model.databasemodels.Event;
import petdananajavi.sanjaorganizator.model.databasemodels.Player;
import petdananajavi.sanjaorganizator.model.databasemodels.Team;
import petdananajavi.sanjaorganizator.model.enums.EventType;
import petdananajavi.sanjaorganizator.model.enums.FailedEvents;
import petdananajavi.sanjaorganizator.model.overviewobjects.MatchOverview;
import petdananajavi.sanjaorganizator.model.overviewobjects.PlayerStatsOverview;
import petdananajavi.sanjaorganizator.model.overviewobjects.TeamResultsOverview;

import java.io.*;
import java.util.*;

public class Storage {
    Gson gson = new Gson();
    private static Storage instance = null;

    private List<Team> teamList;
    private List<Player> playerList;
    private List<Event> eventList;
    private Map<Event, FailedEvents> failedEvents = new HashMap<>();
    private Map<Long, Match> endedMatches = new HashMap<>();
    private HashMap<Long, Match> matchHashMap;
    private HashMap<Long, Match> matchKeys;

    public static Storage getInstance(){
        if (instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public void loadEverything() throws FileNotFoundException {
        loadTeams("src/main/resources/db/teams.json");
        loadPlayers("src/main/resources/db/players.json");
        putPlayersInTeams();
        loadEvents("src/main/resources/db/events_full.json");
        loadGames();
        writeLogs();
    }

    public List<MatchOverview> getAllMatchOverviews(){
        List<MatchOverview> list = new ArrayList<>();
        for (Match match : getAllMatches()){
            MatchOverview overview = new MatchOverview(match.getHost(), match.getGuest(), match.getHostPoints(), match.getGuestPoints(), match.getId(), match.isFinished());
            list.add(overview);
        }
        return list;
    }
    public Match getMatchByID(long id){

        for (Match match : this.getAllMatches()){
            if (match.getId() == id){
                return match;
            }
        }
        return null;
    }
    public PlayerStatsOverview getPlayerStats(long id){
        if (getPlayerbyID(id) == null){

        }else {
            PlayerStatsOverview stats = new PlayerStatsOverview(getPlayerPoints(id), getPlayerJumps(id),
                    getPlayerAssists(id), getPlayerGamesPlayed(id), getPlayerbyID(id));
            return stats;
        }
        return null;
    }
    public Map<String,Map<Player,Integer>> getBestPlayers(){
        Map<String,Map<Player,Integer>> map = new HashMap<>();
        map.put("Points" , getHighestScorers());
        map.put("Assists" , getHighestAssists());
        map.put("Jumps", getHighestJumps());
        return map;
    }
    public Map<Player, Integer> findMostDoubleDoubles(){
        Map<Player, Integer> result = new HashMap<>();
        Map<Player, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (Player p : playerList){
            int counter = 0;
            for (Match m : getAllMatches()){
                if (isDoubleDouble(p, m)){
                    counter++;
                }
            }
            list.add(counter);
            map.put(p, counter);
        }

        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < 5; i++){
            int number = list.get(i);
            for (Map.Entry<Player, Integer> entry : map.entrySet()) {
                if (entry.getValue() == number){
                    result.put(entry.getKey(), entry.getValue());
                    //System.out.println(entry.getKey() + "/" + entry.getValue());
                }
            }
        }

        return result;
    }
    public List<TeamResultsOverview> getTable(){
        List<TeamResultsOverview> list = new ArrayList<>();
        for (Team team : getAllTeamsSortedByWins()){
            TeamResultsOverview teamResult = new TeamResultsOverview(team, pointsScoredByTeam(team),
                    pointsReceivedByTeam(team), winPercentageByTeam(team));
            list.add(teamResult);
        }
        return list;
    }



    private void writeLogs(){
        File dir = new File("tmp/test");
        dir.mkdirs();
        File tmp = new File(dir, "failedEventsLog.txt");
        try {
            tmp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(tmp);
            Map<Event, FailedEvents> map = getFailedEvents();
            for (Map.Entry<Event, FailedEvents> entry : map.entrySet()) {
                fw.write(entry.getKey().toString() + entry.getValue().toString());

                fw.write("\n");

                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Team> getAllTeamsSortedByWins(){
        List<Team> teamList = getTeamList();
        Collections.sort(teamList, new CustomComparatorTeams());
        return teamList;
    }


    private List<Match> getAllMatches(){
        List<Match> matchList = new ArrayList<>();
        for (Match match : matchHashMap.values()){
            matchList.add(match);
        }
        return matchList;
    }

    private Team getTeambyID(long id){
        for (Team t : teamList){
            if (id == t.getId()){
                return t;
            }
        }
        return null;
    }
    private Player getPlayerbyID(long id){
        for (Player p : playerList){
            if (id == p.getId()){
                return p;
            }
        }
        return null;
    }

    private int getPlayerPoints(long id){
        int points = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getPointsMap().containsKey(p.getId())){
                points += match.getPointsMap().get(p.getId());
            }
        }
        return points;
    }
    private int getPlayerJumps(long id){
        int jumps = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getJumpsMap().containsKey(p.getId())){
                jumps += match.getJumpsMap().get(p.getId());
            }
        }
        return jumps;
    }
    private int getPlayerAssists(long id){
        int assists = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getAssistsMap().containsKey(p.getId())){
                assists += match.getAssistsMap().get(p.getId());
            }
        }
        return assists;
    }
    private long getAveragePlayerPoints(long id){
        long points = getPlayerPoints(id);
        int counter = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getPointsMap().containsKey(p.getId())){
               counter++;
            }
        }
        return points/counter;
    }
    private long getAveragePlayerJumps(long id){
        long jumps = getPlayerJumps(id);
        int counter = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getPointsMap().containsKey(p.getId())){
                counter++;
            }
        }
        return jumps/counter;
    }
    private long getAveragePlayerAssists(long id) {
        long assists = getPlayerAssists(id);
        int counter = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getPointsMap().containsKey(p.getId())){
                counter++;
            }
        }
        return assists/counter;
    }
    private int getPlayerGamesPlayed(long id){
        int counter = 0;
        Player p = getPlayerbyID(id);
        for (Match match : instance.getAllMatches()){
            if (match.getPointsMap().containsKey(p.getId())){
                counter++;
            }
        }
        return counter;
    }

    private Map<Player, Integer> getHighestScorers(){
        Map<Player,Integer> bestMap = new HashMap<>();
        int currMax = 0;
        for (Player p : playerList){
            int currPlayerPoints = getPlayerPoints(p.getId());
            if (currPlayerPoints > currMax){
                bestMap.clear();
                currMax = currPlayerPoints;
                bestMap.put(p, currPlayerPoints);
            }else if(currPlayerPoints == currMax){
                bestMap.put(p, currPlayerPoints);
            }

        }
        return bestMap;
    }
    private Map<Player, Integer> getHighestAssists(){
        Map<Player,Integer> bestMap = new HashMap<>();
        int currMax = 0;
        for (Player p : playerList){
            int currPlayerAssists = getPlayerAssists(p.getId());
            if (currPlayerAssists > currMax){
                bestMap.clear();
                currMax = currPlayerAssists;
                bestMap.put(p, currPlayerAssists);
            }else if(currPlayerAssists == currMax){
                bestMap.put(p, currPlayerAssists);
            }

        }
        return bestMap;
    }
    private Map<Player, Integer> getHighestJumps(){
        Map<Player,Integer> bestMap = new HashMap<>();
        int currMax = 0;
        for (Player p : playerList){
            int currPlayerJumps = getPlayerJumps(p.getId());
            if (currPlayerJumps > currMax){
                bestMap.clear();
                currMax = currPlayerJumps;
                bestMap.put(p, currPlayerJumps);
            }else if(currPlayerJumps == currMax){
                bestMap.put(p, currPlayerJumps);
            }

        }
        return bestMap;
    }

    private boolean isDoubleDouble(Player player, Match match){
        if (!(match.getPointsMap().containsKey(player.getId()))) {
            return false;
        }
        int points = match.getPointsMap().get(player.getId());
        int assists = match.getAssistsMap().get(player.getId());
        int jumps = match.getJumpsMap().get(player.getId());

        if ((points >= 10 && jumps >= 10) || (points >= 10 && assists >= 10) || (jumps >= 10 && assists >= 10)){
            return true;
        }
        return false;
    }


    double winPercentageByTeam(Team team){

        double gamesPlayed = 0;
        double gamesWon = 0;

        for (Match match : getAllMatches()){
            if (match.isFinished()){
                if (match.getHost() == team || match.getGuest() == team){
                    gamesPlayed++;
                    if (match.getHost() == team){
                        if (match.getHostPoints() > match.getGuestPoints()){
                            gamesWon++;
                        }
                    }else{
                        if (match.getHostPoints() < match.getGuestPoints()){
                            gamesWon++;
                        }
                    }
                }
            }

        }
        double result = gamesWon/gamesPlayed;
        return result;

    }
     long pointsScoredByTeam(Team team){
        long points = 0;
        for (Match match : getAllMatches()){
            if (match.isFinished()) {
                if (match.getHost() == team || match.getGuest() == team) {

                    if (match.getHost() == team) {
                        points += match.getHostPoints();
                    } else {
                        points += match.getGuestPoints();
                    }
                }
            }
        }

        return points;
    }
     long pointsReceivedByTeam(Team team){
        long points = 0;
        for (Match match : getAllMatches()){
            if (match.isFinished()) {
                if (match.getHost() == team || match.getGuest() == team) {

                    if (match.getHost() == team) {
                        points += match.getGuestPoints();
                    } else {
                        points += match.getHostPoints();
                    }
                }
            }
        }
        return points;
    }


    private void loadTeams(String filepath) throws FileNotFoundException {
        try (Reader reader = new FileReader(filepath)) {
            Team[] teamArray = gson.fromJson(reader, Team[].class);
            teamList = new ArrayList<>(Arrays.asList(teamArray));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadPlayers(String filepath) throws FileNotFoundException {
        try (Reader reader = new FileReader(filepath)) {
            Player[] teamArray = gson.fromJson(reader, Player[].class);
            playerList = new ArrayList<>(Arrays.asList(teamArray));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void putPlayersInTeams(){
        for (Player p : playerList){
            for (Team t : teamList){
                if (p.getTeamId() == t.getId()){
                    t.getPlayerList().add(p);
                    break;
                }
            }
        }
    }
    private void loadEvents(String filepath) throws FileNotFoundException {
        try (Reader reader = new FileReader(filepath)) {
            Event[] eventArray = gson.fromJson(reader, Event[].class);
            eventList = new ArrayList<>(Arrays.asList(eventArray));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("test");
    }
    private void loadGames() {
        matchKeys = new HashMap<>();
        matchHashMap = new HashMap<>();
        boolean isAssist = false;
        long idAssist = 0;
        for (Event event : eventList) {
            switch (event.getEventType()) {
                case START:
                    startCaseCheck(event);
                    break;
            }
        }
        //System.out.println("test");
        for (Map.Entry<Long, Match> entry : matchKeys.entrySet()) {
        //IF ASSISTS HAPPENS EVERY JUMP OR 1-POINTER WILL BE SWALLOWED, ANOTHER ASSIST OVERWRITES PREVIOUS, 2-POINTER OR 3-POINTER RESETS ASSISTS FLAG
            for (Event event : eventList) {
                if (event.getGame() == entry.getKey()) {
                    switch (event.getEventType()) {
                        case START:
                            startCase(event);
                            break;
                        case END:
                            endCase(event, isAssist, idAssist);
                            break;
                        case ASSIST:
                            assistCase(event, isAssist);
                            idAssist = event.getPayload().getPlayerId();
                            isAssist = true;
                            break;
                        case POINT:
                            boolean flag = pointCase(event, isAssist, idAssist);
                            if (flag){
                            isAssist = false;
                            idAssist = 0;
                            }

                            break;
                        case JUMP:
                            jumpCase(event, isAssist);
                            break;

                    }
                }
            }
        }

            for (Match match : matchHashMap.values()) {
                finalResult(match);
            }
           // System.out.println("test");

    }
    private void finalResult(Match match){
        int hostPoints = 0;
        int guestPoints = 0;

        for (Player player : match.getHost().getPlayerList()){
            hostPoints += match.getPointsMap().get(player.getId());
        }
        for (Player player : match.getGuest().getPlayerList()){
            guestPoints += match.getPointsMap().get(player.getId());
        }

        match.setHostPoints(hostPoints);
        match.setGuestPoints(guestPoints);
    }
    private void endCase(Event event, boolean isAssist, long idAssist){
        Match match = matchHashMap.get(event.getGame());
        if (isAssist){
            failedEvents.put(event,FailedEvents.ASSISTBEFOREHAND);
            Player player = getPlayerbyID(idAssist);
            int currAssists = match.getAssistsMap().get(player);
            int newAssists = currAssists - 1;
            match.getAssistsMap().put(player.getId(), newAssists);
        }
            match.setFinished(true);
            endedMatches.put(event.getGame(), getMatchByID(event.getGame()));

    }
    private void startCaseCheck(Event event){
        if (event.getPayload().getHostId() == event.getPayload().getGuestId()){
            failedEvents.put(event, FailedEvents.SAMETEAMGAME);
            return;

        }else if((!teamList.contains(this.getTeambyID(event.getPayload().getHostId()))) || (!(teamList.contains(this.getTeambyID(event.getPayload().getGuestId()))))) {
            failedEvents.put(event, FailedEvents.TEAMNOTEXISTANT);
            return;
        }else if (matchKeys.containsKey(event.getGame())){
            failedEvents.put(event, FailedEvents.ALREADYSTARTED);
            return;

        }else {
            if (event.getEventType().equals(EventType.START)) {
                Match match = new Match(event.getGame(), getTeambyID(event.getPayload().getHostId()), getTeambyID(event.getPayload().getGuestId()));
                matchKeys.put(match.getId(), match);
            }
        }
    }
    private void startCase(Event event){
        if (event.getPayload().getHostId() == event.getPayload().getGuestId()) {
            failedEvents.put(event, FailedEvents.SAMETEAMGAME);
            return;


        }else {
            if (event.getEventType().equals(EventType.START)) {
                Match match = matchKeys.get(event.getGame());
                for (Player p : getTeambyID(event.getPayload().getHostId()).getPlayerList()) {
                    match.getPointsMap().put(p.getId(), 0);
                    match.getAssistsMap().put(p.getId(), 0);
                    match.getJumpsMap().put(p.getId(), 0);
                }
                for (Player p : getTeambyID(event.getPayload().getGuestId()).getPlayerList()) {
                    match.getPointsMap().put(p.getId(), 0);
                    match.getAssistsMap().put(p.getId(), 0);
                    match.getJumpsMap().put(p.getId(), 0);
                }


                matchHashMap.put(match.getId(), match);
            }
        }
    }
    private boolean pointCase(Event event, boolean isAssist, long idAssist){
        if (idAssist == event.getPayload().getPlayerId()){
            failedEvents.put(event, FailedEvents.ASSISTTOYOURSELF);
            return false;
        }

        if (isAssist && event.getPayload().getValue() == 1) {
            failedEvents.put(event, FailedEvents.ASSISTANDONEPOINTER);
            return false;
        }else {
            if (endedMatches.containsKey(event.getGame())) {
                failedEvents.put(event, FailedEvents.ENDEDEARLIER);
                return false;
            } else {
                if (event.getPayload().getValue() >= 1 && event.getPayload().getValue() <= 3) {



                    Player player = getPlayerbyID(event.getPayload().getPlayerId());
                    Match match = matchHashMap.get(event.getGame());

                    if (match.getHost().getPlayerList().contains(player)) {
                        if (match.getGuest().getPlayerList().contains(getPlayerbyID(idAssist))){
                            failedEvents.put(event, FailedEvents.ASSISTTOOPPONENT);
                            return false;
                        }
                    }else{
                        if (match.getHost().getPlayerList().contains(getPlayerbyID(idAssist))){
                            failedEvents.put(event, FailedEvents.ASSISTTOOPPONENT);
                            return false;
                        }
                    }

                    try {
                        int currPoints = match.getPointsMap().get(player.getId());
                    } catch (NullPointerException e) {
                        failedEvents.put(event, FailedEvents.PLAYERNOTINTEAM);
                        return false;
                    }

                    int currPoints = match.getPointsMap().get(player.getId());
                    int newPoints = currPoints + event.getPayload().getValue();
                    match.getPointsMap().put(player.getId(), newPoints);
                    return true;
                } else {
                    failedEvents.put(event, FailedEvents.WRONGVALUE);
                    return false;
                }
            }
        }
    }


    private void jumpCase(Event event, boolean isAssist){
        if (isAssist){
            failedEvents.put(event,FailedEvents.ASSISTBEFOREHAND);
            return;
        }else {
            if (endedMatches.containsKey(event.getGame())) {
                failedEvents.put(event, FailedEvents.ENDEDEARLIER);
                return;
            } else {
                if (event.getPayload().getValue() == 1) {
                    Match match = matchHashMap.get(event.getGame());
                    Player player = getPlayerbyID(event.getPayload().getPlayerId());
                    try {
                        match.getJumpsMap().get(player.getId());
                    } catch (NullPointerException e) {
                        failedEvents.put(event, FailedEvents.PLAYERNOTINTEAM);
                        return;
                    }
                    int currJumps = match.getJumpsMap().get(player.getId());
                    int newJumps = currJumps + event.getPayload().getValue();
                    match.getJumpsMap().put(player.getId(), newJumps);
                } else {
                    failedEvents.put(event, FailedEvents.WRONGVALUE);
                    return;
                }
            }
        }
    }
    private void assistCase(Event event, boolean isAssist){
        if (isAssist){
            failedEvents.put(event,FailedEvents.ASSISTBEFOREHAND);
            return;
        }else {
            if (endedMatches.containsKey(event.getGame())) {
                failedEvents.put(event, FailedEvents.ENDEDEARLIER);
                return;
            } else {
                if (event.getPayload().getValue() == 1) {
                    Match match = matchHashMap.get(event.getGame());
                    Player player = getPlayerbyID(event.getPayload().getPlayerId());
                    try {
                        match.getAssistsMap().get(player.getId());
                    } catch (NullPointerException e) {
                        failedEvents.put(event, FailedEvents.PLAYERNOTINTEAM);
                        return;
                    }
                    int currAssists = match.getAssistsMap().get(player.getId());
                    int newAssists = currAssists + event.getPayload().getValue();
                    match.getAssistsMap().put(player.getId(), newAssists);
                } else {
                    failedEvents.put(event, FailedEvents.WRONGVALUE);
                    return;
                }
            }
        }
    }


    private List<Team> getTeamList() {
        return teamList;
    }
    private void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
    private List<Player> getPlayerList() {
        return playerList;
    }
    private void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
    private List<Event> getEventList() {
        return eventList;
    }
    private void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    private HashMap<Long, Match> getMatchHashMap() {
        return matchHashMap;
    }
    private void setMatchHashMap(HashMap<Long, Match> matchHashMap) {
        this.matchHashMap = matchHashMap;
    }
    private Map<Event, FailedEvents> getFailedEvents() {
        return failedEvents;
    }
    private void setFailedEvents(Map<Event, FailedEvents> failedEvents) {
        this.failedEvents = failedEvents;
    }
    private Map<Long, Match> getEndedMatches() {
        return endedMatches;
    }
    private void setEndedMatches(Map<Long, Match> endedMatches) {
        this.endedMatches = endedMatches;
    }

}

class CustomComparatorTeams implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        Storage storage = Storage.getInstance();
        if (storage.winPercentageByTeam(team1) > storage.winPercentageByTeam(team2)){
            return -1;
        }else if(storage.winPercentageByTeam(team1) < storage.winPercentageByTeam(team2)){
            return 1;
        }else {
            if ((storage.pointsScoredByTeam(team1) - storage.pointsReceivedByTeam(team1)) > (storage.pointsScoredByTeam(team2) - storage.pointsReceivedByTeam(team2))){
                return -1;
            }else{
                return 1;
            }
        }
    }
}