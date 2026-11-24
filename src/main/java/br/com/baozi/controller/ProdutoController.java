package br.com.baozi.controller;
import br.com.baozi.model.Produto;
import br.com.baozi.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;
@RestController @RequestMapping("/produtos")
public class ProdutoController {
 private final ProdutoRepository repository;
 public ProdutoController(ProdutoRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Produto> criar(@Valid @RequestBody Produto entrada) {
  entrada.setId(null);
  Produto salvo = repository.save(entrada);
  return ResponseEntity.created(URI.create("/produtos/" + salvo.getId())).body(salvo);
 }
 @GetMapping public List<Produto> listar() { return repository.findAll(); }
 @GetMapping("/{id}") public Produto buscar(@PathVariable Long id) {
  return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id) {
  repository.delete(buscar(id));
  return ResponseEntity.noContent().build();
 }
}
