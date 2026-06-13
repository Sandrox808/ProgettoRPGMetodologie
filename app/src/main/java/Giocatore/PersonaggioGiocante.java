package Giocatore;

import Mosse.Mossa;
import lombok.Getter;

import java.util.List;

/**
 * Personaggio controllato dal giocatore.
 */
public class PersonaggioGiocante extends Personaggio{
    public PersonaggioGiocante(String nome, String immagine, int hp, int attacco, int stamina, List<Mossa> mosse) {
        super(nome, immagine, hp, attacco, stamina, mosse);
    }
}
