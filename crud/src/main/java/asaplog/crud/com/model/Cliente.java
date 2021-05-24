package asaplog.crud.com.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.caelum.stella.validation.CPFValidator;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "cliente")
public class Cliente {
    
    @Id
    @Getter 
    @Setter
    @NotNull(message = "CPF deve ser informado")
    private String cpf;

    @Getter
    @Setter
    @NotNull(message = "nome deve ser informado")
    private String nome;

    @Getter 
    @Setter
    @NotNull(message = "Cidade deve ser informado")
    private String cidade;

    @Getter 
    @Setter
    @NotNull(message = "Estado deve ser informado")
    private String uf;
    

    public Boolean clienteValido(){           
        try {             
            CPFValidator cpfValidator = new CPFValidator(); 
            cpfValidator.assertValid(this.getCpf()); 
            return true;
        } catch (Exception e) {
            return false;            
        }   
    }

}
