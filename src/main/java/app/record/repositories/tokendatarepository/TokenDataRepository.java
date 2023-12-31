package app.record.repositories.tokendatarepository;

import app.record.entities.TokenDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenDataRepository extends CrudRepository<TokenDataEntity, UUID> {
    Optional<TokenDataEntity> findByToken(UUID token);

    @Transactional(timeout = 7200, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Query("delete FROM TokenDataEntity t where t.used = true or t.expirationDate <= current_timestamp")
    void deleteAllInvalidTokens();
}
