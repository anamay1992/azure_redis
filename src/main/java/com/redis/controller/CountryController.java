package com.redis.controller;

import com.redis.model.Country;
import com.redis.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Country> findByCode(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<Country> save(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteByCode(@PathVariable String code) {
        countryService.deleteByCode(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
