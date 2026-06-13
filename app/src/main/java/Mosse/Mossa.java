package Mosse;

import Giocatore.Personaggio;
import Mosse.Modificatore.ModificatoreMossa;
import lombok.Getter;

/**
 * Classe base per le mosse eseguibili dai personaggi.
 * Definisce costo, tipo, modificatore e contratto comune per l'esecuzione.
 */
@Getter
public abstract class Mossa {

    private final String nome;
    private final int costo;
    private final TipoMossa tipoMossa;
    private final ModificatoreMossa modificatore;

    protected Mossa(String nome, int costo, TipoMossa tipoMossa, ModificatoreMossa modificatore){
        this.nome = nome;
        this.costo = costo;
        this.tipoMossa = tipoMossa;
        this.modificatore = modificatore;
    }

    public boolean utilizzabile(Personaggio utilizzatore){return utilizzatore.getStamina() >= costo;}

    public String applicaModificatore(Personaggio utilizzatore, Personaggio bersaglio){
        return modificatore.applica(utilizzatore, bersaglio);
    }

    public abstract String esegui(Personaggio utilizzatore, Personaggio bersaglio);
}
