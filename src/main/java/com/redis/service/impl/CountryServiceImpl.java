package com.redis.service.impl;

import com.redis.model.Country;
import com.redis.repository.CountryRepository;
import com.redis.service.CountryService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RedissonClient redissonClient;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "countries")
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "countries", key = "#code", unless = "#result == null")
    public Country findByCode(String code) {
        return countryRepository.findByCode(code);
    }

    @Override
    @Transactional
    @CachePut(value = "countries", key = "#country.code")
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional
    @CacheEvict(value = "countries", key = "#code")
    public void deleteByCode(String code) {
        countryRepository.deleteByCode(code);
    }

}
