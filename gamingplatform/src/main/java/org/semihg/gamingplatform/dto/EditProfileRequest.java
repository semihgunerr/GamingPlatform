package org.semihg.gamingplatform.dto;

import lombok.Data;

@Data
public class EditProfileRequest {
    private Long id;
    private String username;
    private String password;
}