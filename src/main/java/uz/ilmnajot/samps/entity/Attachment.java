package uz.ilmnajot.samps.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.samps.abstractClass.AbsEntity;

@Getter
@Setter
@Entity(name = "attachments")
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends AbsEntity {

    private String name;

    private String description;

    private byte[] size;

}
