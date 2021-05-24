package asaplog.crud.com.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asaplog.crud.com.model.Apolice;
import asaplog.crud.com.model.Cliente;
import asaplog.crud.com.repository.ApoliceRepository;
import asaplog.crud.com.service.ApoliceService;

@Service
public class ApoliceServiceImpl implements ApoliceService {

    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    public void setClienteService(ClienteServiceImpl clienteServiceImpl) {
      this.clienteServiceImpl = clienteServiceImpl;
    }

    @Override
    public List<Apolice> getAll() {
        try {
            return this.apoliceRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Apolice getByNumero(String numero) {
        try {
                
            Optional<Apolice> apoliceData = this.apoliceRepository.findById(numero);

            if(!apoliceData.isPresent()){
                return null;
            }
                
            return apoliceData.get();

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }

    @Override
    public Apolice create(Apolice apolice) {
                
        try {
            
            if(apolice.getCliente() == null || !apolice.getCliente().clienteValido()){
                throw new Exception("Cliente invalido");
            }
            
            Cliente cliente = this.clienteServiceImpl.getByCPF(apolice.getCliente().getCpf());            
            apolice.setCliente(cliente);

            do{
               apolice.setNumero(this.generatingRandomAlphanumericString_thenCorrect());
            } while (this.getByNumero(apolice.getNumero()) != null );
            

            return this.apoliceRepository.save(apolice);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Apolice update(String numero, Apolice apolice) {       
        
        try {
            Apolice _apolice = this.getByNumero(numero);

            if(_apolice == null ){
                throw new Exception("Apolice inexistente");
            }

            if(apolice.getCliente() == null || !apolice.getCliente().clienteValido()){                
                throw new Exception("Cliente invalido");
            }

            Cliente cliente = this.clienteServiceImpl.getByCPF(apolice.getCliente().getCpf());            
            apolice.setCliente(cliente);

            _apolice.setNumero(numero);
            _apolice.setVigenciaInicio(apolice.getVigenciaInicio());
            _apolice.setVigenciaFim(apolice.getVigenciaFim());
            _apolice.setPlacaVeiculo(apolice.getPlacaVeiculo());
            _apolice.setValor(apolice.getValor());
            
            return this.apoliceRepository.save(_apolice);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public String delete(String numero) {
        this.apoliceRepository.deleteById(numero);
        return "Apolice Removido";
    }


    public String generatingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        return random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();       
    }

   
    
}
