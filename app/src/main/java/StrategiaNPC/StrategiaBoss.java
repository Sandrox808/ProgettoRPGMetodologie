package StrategiaNPC;

import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;
import Mosse.MossaAttacco;

import java.util.Comparator;

/**
 * Strategia NPC per boss: privilegia l'attacco utilizzabile con potenza piu alta.
 * Se non ci sono attacchi utilizzabili, ripiega sulla prima mossa disponibile.
 */
public class StrategiaBoss implements StrategiaNpc {

    @Override
    public Mossa scegliMossa(PersonaggioNPC npc, PersonaggioGiocante giocatore) {
        Mossa attaccoMigliore = scegliAttaccoMigliore(npc);

        if (attaccoMigliore != null) {
            return attaccoMigliore;
        }

        return npc.getMosse()
                .stream()
                .filter(mossa -> mossa.utilizzabile(npc))
                .findFirst()
                .orElse(null);
    }

    private Mossa scegliAttaccoMigliore(PersonaggioNPC npc) {
        return npc.getMosse()
                .stream()
                .filter(MossaAttacco.class::isInstance)
                .map(MossaAttacco.class::cast)
                .filter(mossa -> mossa.utilizzabile(npc))
                .max(Comparator.comparingInt(MossaAttacco::getPotenza))
                .orElse(null);
    }
}
