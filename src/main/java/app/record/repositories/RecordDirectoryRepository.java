package app.record.repositories;

import app.record.entities.RecordDirectoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecordDirectoryRepository extends CrudRepository<RecordDirectoryEntity, UUID> {
    Optional<RecordDirectoryEntity> findByIdAndUserLogin(UUID directoryId, String userLogin);
    Optional<RecordDirectoryEntity> findByNameAndParentIdAndUserLogin(
            @Param("dirName") String nestedDirectoryName,
            UUID directoryId,
            String currentUserLogin
    );
    List<RecordDirectoryEntity> findAllByParentIdAndUserLogin(UUID directoryId, String userLogin);
}
