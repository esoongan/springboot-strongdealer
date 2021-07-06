package com.strongdealer.mobile.dto.User;

import com.strongdealer.mobile.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
public class UserResponseTempDto {

    private final Long id;
    private final String email;

    public UserResponseTempDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

}
