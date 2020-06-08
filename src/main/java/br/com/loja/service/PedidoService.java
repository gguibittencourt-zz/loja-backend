package br.com.loja.service;

import br.com.loja.model.Pedido;
import org.bson.types.ObjectId;

import java.util.Collection;

public interface PedidoService {
    Collection<Pedido> listar();

    Pedido getById(ObjectId id);

    void salvar(Pedido pedido);
}
