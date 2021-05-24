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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asaplog.crud.com.model.Apolice;
import asaplog.crud.com.model.ApoliceDetails;
import asaplog.crud.com.service.ApoliceService;

@RestController
@RequestMapping("/apolice")
public class ApoliceController {
    
    @Autowired
    private ApoliceService apoliceService;

    @GetMapping(value="/")
    public ResponseEntity<List<Apolice>> getAll() {        
        return  new ResponseEntity<>(apoliceService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{numero}")
    public ResponseEntity getByNumero(@PathVariable String numero) {
        try {
            Apolice apolice = apoliceService.getByNumero(numero);

            if(apolice == null){
                throw new IllegalArgumentException("Apolice não encontrada");
            }

            return ResponseEntity
            .status(HttpStatus.OK)                 
            .body(apolice);
        
        }catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
    }   

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Apolice apolice, BindingResult result) {
        try {
            if(result.hasErrors()){
                final StringBuilder messageError = new StringBuilder();
                result.getAllErrors().forEach(
                        err -> {
                            messageError.append(err.getDefaultMessage()+" || ");
                        });
                throw new Exception(messageError.toString());
            }
                Apolice _apolice = apoliceService.create(apolice);
                return ResponseEntity
                .status(HttpStatus.OK)                 
                .body(_apolice);
                    
            } catch (Exception e) {
                return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
            }
    }
    
    @PutMapping("/{numero}")
    public ResponseEntity update(
                                @PathVariable("numero")  String numero,
                                @Valid @RequestBody Apolice apolice, BindingResult result) {
        try {
            if(result.hasErrors()){
                final StringBuilder messageError = new StringBuilder();
                result.getAllErrors().forEach(
                        err -> {
                            messageError.append(err.getDefaultMessage()+" || ");
                        });
                throw new Exception(messageError.toString());
            }
            Apolice _apolice = apoliceService.update(numero,apolice);
        
            return new ResponseEntity<>(_apolice,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)                 
            .body(e.getMessage());
        }
    }


    @DeleteMapping("/{numero}")    
    public String delete(@PathVariable String numero){            
        return apoliceService.delete(numero);
    }     

    @GetMapping("/consulta-apolice")
    public ResponseEntity<ApoliceDetails> consultaApolice(@RequestParam String numero) {
        try {
            Apolice apolice = apoliceService.getByNumero(numero);
            if(apolice == null)
                throw new Exception("Invalido");
            
            ApoliceDetails apoliceDetails = new ApoliceDetails(apolice);

            return  new ResponseEntity<>(apoliceDetails,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
