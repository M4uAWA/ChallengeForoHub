package com.m4uawa.forohub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.m4uawa.forohub.domain.topico.DTODTopico;
import com.m4uawa.forohub.domain.topico.DTORTopico;
import com.m4uawa.forohub.domain.topico.DTOUTopico;
import com.m4uawa.forohub.domain.topico.Topico;
import com.m4uawa.forohub.domain.topico.TopicoRepository;

import jakarta.validation.Valid;

@RestController
public class TopicosController {
    
    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping("/topicos")
    public ResponseEntity<DTODTopico> register(@RequestBody @Valid DTORTopico data, UriComponentsBuilder uriCB){
        var topico = new Topico(data);
        
        repository.save(topico);

        var uri = uriCB.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DTODTopico(topico));
    }

    @GetMapping("/topicos")
    public ResponseEntity<List<DTODTopico>> list() {
        List<DTODTopico> topicoList = repository.findAll()
            .stream()
            .map(DTODTopico::new)
            .toList();
        return ResponseEntity.ok(topicoList);
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<DTODTopico> getById(@PathVariable Long id) {
        Optional<Topico> topico = repository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new DTODTopico(topico.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping("/topicos/{id}")
    public ResponseEntity<DTODTopico> updateById(@PathVariable Long id,@RequestBody @Valid DTOUTopico data){
        Optional<Topico> topico = repository.findById(id);
        if(topico.isPresent()){
            topico.get().updateData(data);
            return ResponseEntity.ok(new DTODTopico(topico.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("/topicos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        Optional<Topico> topico = repository.findById(id);
        if(topico.isPresent()){
            repository.deleteById(topico.get().getId());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
