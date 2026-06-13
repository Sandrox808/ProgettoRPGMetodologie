package Mosse.Modificatore;

import Giocatore.Personaggio;

/**
 * Modificatore informativo che rivela le statistiche principali del bersaglio.
 */
public class ModificatoreScruta implements ModificatoreMossa{
    @Override
    public String getNome() {
        return "scruta";
    }

    @Override
    public String applica(Personaggio utilizzatore, Personaggio bersaglio) {
        return bersaglio.getNome()
                + " ha HP: " + bersaglio.getHp()
                + ", Stamina: " + bersaglio.getStamina()
                + ", Attacco: " + bersaglio.getAttacco()
                + ".";
    }
}
