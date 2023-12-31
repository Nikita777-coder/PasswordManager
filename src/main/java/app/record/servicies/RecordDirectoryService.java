package app.record.servicies;

import app.additionalattributes.RecordDirectoryAttachments;
import app.auth.servicies.user.UserService;
import app.record.dto.record.RecordShortData;
import app.record.dto.recorddirectory.RecordDirectoryData;
import app.record.dto.recorddirectory.RecordDirectoryShortData;
import app.record.entities.RecordDirectoryEntity;
import app.record.mappers.RecordDirectoryMapper;
import app.record.mappers.RecordMapper;
import app.record.repositories.RecordDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class RecordDirectoryService {
    private final RecordDirectoryRepository recordDirectoryRepository;
    private final UserService userService;
    private final RecordDirectoryMapper recordDirectoryMapper;
    private final RecordMapper recordMapper;
    public UUID createNew(RecordDirectoryData newRecordDirectoryData) {
        String currentUsername = userService.getCurrentUser().getUsername();

        if (recordDirectoryRepository.
                findByNameAndParentIdAndUserLogin(
                        newRecordDirectoryData.getName(),
                        newRecordDirectoryData.getParentDirectoryId(),
                        currentUsername).isPresent()
        ) {
            throw new IllegalArgumentException(String.format
                    (
                            "parent directory has already nested directory by name %s",
                            newRecordDirectoryData.getName()
                    )
            );
        }

        RecordDirectoryEntity newDirectory = new RecordDirectoryEntity();
        newDirectory.setName(newRecordDirectoryData.getName());
        newDirectory.setParentId(newRecordDirectoryData.getParentDirectoryId());
        newDirectory.setUserLogin(currentUsername);

        return recordDirectoryRepository.save(newDirectory).getId();
    }
    public RecordDirectoryEntity getRecordDirectoryById(UUID directoryId) {
        if (directoryId == null) {
            return null;
        }

        String currentUsername = userService.getCurrentUser().getUsername();
        Optional<RecordDirectoryEntity> foundDirectoryWrapper = recordDirectoryRepository.findByIdAndUserLogin(
                directoryId
                , currentUsername
        );

        if (foundDirectoryWrapper.isEmpty()) {
            throw new IllegalArgumentException("directory doesn't exist");
        }

        return foundDirectoryWrapper.get();
    }
    public List<RecordDirectoryShortData> getAllNestedDirectoriesInDirectory(UUID directoryId) {
        return recordDirectoryMapper.map(recordDirectoryRepository.findAllByParentIdAndUserLogin(
                directoryId, userService.getCurrentUser().getUsername()
        ));
    }
    public void deleteRecordDirectory(UUID directoryId) {
        recordDirectoryRepository.deleteById(directoryId);
    }
    public List<RecordShortData> getRecordsFromDirectory(UUID directoryId) {
        return recordMapper.mapToRecordShortData(getRecordDirectoryById(directoryId).getRecords());
    }
    public RecordDirectoryAttachments getAllAttachments(UUID directoryId) {
        RecordDirectoryAttachments recordDirectoryAttachments = new RecordDirectoryAttachments();

        recordDirectoryAttachments.setNestedRecords(getRecordsFromDirectory(directoryId));
        recordDirectoryAttachments.setNestedDirectories(getAllNestedDirectoriesInDirectory(directoryId));

        return recordDirectoryAttachments;
    }
}
