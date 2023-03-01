package com.redis.repository;

import com.redis.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByCode(String code);

    @Transactional
    void deleteByCode(String code);

}
