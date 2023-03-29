package com.mitraecp.api.mitratests.feature.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogin {

    private String username;
    private String password;
}
