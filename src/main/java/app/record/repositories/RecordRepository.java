package app.record.repositories;

import app.record.entities.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, UUID>, CrudRepository<RecordEntity, UUID> {
    @Query("select r from RecordEntity r where r.login = :login and r.directory.id = :directoryId")
    List<RecordEntity> findAllByLoginAndAndDirectoryId(String login, UUID directoryId);
    @Query("select r from RecordEntity r where r.login = :login and r.directory.id = null")
    List<RecordEntity> findAllByLogin(String login);
    Page<RecordEntity> findAllByLogin(String login, Pageable pageable);
    Optional<RecordEntity> findByLoginAndId(String login, UUID id);
}
