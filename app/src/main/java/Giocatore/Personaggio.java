package Giocatore;

import Mosse.Mossa;
import Mosse.TipoMossa;
import lombok.Getter;

import java.util.List;


/**
 * Classe base per tutti i personaggi che possono partecipare a una battaglia.
 * Incapsula statistiche, mosse disponibili e stato temporaneo come il turno annullato.
 */
@Getter
public abstract class Personaggio {
    private String nome;
    private String immagine;
    private int hp;
    private int attacco;
    private int stamina;
    private int maxStamina;
    private int maxHp;
    private boolean prossimoTurno = false;

    private List<Mossa> mosse;

    protected Personaggio(
            String nome,
            String immagine,
            int hp,
            int attacco,
            int stamina,
            List<Mossa> mosse
    ) {
        this.nome = nome;
        this.immagine = immagine;
        this.hp = hp;
        this.maxHp = hp;
        this.attacco = attacco;
        this.stamina = stamina;
        this.maxStamina = stamina;
        this.mosse = mosse;
    }

    public void danneggia(int danno) {
        if (danno > hp) {
            hp = 0;
        } else {
            hp -= danno;
        }
    }

    public void cura(int numero) {
        if (numero + hp > maxHp) {
            hp = maxHp;
        } else {
            hp += numero;
        }
    }

    public boolean consumaStamina(int numero) {
        if (numero > stamina) {
            return false;
        } else {
            stamina -= numero;
            return true;
        }
    }

    public void immagazzinaStamina(int numero) {
        if (numero + stamina > maxStamina) {
            stamina = maxStamina;
        } else {
            stamina += numero;
        }
    }

    public boolean tiroSuVivo() {
        return hp > 0;
    }

    public boolean haProssimoTurnoAnnullato() {
        return prossimoTurno;
    }

    public void annullaTurno() {
        prossimoTurno = true;
    }

    public void consumaAnnullamentoTurno() {
        prossimoTurno = false;
    }

    public List<Mossa> getMossePerTipo(TipoMossa tipoMossa) {
        return mosse.stream()
                .filter(mossa -> mossa.getTipoMossa() == tipoMossa)
                .toList();
    }
}
