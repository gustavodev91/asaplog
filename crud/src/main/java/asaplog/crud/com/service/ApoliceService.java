package asaplog.crud.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import asaplog.crud.com.model.Apolice;
@Service
public interface ApoliceService {
    
    public List<Apolice> getAll();

    public Apolice getByNumero(String numero);

    public Apolice create(Apolice apolice);

    public Apolice update(String numero, Apolice apolice);

    public String delete(String numero);

}
