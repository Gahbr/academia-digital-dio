package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService{

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        try {
            aluno.setNome(form.getNome());
            aluno.setBairro(form.getBairro());
            aluno.setDataDeNascimento(form.getDataDeNascimento());
            aluno.setCpf(form.getCpf());
        }catch (Error error){
            System.out.println(error.getMessage());
        }
        return repository.save(aluno);
    }

    @Override
    public Optional<Aluno> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if(dataDeNascimento ==null){
            return repository.findAll();
        }else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localDate);
        }

    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = repository.findById(id).get();
        try {
            aluno.setNome(formUpdate.getNome());
            aluno.setBairro(formUpdate.getBairro());

        }catch (Error error){
            System.out.println(error.getMessage());
        }
        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(Long id) {
      Aluno aluno =  repository.findById(id).get();
        return aluno.getAvaliacoes();
    }
}
