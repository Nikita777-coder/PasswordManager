package app.record.servicies;

import app.record.dto.recordsharing.RecordForSharingBodyParams;
import app.record.entities.TokenDataEntity;
import app.record.repositories.tokendatarepository.TokenDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class TokenDataService {
    private final MetricCounter metricCounter;

    @Value("${token.default.ttl}")
    private int hoursOfValidToken;

    private final TokenDataRepository tokenDataRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UUID createToken(UUID id, RecordForSharingBodyParams recordForSharingBodyParams) {
        TokenDataEntity tokenDataEntity = new TokenDataEntity();

        tokenDataEntity.setToken(UUID.randomUUID());
        tokenDataEntity.setUsed(false);
        tokenDataEntity.setRecordLink(id);
        tokenDataEntity.setCreationTime(LocalDateTime.now());

        LocalDateTime expireDate = recordForSharingBodyParams.getExpirationDate();
        tokenDataEntity.setExpirationDate(expireDate != null ? expireDate : LocalDateTime.now().plusHours(hoursOfValidToken));

        return tokenDataRepository.save(tokenDataEntity).getToken();
    }
    public TokenDataEntity getTokenDataEntityByToken(UUID token) {
        return getTokenDataEntity(token);
    }
    public void checkTokenDataEntityValidation(TokenDataEntity tokenDataEntity) {
        checkUsedStatusOfToken(tokenDataEntity);
        checkTokenDateEntityDate(tokenDataEntity);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TokenDataEntity updateUsedStatus(TokenDataEntity tokenDataEntity) {
        tokenDataEntity.setUsed(true);
        recordTokenPeriodBetweenCreationAndFirstUsing(tokenDataEntity);
        return tokenDataRepository.save(tokenDataEntity);
    }
    private TokenDataEntity getTokenDataEntity(UUID token) {
        Optional<TokenDataEntity> tokenDataShell = tokenDataRepository.findByToken(token);

        if (tokenDataShell.isEmpty()) {
            throw new IllegalArgumentException("Token doesn't exist");
        }

        return tokenDataShell.get();
    }
    private void checkUsedStatusOfToken(TokenDataEntity tokenDataEntity) {
        if (tokenDataEntity.getUsed()) {
            metricCounter.countGettingOfUsedToken();
            throw new IllegalArgumentException("token used");
        }
    }
    private void checkTokenDateEntityDate(TokenDataEntity tokenDataEntity) {
        if (!tokenDataEntity.getExpirationDate().isAfter(LocalDateTime.now())) {
            metricCounter.countGettingOfExpiredToken();
            throw new IllegalArgumentException("token expired");
        }
    }
    private void recordTokenPeriodBetweenCreationAndFirstUsing(TokenDataEntity tokenDataEntity) {
        LocalDateTime creationTokenTime = tokenDataEntity.getCreationTime();
        LocalDateTime firstUseTokenTime = LocalDateTime.now();
        metricCounter.recordTokenPeriodBetweenCreationAndFirstUsing(creationTokenTime, firstUseTokenTime);
    }
}
