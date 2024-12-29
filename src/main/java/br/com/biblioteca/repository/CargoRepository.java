package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.Cargo;


public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
