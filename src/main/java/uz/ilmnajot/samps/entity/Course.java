package uz.ilmnajot.samps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.samps.abstractClass.AbsEntity;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "courses")
@Getter
@Setter
public class Course extends AbsEntity {
    
    private String name;

    private String description;

    private String price;

    @OneToOne
    private Attachment attachment;


    public Course(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;

    }
}
