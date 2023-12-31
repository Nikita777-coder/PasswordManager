package app.record.servicies;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MetricCounter {
    private final Counter usedTokenCounter;
    private final Counter expiredTokenCounter;
    private final Timer timerBetweenTokenCreationAndItsFirstUsing;
    public void countGettingOfUsedToken() {
        usedTokenCounter.increment();
    }
    public void countGettingOfExpiredToken() {
        expiredTokenCounter.increment();
    }
    public void recordTokenPeriodBetweenCreationAndFirstUsing(LocalDateTime creationTime, LocalDateTime firstUseTime) {
        timerBetweenTokenCreationAndItsFirstUsing.record(Math.abs(firstUseTime.getNano() - creationTime.getNano()), TimeUnit.NANOSECONDS);
    }
    public MetricCounter(MeterRegistry meterRegistry) {
        usedTokenCounter = meterRegistry.counter("get_record_by_used_token_counter");
        expiredTokenCounter = meterRegistry.counter("get_record_by_expired_token_counter");
        timerBetweenTokenCreationAndItsFirstUsing = Timer
                .builder("timer_between_token_creation_and_its_first_using")
                .publishPercentiles(0.5, 0.8, 0.95, 0.99)
                .publishPercentileHistogram()
                .register(meterRegistry);
    }
}
