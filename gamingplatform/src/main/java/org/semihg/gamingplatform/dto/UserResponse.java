package org.semihg.gamingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semihg.gamingplatform.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String message;
    private User user;

}
