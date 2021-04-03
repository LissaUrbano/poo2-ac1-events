package com.facens.event.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import com.facens.event.services.exception.DataFinalInvalidaException;
import com.facens.event.services.exception.DataInicialInvalidaException;
import com.facens.event.services.exception.HoraFinalInvalidaException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class EventsExeptionHandler extends ResponseEntityExceptionHandler{

    
    @ExceptionHandler({DataInicialInvalidaException.class})
	public ResponseEntity<Object> handleDataInicialInvalidaException (DataInicialInvalidaException ex, WebRequest request){
		String mensagemUsuario = messageSource().getMessage("event.data-inicio-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request) ;
	}

    @ExceptionHandler({DataFinalInvalidaException.class})
	public ResponseEntity<Object> handleDataFinalInvalidaException (DataFinalInvalidaException ex, WebRequest request){
		String mensagemUsuario = messageSource().getMessage("event.data-final-antes-data-inicial", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request) ;
	}

    @ExceptionHandler({HoraFinalInvalidaException.class})
	public ResponseEntity<Object> handleHoraFinalInvalidaException (HoraFinalInvalidaException ex, WebRequest request){
		String mensagemUsuario = messageSource().getMessage("event.hora-final-antes-data-inicial", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request) ;
	}
	
	public static class Erro{
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
}
