package app.record.dto.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecordOutputData {
    private UUID id;
    private String name;

    private String login;

    private String password;

    private String url;

    private UUID directoryParentId;
}
