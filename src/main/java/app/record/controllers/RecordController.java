package app.record.controllers;

import app.record.dto.record.RecordData;
import app.record.dto.record.RecordOutputData;
import app.record.dto.recorddirectory.RecordDirectoryShortData;
import app.record.entities.RecordEntity;
import app.record.servicies.RecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;
    @PostMapping
    public @ResponseBody ResponseEntity<RecordOutputData> createRecord(@Valid @RequestBody RecordData record) {
        RecordOutputData result = recordService.createRecord(record);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public @ResponseBody ResponseEntity<Page<RecordOutputData>> getRecords(
            @ParameterObject Pageable pageable
    ) {
        return new ResponseEntity<>(recordService.getRecords(pageable), HttpStatus.OK);
    }

    @GetMapping("/{record_id}")
    public @ResponseBody ResponseEntity<RecordOutputData> getRecordById(@PathVariable UUID record_id) {
        RecordOutputData resultRecordData = recordService.getRecordDataById(record_id);

        return new ResponseEntity<>(resultRecordData, HttpStatus.OK);
    }

    @GetMapping("/get_directory_info_of_record/{record_id}")
    public @ResponseBody ResponseEntity<RecordDirectoryShortData> getRecordDirectoryInfo(@PathVariable UUID record_id) {
        RecordDirectoryShortData recordDirectoryInfo = recordService.getRecordDirectory(record_id);

        return new ResponseEntity<>(recordDirectoryInfo, HttpStatus.OK);
    }

    @PatchMapping("/{record_id}")
    public @ResponseBody ResponseEntity<RecordOutputData> updateRecordById(
            @PathVariable UUID record_id,
            @Valid @RequestBody RecordEntity record)
    {
        RecordOutputData resultEntity = recordService.updateRecordById(record_id, record);

        return new ResponseEntity<>(resultEntity, HttpStatus.ACCEPTED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}