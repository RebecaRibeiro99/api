package com.residencia.firstapi.controller;

import com.residencia.firstapi.entity.Autor;
import com.residencia.firstapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> findAll(){
        return ResponseEntity.ok().body(autorService.findAll());
    }

    @GetMapping("/{id}")
    public Autor findById(@PathVariable(value = "id") Integer id){
        return autorService.findById(id);
    }
    @GetMapping("/nome/{nomeAutor}")
    public Autor findByNome(@PathVariable(value = "nomeAutor") String nome){
        return autorService.findByNome(nome);
    }

    @PostMapping
    public Autor save(@RequestBody Autor autor){
        return autorService.save(autor);
    }

    @PutMapping("/{id}")
    public Autor update(@PathVariable Integer Id, @RequestBody Autor autor) {
        return autorService.update(autor, Id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer autorId){
        autorService.delete(autorId);
    }
}