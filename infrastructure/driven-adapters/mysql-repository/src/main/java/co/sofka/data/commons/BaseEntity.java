package co.sofka.data.commons;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active", columnDefinition = "tinyint default 1")
    private Boolean isActive;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "creator_user_id", updatable = false)
    private String createUserId;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updater_user_id")
    private String updateUserId;
    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.isActive = Boolean.TRUE;
        this.createdAt = new Date(System.currentTimeMillis());

    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }

}
