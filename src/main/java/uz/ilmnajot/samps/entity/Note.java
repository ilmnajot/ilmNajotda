package uz.ilmnajot.samps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.samps.abstractClass.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "note")

public class Note extends AbsEntity {

    private String name;

    private String content;

    @ManyToOne
    private User user;
}
