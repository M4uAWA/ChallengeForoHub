package com.m4uawa.forohub.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m4uawa.forohub.topico.DTOTopico;
import com.m4uawa.forohub.topico.Topico;
import com.m4uawa.forohub.topico.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    
    private TopicoRepository repository;

    @PostMapping
    public void register(@RequestBody DTOTopico data){
        repository.save(new Topico(data));
    }

    
}
