package app.record.dto.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecordShortData {
    private UUID id;
    private String name;
}
