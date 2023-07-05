package uz.ilmnajot.samps.abstractClass;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // without this, annotations under will not work;
public class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    private Timestamp createdBy;

    @LastModifiedBy
    private Timestamp updatedBy;

    private Boolean deleted;
}

