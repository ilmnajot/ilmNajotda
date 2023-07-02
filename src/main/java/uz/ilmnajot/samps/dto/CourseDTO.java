package uz.ilmnajot.samps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.samps.entity.Attachment;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CourseDTO {
    private String name;

    private String description;

    private String price;


    private Attachment attachment;
}
