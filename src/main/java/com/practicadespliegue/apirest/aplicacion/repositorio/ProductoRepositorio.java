package com.practicadespliegue.apirest.aplicacion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicadespliegue.apirest.aplicacion.entities.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

}
