package StrategiaNPC;

import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;

/**
 * Strategia NPC semplice: sceglie la prima mossa utilizzabile nell'ordine in cui e stata caricata.
 */
public class StrategiaBase implements StrategiaNpc {

    @Override
    public Mossa scegliMossa(PersonaggioNPC npc, PersonaggioGiocante giocatore) {
        return npc.getMosse()
                .stream()
                .filter(mossa -> mossa.utilizzabile(npc))
                .findFirst()
                .orElse(null);
    }
}
