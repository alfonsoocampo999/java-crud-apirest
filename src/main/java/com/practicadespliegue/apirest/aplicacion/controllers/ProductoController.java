package com.practicadespliegue.apirest.aplicacion.controllers;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practicadespliegue.apirest.aplicacion.repositorio.ProductoRepositorio;

//import scala.concurrent.impl.FutureConvertersImpl.P;

import com.practicadespliegue.apirest.aplicacion.entities.Producto;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable long id){
        return productoRepositorio.findById(id)
        .orElseThrow( () -> new RuntimeException("No se encontro el producto con el id: "+id));

    }

    @PostMapping
    public Producto creaProducto(@RequestBody Producto producto){
        return productoRepositorio.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable long id, @RequestBody Producto detallesProducto){

        Producto producto = productoRepositorio.findById(id)
        .orElseThrow( () -> new RuntimeException("No se encontro el producto con el id: "+id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepositorio.save(producto);
   }

   @DeleteMapping("/{id}")
   public String eliminarUnProducto(@PathVariable long id){

    Producto producto = productoRepositorio.findById(id)
        .orElseThrow( () -> new RuntimeException("No se encontro el producto con el id: "+id));

        productoRepositorio.delete(producto);

        return "Se elimino corectamente el producto con id: "+id;

   }


}
