package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "datadeNascimento", required = false) String dataDeNascimento){
        return service.getAll(dataDeNascimento);
    }

    @GetMapping("/{id}")
    public Optional<Aluno> getAluno(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacoes(@PathVariable Long id){
        return service.getAllAvaliacaoFisica(id);
    }

    @PostMapping
    public Aluno createAluno(@RequestBody AlunoForm form){
        return service.create(form);
    }

    @PostMapping("/update/{id}")
    public Aluno UpdateAluno(@RequestBody AlunoUpdateForm form, @PathVariable Long id){
        return service.update(id, form);
    }
}



