package uz.ilmnajot.samps.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {

    @NotNull(message = "this place cannot be blank")
    private String fullName;

    @NotNull(message = "this place cannot be blank")
    private String username; //here is username is email

    @NotNull(message = "this place cannot be blank")
    private String password;
}
