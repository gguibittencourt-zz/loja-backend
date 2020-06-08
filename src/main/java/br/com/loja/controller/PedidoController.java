package br.com.loja.controller;

import br.com.loja.model.Pedido;
import br.com.loja.service.PedidoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Pedido> listar() {
        return this.pedidoService.listar();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pedido getById(@PathVariable("id") ObjectId id) {
        return this.pedidoService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void salvar(@Valid @RequestBody Pedido pedido) {
        this.pedidoService.salvar(pedido);
    }
}
