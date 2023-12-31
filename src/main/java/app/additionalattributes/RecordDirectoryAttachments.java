package app.additionalattributes;

import app.record.dto.record.RecordShortData;
import app.record.dto.recorddirectory.RecordDirectoryShortData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecordDirectoryAttachments {
    private List<RecordDirectoryShortData> nestedDirectories;
    private List<RecordShortData> nestedRecords;
}
