package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;
import fr.sdv.cnit.university.api.service.PlayerService;
import fr.sdv.cnit.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/all")
    public List<PlayerEntity> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/add")
    public List<PlayerEntity> addTeam(@RequestBody PlayerEntity player) {
        try {
            playerService.createPlayer(player);
            return playerService.getAllPlayers();
        } catch (TeamInvalidException e) {
            throw new TeamInvalidException(e.getMessage());
        }
    }

    @PutMapping("/{playerId}")
    public List<PlayerEntity> updateTeam(@PathVariable("playerId") Long teamId, @RequestBody PlayerEntity player ) {
        try {
            playerService.updatePlayer(teamId, player.getName(), player.getNumber(), player.getPosition(), player.getTeam_id());
            return playerService.getAllPlayers();
        } catch (TeamInvalidException e) {
            throw new TeamInvalidException(e.getMessage());
        }
    }

    @DeleteMapping("/{playerId}")
    public List<PlayerEntity> deleteTeam(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return playerService.getAllPlayers();
    }
}
