package com.m4uawa.forohub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m4uawa.forohub.topico.DTOTopico;
import com.m4uawa.forohub.topico.Topico;
import com.m4uawa.forohub.topico.TopicoRepository;

import jakarta.validation.Valid;

@RestController
public class TopicosController {
    
    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping("/topicos")
    public void register(@RequestBody @Valid DTOTopico data){
        repository.save(new Topico(data));
    }

    @GetMapping("/topicos")
    public List<Topico> list() {
        return repository.findAll();
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<Topico> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
}
