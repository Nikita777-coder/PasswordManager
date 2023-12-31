package app.record.controllers;

import app.additionalattributes.RecordDirectoryAttachments;
import app.record.dto.record.RecordShortData;
import app.record.dto.recorddirectory.RecordDirectoryData;
import app.record.dto.recorddirectory.RecordDirectoryShortData;
import app.record.servicies.RecordDirectoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@AllArgsConstructor
@RequestMapping("/record_directory")
public class RecordDirectoryController {
    private final RecordDirectoryService recordDirectoryService;
    @PostMapping(value = "/create")
    public ResponseEntity<UUID> createRecordDirectory(@Valid @RequestBody RecordDirectoryData recordDirectoryData) {
        UUID recordDirectoryId = recordDirectoryService.createNew(recordDirectoryData);

        return new ResponseEntity<>(recordDirectoryId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get_all_nested_directories")
    public ResponseEntity<List<RecordDirectoryShortData>> getAllDirectoriesFromDirectory(@RequestParam(required = false)
                                                                                          UUID directoryId) {
        return new ResponseEntity<>(recordDirectoryService.getAllNestedDirectoriesInDirectory(directoryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecordDirectory(@PathVariable UUID id) {
        recordDirectoryService.deleteRecordDirectory(id);
    }

    @GetMapping("/get_records")
    public ResponseEntity<List<RecordShortData>> getRecordsFromDirectory(@RequestParam(required = false) UUID id) {
        return new ResponseEntity<>(recordDirectoryService.getRecordsFromDirectory(id), HttpStatus.OK);
    }

    @GetMapping("/get_all_attachments")
    public ResponseEntity<RecordDirectoryAttachments> getAllAttachments(@RequestParam(required = false) UUID id) {
        return new ResponseEntity<>(recordDirectoryService.getAllAttachments(id), HttpStatus.OK);
    }
}
