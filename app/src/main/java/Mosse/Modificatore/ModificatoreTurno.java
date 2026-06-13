package Mosse.Modificatore;

import Giocatore.Personaggio;

/**
 * Modificatore che annulla il prossimo turno del bersaglio.
 */
public class ModificatoreTurno implements ModificatoreMossa{
    @Override
    public String getNome() {
        return "SaltaTurno";
    }

    @Override
    public String applica(Personaggio utilizzatore, Personaggio bersaglio) {
        bersaglio.annullaTurno();
        return bersaglio.getNome() + " saltera il prossimo turno.";
    }
}
