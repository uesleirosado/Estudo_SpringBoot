package com.example.algamoney.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@ControllerAdvice
public class AlgamoneyExceptionhandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		String msgUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDesenv = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDesenv), headers, status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAcessExcepition(EmptyResultDataAccessException ex, WebRequest request) {
		
		String msgUsuario = messageSource.getMessage("recurso.nao.encontrado", null, LocaleContextHolder.getLocale());
		String msgDesenv = ex.toString();		
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDesenv), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationExcepition(DataIntegrityViolationException ex, WebRequest request) {
		
		String msgUsuario = messageSource.getMessage("recurso.operacao.nao.permitido", null, LocaleContextHolder.getLocale());
		String msgDesenv = ExceptionUtils.getRootCauseMessage(ex);
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDesenv), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex, WebRequest request) {
		
		String msgUsuario = messageSource.getMessage("pessoa.inexistente.inativo", null, LocaleContextHolder.getLocale());
		String msgDesenv = ex.toString();
		return handleExceptionInternal(ex, new Erro(msgUsuario, msgDesenv), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	private List<Erro> criarListaErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDesenv = fieldError.toString();
			erros.add(new Erro(msgUsuario, msgDesenv));			
		}
		return erros;
		
	}
	
	public static class Erro {
		
		private String msgUsuario;
		private String msgDesenv;
		
		public Erro(String msgUsuario, String msgDesenv) {
			this.msgUsuario = msgUsuario;
			this.msgDesenv = msgDesenv;
		}

		public String getMsgUsuario() {
			return msgUsuario;
		}

		public String getMsgDesenv() {
			return msgDesenv;
		}
	}
}
