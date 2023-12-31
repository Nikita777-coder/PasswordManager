package app.record.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tokens")
@Getter
@Setter
@NoArgsConstructor
public class TokenDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    private Boolean used;

    @Column(name = "record_link")
    private UUID recordLink;
}
