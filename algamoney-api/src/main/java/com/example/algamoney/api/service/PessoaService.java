package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService  {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * Atualizar Pessoa
	 * @param pessoa
	 * @param codigo
	 * @return Pessoa
	 */
	public Pessoa atualizar(Pessoa pessoa, Long codigo) {
		Optional<Pessoa> pessoaSalva = buscarPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo");		
		return pessoaRepository.save(pessoaSalva.get());			
	}	
	
	/**
	 * Atualizar Ativo
	 * @param codigo
	 * @param ativo
	 * @return Pessoa
	 */
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Optional<Pessoa> pessoaSalva = buscarPessoaPorCodigo(codigo);	
		pessoaSalva.get().setAtivo(ativo);
		pessoaRepository.save(pessoaSalva.get());
	}

	
	private Optional<Pessoa> buscarPessoaPorCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		if (pessoaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
