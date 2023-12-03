package fr.sdv.cnit.university.api.repository;

import fr.sdv.cnit.university.api.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
