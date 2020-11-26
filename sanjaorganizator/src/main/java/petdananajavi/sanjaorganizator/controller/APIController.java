package petdananajavi.sanjaorganizator.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import petdananajavi.sanjaorganizator.model.Match;
import petdananajavi.sanjaorganizator.model.databasemodels.Player;
import petdananajavi.sanjaorganizator.model.Storage;
import petdananajavi.sanjaorganizator.model.overviewobjects.MatchOverview;
import petdananajavi.sanjaorganizator.model.overviewobjects.PlayerStatsOverview;
import petdananajavi.sanjaorganizator.model.overviewobjects.TeamResultsOverview;

@RestController
public class APIController {

    private Storage storage = Storage.getInstance();


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/matches")
    @ResponseBody
    public List<MatchOverview> allMatches(){
        return storage.getAllMatchOverviews();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/matches/{id}")
    @ResponseBody
    public Match getMatchByID(@PathVariable long id){
        Match response = storage.getMatchByID(id);
        if (response == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return response;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/player/{id}")
    @ResponseBody
    public PlayerStatsOverview getPlayerStats(@PathVariable long id){
        PlayerStatsOverview response = storage.getPlayerStats(id);
        if (response == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return response;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/best")
    @ResponseBody
    public Map<String,Map<Player,Integer>> getBestPlayers(){

        return storage.getBestPlayers();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/dd")
    @ResponseBody
    public Map<Player, Integer> getMostDoubleDoubles(){

        return storage.findMostDoubleDoubles();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/table")
    @ResponseBody
    public List<TeamResultsOverview> getTable(){
        return storage.getTable();
    }

}