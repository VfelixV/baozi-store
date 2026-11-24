package br.com.baozi.controller;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class ErroController {
 @ExceptionHandler(DataIntegrityViolationException.class)
 public ResponseEntity<Map<String,String>> conflito(DataIntegrityViolationException erro) {
  return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensagem","Registro vinculado a um pedido. Exclua o pedido primeiro."));
 }
}
