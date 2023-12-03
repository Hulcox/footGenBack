package fr.sdv.cnit.university.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private String slogan;
    private String primary_color;
    private String secondary_color;
    private String logo;
    private Boolean is_deletable = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "team_id")
    private List<PlayerEntity> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getPrimaryColor() {
        return primary_color;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primary_color = primaryColor;
    }

    public String getSecondaryColor() {
        return secondary_color;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondary_color = secondaryColor;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getDeletable() {
        return is_deletable;
    }
    public void setDeletable(Boolean deletable) {
        this.is_deletable = deletable;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }
}
