package asaplog.crud.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import asaplog.crud.com.model.Cliente;
@Service
public interface ClienteService {
    
    public List<Cliente> getAll();

    public Cliente getByCPF(String cpf);

    public Cliente create(Cliente cliente);

    public Cliente update(String cpf, Cliente cliente);

    public Boolean delete(String cpf);


}
