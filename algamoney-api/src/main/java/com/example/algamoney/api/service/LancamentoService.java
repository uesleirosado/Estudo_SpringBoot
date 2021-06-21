package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService  {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa.isEmpty() || !pessoa.get().getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		} else {
			return lancamentoRepository.save(lancamento);
		}
	}
	
	
	
	/**
	 * Atualizar Pessoa
	 * @param pessoa
	 * @param codigo
	 * @return Pessoa
	 */
	public Lancamento atualizar(Lancamento lancamento, Long codigo) {
		Optional<Lancamento> lancamentoOriginal = buscarLancamentoPorCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoOriginal.get(), "codigo");		
		return lancamentoRepository.save(lancamentoOriginal.get());			
	}	
	
	
	private Optional<Lancamento> buscarLancamentoPorCodigo(Long codigo) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		if (lancamento.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamento;
	}
}
