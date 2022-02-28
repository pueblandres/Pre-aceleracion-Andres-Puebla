package com.Alkemy.demo.auth.dto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UsuarioDTO {

    @Email(message = "Username must be an email")
    private String username;
    @Size(min = 8)
    private String password;
}
