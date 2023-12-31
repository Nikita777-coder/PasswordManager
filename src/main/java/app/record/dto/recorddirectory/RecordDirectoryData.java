package app.record.dto.recorddirectory;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecordDirectoryData {
    @NotBlank(message = "name of new directory can't be empty")
    private String name;

    private UUID parentDirectoryId;
}
