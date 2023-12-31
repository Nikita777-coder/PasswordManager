package app.record.controllers;

import app.record.dto.recordsharing.RecordForSharingBodyParams;
import app.record.dto.record.RecordOutputData;
import app.record.servicies.RecordSharingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/sharing")
public class RecordSharingController {
    private final RecordSharingService recordSharingService;
    @PostMapping("/prepare_record/{id}")
    public @ResponseBody ResponseEntity<UUID> createRecordForSharing(@PathVariable UUID id,
                                                                     @RequestBody(required = false) RecordForSharingBodyParams bodyParams) {
        UUID resultToken = recordSharingService.createRecordForSharing(id,
                bodyParams == null ? new RecordForSharingBodyParams() : bodyParams);

        return new ResponseEntity<>(resultToken, HttpStatus.OK);
    }

    @GetMapping("/get_record/{token}")
    public @ResponseBody ResponseEntity<RecordOutputData> getRecordFromToken(@PathVariable UUID token) {
        RecordOutputData resultEntity = recordSharingService.getRecordBySharingToken(token);

        return new ResponseEntity<>(resultEntity, HttpStatus.OK);
    }
}