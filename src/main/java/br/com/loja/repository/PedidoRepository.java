package br.com.loja.repository;

import br.com.loja.model.Pedido;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, Integer> {

    Pedido findById(ObjectId id);
}
