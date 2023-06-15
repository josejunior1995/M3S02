package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Cliente;
import br.com.canasvieiras.m3s02projetorevisao.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

//    public Cliente salvar(Cliente cliente) throws Exception {
//
//        Cliente clienteBanco = new Cliente();
//        if (cliente.getId() != null) {
//            clienteBanco = buscarPorId(cliente.getId());
//        }
//        cliente.setAlteracao(new Date());
//
//        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
//            throw new Exception("Nome é obrigatório!");
//        }
//
//        clienteBanco.setAlteracao(cliente.getAlteracao());
//        clienteBanco.setNome(cliente.getNome());
//        clienteBanco.setDescricao(cliente.getDescricao());
//
//        clienteBanco = clienteRepository.save(clienteBanco);
//        return clienteBanco;
//    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) throws Exception {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) {
            throw new Exception("Cliente não encontrado!");
        }
        return clienteOpt.get();
    }

    public boolean apagar(Long id) {
        try {
            Cliente cliente = buscarPorId(id);
            clienteRepository.delete(cliente);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
