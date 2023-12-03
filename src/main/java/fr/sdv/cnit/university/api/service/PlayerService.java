package fr.sdv.cnit.university.api.service;

import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;
import fr.sdv.cnit.university.api.exception.TeamNotFoundException;
import fr.sdv.cnit.university.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void createPlayer(PlayerEntity player) {
        playerRepository.save(player);
    }

    public void updatePlayer(Long id, String name, Integer number, String position, Integer team_id) {
        PlayerEntity existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + id));

        existingPlayer.setName(name);
        existingPlayer.setNumber(number);
        existingPlayer.setPosition(position);
        existingPlayer.setTeam_id(team_id);

        playerRepository.save(existingPlayer);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
