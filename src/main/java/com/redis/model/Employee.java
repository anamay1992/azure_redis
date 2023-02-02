package com.redis.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee {

    private String firstName;
    private String secondName;
    private Integer age;
    private String email;

}
