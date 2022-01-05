package com.malli.Registraion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malli.Registraion.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
