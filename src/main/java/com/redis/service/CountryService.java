package com.redis.service;

import com.redis.model.Country;
import java.util.List;

public interface CountryService {

    List<Country> findAll();
    Country findByCode(String code);
    Country save(Country country);
    void deleteByCode(String code);

}
