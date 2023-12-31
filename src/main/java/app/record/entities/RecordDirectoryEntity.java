package app.record.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "record_directories")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class RecordDirectoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "parent_directory_id")
    private UUID parentId;

    @OneToMany(
            mappedBy = "directory",
            cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<RecordEntity> records;
}
