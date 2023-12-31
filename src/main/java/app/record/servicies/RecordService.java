package app.record.servicies;

import app.auth.servicies.user.UserService;
import app.record.dto.record.RecordData;
import app.record.dto.record.RecordOutputData;
import app.record.dto.recorddirectory.RecordDirectoryShortData;
import app.record.entities.RecordEntity;
import app.record.mappers.RecordDirectoryMapper;
import app.record.mappers.RecordMapper;
import app.record.repositories.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserService userService;
    private final RecordMapper recordMapper;
    private final RecordDirectoryService recordDirectoryService;
    private final RecordDirectoryMapper recordDirectoryMapper;
    public RecordDirectoryShortData getRecordDirectory(UUID recordId) {
        RecordEntity resultRecordEntity = getRecordById(recordId);
        return recordDirectoryMapper.map(resultRecordEntity.getDirectory());
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RecordOutputData createRecord(RecordData record) {
        UserDetails currentUserDetails = userService.getCurrentUser();
        record.setLogin(currentUserDetails.getUsername());
        RecordEntity newRecord = recordMapper.recordDataToEntity(record);
        addDirectoryToNewRecord(newRecord, record.getDirectoryParentId());

        return recordMapper.recordEntityToOutputData(recordRepository.save(newRecord));
    }
    public Page<RecordOutputData> getRecords(Pageable pageable) {
        UserDetails currentUserDetails = userService.getCurrentUser();
        Page<RecordEntity> pageOfRecordEntities = recordRepository.findAllByLogin(currentUserDetails.getUsername(), pageable);
        List<RecordOutputData> outputRecords = recordMapper.mapToRecordOutputData(pageOfRecordEntities.getContent());

        return new PageImpl<>(outputRecords, pageable, pageOfRecordEntities.getTotalElements());
    }
    public RecordData getRecordByIdNotInCurrentUser(UUID id) {
        Optional<RecordEntity> resultEntity = recordRepository.findById(id);

        if (resultEntity.isEmpty()) {
            throw new NoSuchElementException(String.format("there is no record by id %s", id));
        }

        return recordMapper.recordEntityToRecordData(resultEntity.get());
    }
    public RecordOutputData getRecordDataById(UUID id) {
        UserDetails currentUserDetails = userService.getCurrentUser();
        Optional<RecordEntity> resultEntity = recordRepository.findByLoginAndId(currentUserDetails.getUsername(), id);

        if (resultEntity.isEmpty()) {
            throw new NoSuchElementException(String.format("there is no record by id %s", id));
        }

        return recordMapper.recordEntityToOutputData(getRecordById(id));
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RecordOutputData updateRecordById(UUID id, RecordEntity record) {
        RecordEntity foundEntity = getRecordById(id);

        record.setId(foundEntity.getId());

        return recordMapper.recordEntityToOutputData(recordRepository.save(record));
    }
    private RecordEntity getRecordById(UUID id) {
        UserDetails currentUserDetails = userService.getCurrentUser();
        Optional<RecordEntity> resultEntity = recordRepository.findByLoginAndId(currentUserDetails.getUsername(), id);

        if (resultEntity.isEmpty()) {
            throw new NoSuchElementException(String.format("there is no record by id %s", id));
        }

        return resultEntity.get();
    }
    private void addDirectoryToNewRecord(RecordEntity newRecord, UUID directoryParentId) {
        if (directoryParentId == null) {
            return;
        }

        newRecord.setDirectory(recordDirectoryService.getRecordDirectoryById(directoryParentId));
    }
}