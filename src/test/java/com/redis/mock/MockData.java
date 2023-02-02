package com.redis.mock;

import com.redis.model.Employee;

public class MockData {

    public static Employee getMockEmployee() {
        return Employee
                .builder()
                .firstName("Angel")
                .secondName("Leonard")
                .age(30)
                .email("angel.leonard@gmail.com")
                .build();
    }

}
