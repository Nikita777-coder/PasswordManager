package app.record.dto.recordsharing;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RecordForSharingBodyParams {
    @Future
    private LocalDateTime expirationDate;
}
