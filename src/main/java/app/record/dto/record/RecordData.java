package app.record.dto.record;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecordData {
    @NotBlank(message = "name of record can't be empty!")
    private String name;

    private String login;

    @NotBlank(message = "password of record can't be empty!")
    private String password;

    private String url;

    private UUID directoryParentId;
}
