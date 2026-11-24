package br.com.baozi.controller;
import br.com.baozi.model.*;
import br.com.baozi.repository.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.*;
import java.net.URI;
import java.util.List;
@RestController @RequestMapping("/pedidos")
public class PedidoController {
 public record Entrada(@NotNull Long clienteId, @NotNull Long produtoId, @NotNull @Min(1) Integer quantidade) {}
 public record Saida(Long id, Long clienteId, Long produtoId, Integer quantidade) {}
 private final PedidoRepository pedidos;
 private final ClienteRepository clientes;
 private final ProdutoRepository produtos;
 public PedidoController(PedidoRepository pedidos, ClienteRepository clientes, ProdutoRepository produtos) {
  this.pedidos=pedidos; this.clientes=clientes; this.produtos=produtos;
 }
 private Saida saida(Pedido p) { return new Saida(p.getId(), p.getCliente().getId(), p.getProduto().getId(), p.getQuantidade()); }
 private Pedido buscarEntidade(Long id) {
  return pedidos.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
 }
 @PostMapping public ResponseEntity<Saida> criar(@Valid @RequestBody Entrada entrada) {
  Cliente cliente=clientes.findById(entrada.clienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
  Produto produto=produtos.findById(entrada.produtoId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado"));
  if (!Boolean.TRUE.equals(produto.getEstoque())) throw new ResponseStatusException(HttpStatus.CONFLICT,"Produto indisponível");
  Pedido pedido=new Pedido(); pedido.setCliente(cliente); pedido.setProduto(produto); pedido.setQuantidade(entrada.quantidade());
  Pedido salvo=pedidos.save(pedido);
  return ResponseEntity.created(URI.create("/pedidos/"+salvo.getId())).body(saida(salvo));
 }
 @GetMapping public List<Saida> listar() { return pedidos.findAll().stream().map(this::saida).toList(); }
 @GetMapping("/{id}") public Saida buscar(@PathVariable Long id) { return saida(buscarEntidade(id)); }
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id) {
  pedidos.delete(buscarEntidade(id)); return ResponseEntity.noContent().build();
 }
}
