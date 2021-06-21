package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService  {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Categoria categoria, Long codigo) {
		Optional<Categoria> categoriaSalva = buscarCategoriaPorCodigo(codigo);		
		BeanUtils.copyProperties(categoria, categoriaSalva.get(), "codigo");		
		return categoriaRepository.save(categoriaSalva.get());			
	}
	

	private Optional<Categoria> buscarCategoriaPorCodigo(Long codigo) {
		Optional<Categoria> categoriaSalva = categoriaRepository.findById(codigo);
		if (!categoriaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}	
}
