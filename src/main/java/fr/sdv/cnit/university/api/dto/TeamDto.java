package fr.sdv.cnit.university.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.sdv.cnit.university.api.entity.PlayerEntity;
import fr.sdv.cnit.university.api.entity.TeamEntity;

public record TeamDto(Long id, String name, String nickname, String slogan, String primaryColor, String secondaryColor, String logo, List<PlayerEntity> players) {

    public static TeamDto fromEntity(TeamEntity team) {
        return new TeamDto(team.getId(), team.getName(), team.getNickname(), team.getSlogan(), team.getPrimaryColor(), team.getSecondaryColor(), team.getLogo(), new ArrayList<>());
    }

    public static TeamDto fromEntityWithPlayer(TeamEntity team) {
        return new TeamDto(team.getId(), team.getName(), team.getNickname(), team.getSlogan(), team.getPrimaryColor(), team.getSecondaryColor(), team.getLogo(), team.getPlayers());
    }

    public static List<TeamDto> toDtoList(List<TeamEntity> teams) {
        return teams.stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static TeamEntity toEntity(TeamDto teamDto) {
        TeamEntity team = new TeamEntity();
        team.setId(teamDto.id());
        team.setName(teamDto.name());
        team.setNickname(teamDto.nickname());
        team.setSlogan(teamDto.slogan());
        team.setPrimaryColor(teamDto.primaryColor());
        team.setSecondaryColor(teamDto.secondaryColor());
        team.setLogo(teamDto.logo());
        team.setPlayers(teamDto.players());
        return team;
    }
}
