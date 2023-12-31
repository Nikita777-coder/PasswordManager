package app.record.mappers;

import app.record.dto.recorddirectory.RecordDirectoryShortData;
import app.record.entities.RecordDirectoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordDirectoryMapper {
    RecordDirectoryShortData map(RecordDirectoryEntity recordDirectoryEntity);
    List<RecordDirectoryShortData> map(List<RecordDirectoryEntity> recordDirectoryEntities);
}
