package Giocatore;

import Mosse.Mossa;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Personaggio non giocante controllato da una strategia NPC.
 * Conserva il nome della strategia letto dal JSON, risolto poi dalla factory della battaglia.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PersonaggioNPC extends Personaggio{

    private String strategia;

    public PersonaggioNPC(String nome, String immagine, String strategia, int hp, int attacco, int stamina, List<Mossa> mosse) {
        super(nome, immagine, hp, attacco, stamina, mosse);
        this.strategia = strategia;
    }
}
