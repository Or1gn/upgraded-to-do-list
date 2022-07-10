package com.project.Batnik.model.RQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRQ {
    private String username;

    private String email;

    private String password;
}
