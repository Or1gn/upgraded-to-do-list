package com.project.Batnik.model.RQ;

import com.project.Batnik.model.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRQ {
    private String username;

    private String email;

    private String password;

    private Roles role;

}
