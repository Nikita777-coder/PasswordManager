package app.record.servicies;

import app.record.dto.record.RecordData;
import app.record.dto.record.RecordOutputData;
import app.record.dto.recordsharing.RecordForSharingBodyParams;
import app.record.entities.TokenDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordSharingService {
    private final TokenDataService tokenDataService;
    private final RecordService recordService;
    public UUID createRecordForSharing(UUID id, RecordForSharingBodyParams recordForSharingBodyParams) {
        RecordOutputData foundEntity = recordService.getRecordDataById(id);

        return tokenDataService.createToken(foundEntity.getId(), recordForSharingBodyParams);
    }
    public RecordOutputData getRecordBySharingToken(UUID token) {
        TokenDataEntity tokenDataEntity = tokenDataService.getTokenDataEntityByToken(token);
        tokenDataService.checkTokenDataEntityValidation(tokenDataEntity);

        tokenDataEntity = tokenDataService.updateUsedStatus(tokenDataEntity);
        RecordData recordData = recordService.getRecordByIdNotInCurrentUser(tokenDataEntity.getRecordLink());

        return recordService.createRecord(recordData);
    }
}
