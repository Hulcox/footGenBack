package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;
import fr.sdv.cnit.university.api.service.PlayerService;
import fr.sdv.cnit.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService,PlayerService playerService) {
        this.teamService = teamService;this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<TeamDto> getAllTeams() {
        List<TeamEntity> teams = teamService.getAllTeams();
        return TeamDto.toDtoList(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable Long teamId) {
        TeamEntity team = teamService.getTeamById(teamId);
        return ResponseEntity.ok(TeamDto.fromEntityWithPlayer(team));
    }

    @PostMapping("/add")
    public List<TeamDto> addTeam(@RequestBody TeamDto teamDtoToCreate) {
        try {
            TeamEntity teamToCreate = TeamDto.toEntity(teamDtoToCreate);
            teamService.createTeam(teamToCreate);

            List<TeamEntity> teams = teamService.getAllTeams();

            return TeamDto.toDtoList(teams);
        } catch (TeamInvalidException e) {
            throw new TeamInvalidException(e.getMessage());
        }
    }

    @PutMapping("/{teamId}")
    public List<TeamDto> updateTeam(@PathVariable("teamId") Long teamId, @RequestBody TeamDto team ) {
        try {
            TeamEntity teamToUpdated = TeamDto.toEntity(team);
            teamService.updateTeam(teamId, teamToUpdated.getName(), teamToUpdated.getNickname(), teamToUpdated.getSlogan(), teamToUpdated.getPrimaryColor(), teamToUpdated.getSecondaryColor(), teamToUpdated.getLogo() );
            List<TeamEntity> teams = teamService.getAllTeams();
            return TeamDto.toDtoList(teams);
        } catch (TeamInvalidException e) {
            throw new TeamInvalidException(e.getMessage());
        }
    }

    @PatchMapping("/players/{teamId}")
    public ResponseEntity<TeamDto> updatePlayersInTeam(@PathVariable("teamId") Long teamId, @RequestBody List<PlayerEntity> players) {
        try {

            for(PlayerEntity player : playerService.getAllPlayers()) {
                System.out.println(player.getId());
                if(player.getTeam_id() == Integer.parseInt(String.valueOf(teamId))){
                    playerService.deletePlayer(player.getId());
                }
            }

            for(PlayerEntity player : players) {
                playerService.createPlayer(player);
            }

            TeamEntity team = teamService.getTeamById(teamId);
            return ResponseEntity.ok(TeamDto.fromEntityWithPlayer(team));
        } catch (TeamInvalidException e) {
            throw new TeamInvalidException(e.getMessage());
        }
    }

    @DeleteMapping("/{teamId}")
    public List<TeamDto> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        List<TeamEntity> teams = teamService.getAllTeams();
        return TeamDto.toDtoList(teams);
    }

}
