package asaplog.crud.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asaplog.crud.com.model.Cliente;
import asaplog.crud.com.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping(value="/")
    public List<Cliente> getAll() {        
        return clienteService.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity getByCPF(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteService.getByCPF(cpf);

            if(cliente == null){
                throw new IllegalArgumentException("Cliente não encontrado");
            }

            return ResponseEntity
                .status(HttpStatus.OK)                 
                .body(cliente);
            

        } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Cliente cliente, BindingResult result) {        

        try {
            if(result.hasErrors()){
                final StringBuilder messageError = new StringBuilder();
                result.getAllErrors().forEach(
                        err -> {
                            messageError.append(err.getDefaultMessage()+" || ");
                        });
                throw new Exception(messageError.toString());
            }
            
            Cliente _cliente = clienteService.create(cliente);
            return ResponseEntity
                    .status(HttpStatus.OK)                 
                    .body(_cliente);
            
        } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity update(@PathVariable("cpf")  String cpf,
                                @Valid @RequestBody Cliente cliente, BindingResult result){
        try {
            if(result.hasErrors()){
                final StringBuilder messageError = new StringBuilder();
                result.getAllErrors().forEach(
                        err -> {
                            messageError.append(err.getDefaultMessage()+" || ");
                        });
                throw new Exception(messageError.toString());
            }

            Cliente _cliente = clienteService.update(cpf,cliente);        
            return new ResponseEntity<>(_cliente,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
                        // .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }


    @DeleteMapping("/{cpf}")    
    public ResponseEntity<String> delete(@PathVariable String cpf){
        try {
        return clienteService.delete(cpf) == true ? 
                new ResponseEntity<>("Cliente removido", HttpStatus.OK) 
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
    }     
}
