package fr.sdv.cnit.university.api.service;

import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;
import fr.sdv.cnit.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sdv.cnit.university.api.exception.TeamNotFoundException;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;

import java.util.List;
import java.util.Optional;

import java.util.List;
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public List<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    public TeamEntity getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + id));
    }

    public void createTeam(TeamEntity team) {
        if (team.getSlogan() == null || team.getSlogan().isEmpty()) {
            throw new TeamInvalidException("Slogan cannot be null or empty");
        }
        teamRepository.save(team);
    }

    public void updateTeam(Long id, String name, String nickname, String slogan, String primaryColor, String secondaryColor, String logo ) {
        TeamEntity existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + id));

        existingTeam.setName(name);
        existingTeam.setNickname(nickname);
        existingTeam.setSlogan(slogan);
        existingTeam.setPrimaryColor(primaryColor);
        existingTeam.setSecondaryColor(secondaryColor);
        existingTeam.setLogo(logo);

        teamRepository.save(existingTeam);
    }

    public void updatePlayerInTeam(Long id, List<PlayerEntity> players ) {
        TeamEntity existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + id));

        existingTeam.setPlayers(players);

        teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
