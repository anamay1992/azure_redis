package com.redis.mock;

import com.redis.model.Country;

public class MockData {

    public static Country getMockEmployee() {
        return Country
                .builder()
                .firstName("Angel")
                .secondName("Leonard")
                .age(30)
                .email("angel.leonard@gmail.com")
                .build();
    }

}
