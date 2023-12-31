package app.record.mappers;

import app.record.dto.record.RecordData;
import app.record.dto.record.RecordOutputData;
import app.record.dto.record.RecordShortData;
import app.record.entities.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "directory")
    RecordEntity recordDataToEntity(RecordData recordData);
    @Mapping(source = "directory.id", target = "directoryParentId")
    RecordOutputData recordEntityToOutputData(RecordEntity recordEntity);
    @Mapping(source = "directory.id", target = "directoryParentId")
    RecordData recordEntityToRecordData(RecordEntity recordEntity);
    RecordShortData recordEntityToRecordShortData(RecordEntity recordEntity);
    List<RecordShortData> mapToRecordShortData(List<RecordEntity> recordEntities);
    List<RecordOutputData> mapToRecordOutputData(List<RecordEntity> recordEntities);
}
