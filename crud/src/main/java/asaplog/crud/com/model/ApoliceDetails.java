package asaplog.crud.com.model;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.Duration;
import org.joda.time.Interval;

import lombok.Data;

@Data
public class ApoliceDetails {
    private Apolice apolice;
    private Boolean venceu;
    private Long numeroDias;
    private Long numeroHoras;


    public ApoliceDetails(Apolice apolice){
        this.setApolice(apolice);

        Interval interval;
        Duration duracao;

        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        Long agora = timestamp2.getTime();
        Long dtFim = apolice.getVigenciaFim().getTime();

        boolean venceu = false;

        if(agora > dtFim){
            venceu = true;
            interval = new Interval(dtFim,agora);
        }else{
            interval = new Interval(agora,dtFim);
        }

        duracao = interval.toDuration();

        this.setVenceu(venceu);
        this.setNumeroDias(duracao.getStandardDays());
        this.setNumeroHoras(duracao.getStandardHours());
    }    
}
