package br.com.loja.listener;

import br.com.loja.model.Pedido;
import br.com.loja.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);
    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PedidoListener(PedidoRepository pedidoRepository, ObjectMapper objectMapper) {
        this.pedidoRepository = pedidoRepository;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${fila.pedido}", containerFactory = "jmsListenerContainerFactory")
    public void receberPedido(String mensagem) {
        try {
            Pedido pedido = this.objectMapper.readValue(mensagem, Pedido.class);
            this.pedidoRepository.save(pedido);
        } catch (JsonProcessingException e) {
            this.logger.error(e.getMessage());
        }
    }
}
