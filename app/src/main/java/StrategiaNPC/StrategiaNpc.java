package StrategiaNPC;

import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;

/**
 * Strategia di scelta della mossa per un NPC.
 * Permette di variare il comportamento dell'avversario senza modificare la battaglia.
 */
public interface StrategiaNpc {
    Mossa scegliMossa(PersonaggioNPC npc, PersonaggioGiocante giocatore);
}
