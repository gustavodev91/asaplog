package asaplog.crud.com.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "apolice")
public class Apolice {
    
    @Id    
    private String numero;

    @NotNull(message = "Data de inicio deve ser informado")
    private Date vigenciaInicio;
    
    @NotNull(message = "Data do fim deve ser informado")
    private Date vigenciaFim;

    @NotNull(message = "Placa do veículo deve ser informado")
    private String placaVeiculo;
    
    @NotNull(message = "Valor deve ser informado")
    private BigDecimal valor;
    
    @DBRef
    @NotNull(message = "Cliente deve ser informado")
    private Cliente cliente;
    
}
