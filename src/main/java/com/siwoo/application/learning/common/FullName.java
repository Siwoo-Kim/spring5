package com.siwoo.application.learning.common;

import lombok.*;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FullName {
    private String lastName;
    private String firstName;

    public String toString(){
        return firstName+", "+lastName;
    }

}
