package com.converter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.converter.Model.Mesto;

public interface MestoRepository extends JpaRepository<Mesto, Integer>{

}
