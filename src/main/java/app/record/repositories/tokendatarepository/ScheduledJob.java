package app.record.repositories.tokendatarepository;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
public class ScheduledJob {
    private final TokenDataRepository dataRepository;

    @Scheduled(cron = "${token.refresh.cron}")
    public void deleteAllInvalidTokens() {
        dataRepository.deleteAllInvalidTokens();
    }
}
