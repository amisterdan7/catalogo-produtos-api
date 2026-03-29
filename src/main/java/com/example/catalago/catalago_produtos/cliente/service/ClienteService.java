package com.example.catalago.catalago_produtos.cliente.service;

import com.example.catalago.catalago_produtos.cliente.dto.ClienteRequestDTO;
import com.example.catalago.catalago_produtos.cliente.exception.ClienteNotFoundException;
import com.example.catalago.catalago_produtos.cliente.model.Cliente;
import com.example.catalago.catalago_produtos.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(ClienteRequestDTO dto) {
        return repository.save(
                new Cliente(dto.nome(), dto.sobrenome(), dto.email(), dto.celular(), dto.cep())
        );
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public Cliente atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = buscarPorId(id);
        cliente.atualizar(dto.nome(), dto.sobrenome(), dto.email(), dto.celular(), dto.cep());
        return repository.save(cliente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
