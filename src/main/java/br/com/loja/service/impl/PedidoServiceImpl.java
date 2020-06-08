package br.com.loja.service.impl;

import br.com.loja.model.Pedido;
import br.com.loja.repository.PedidoRepository;
import br.com.loja.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoServiceImpl.class);
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final PedidoRepository pedidoRepository;

    private static final String FILA = "pedido";

    @Autowired
    public PedidoServiceImpl(JmsTemplate jmsTemplate, ObjectMapper objectMapper, PedidoRepository pedidoRepository) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Collection<Pedido> listar() {
        return this.pedidoRepository.findAll();
    }

    @Override
    public Pedido getById(ObjectId id) {
        return this.pedidoRepository.findById(id);
    }

    @Override
    public void salvar(Pedido pedido) {
        try {
            this.jmsTemplate.convertAndSend(PedidoServiceImpl.FILA, this.objectMapper.writeValueAsString(pedido));
        } catch (JsonProcessingException e) {
            this.logger.error(e.getMessage());
        }
    }
}
