package Mosse.Modificatore;

import Giocatore.Personaggio;

/**
 * Implementazione nulla del modificatore, usata per mosse senza effetti aggiuntivi.
 */
public class NessunModificatore implements ModificatoreMossa{
    @Override
    public String getNome() {return "___";}

    @Override
    public String applica(Personaggio utilizzatore, Personaggio bersaglio) {
        return "";
    }
}
