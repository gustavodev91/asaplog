package asaplog.crud.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asaplog.crud.com.model.Cliente;
import asaplog.crud.com.repository.ClienteRepository;
import asaplog.crud.com.service.ClienteService;
import br.com.caelum.stella.validation.CPFValidator;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        try {
            return this.clienteRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cliente getByCPF(String cpf)  {
        
        try {
            CPFValidator cpfValidator = new CPFValidator(); 
            cpfValidator.assertValid(cpf);

            Optional<Cliente> clienteData = this.clienteRepository.findById(cpf);
        
            if(!clienteData.isPresent()){
                return null;
            }
            return clienteData.get();


        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cliente create(Cliente cliente) {
        try {
            CPFValidator cpfValidator = new CPFValidator(); 
            cpfValidator.assertValid(cliente.getCpf());

            if(this.getByCPF(cliente.getCpf()) != null ){
                throw new IllegalArgumentException("Cliente já existe");
            }

            return this.clienteRepository.insert(cliente);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cliente update(String cpf, Cliente cliente) {
        try {           
            CPFValidator cpfValidator = new CPFValidator(); 
            cpfValidator.assertValid(cpf); 

            Cliente _cliente = this.getByCPF(cpf);
            
            if(_cliente == null ){
                throw new IllegalArgumentException("Cliente não existe");
            }

            _cliente.setCidade(cliente.getCidade());
            _cliente.setNome(cliente.getNome());
            _cliente.setUf(cliente.getUf());
            _cliente.setCpf(cpf);
            return this.clienteRepository.save(_cliente);
            
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(String cpf) {
        try {           
            CPFValidator cpfValidator = new CPFValidator(); 
            cpfValidator.assertValid(cpf); 

            Cliente _cliente = this.getByCPF(cpf);

            if(_cliente == null ){
                throw new IllegalArgumentException("Cliente não existe");
            }

            this.clienteRepository.deleteById(cpf);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


   
   
    
}
