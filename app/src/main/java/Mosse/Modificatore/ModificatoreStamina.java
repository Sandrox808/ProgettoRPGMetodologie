package Mosse.Modificatore;

import Giocatore.Personaggio;

/**
 * Modificatore che restituisce stamina all'utilizzatore della mossa.
 */
public class ModificatoreStamina implements ModificatoreMossa {

    private final int numero;

    public ModificatoreStamina(int numero) {
        this.numero = numero;
    }

    @Override
    public String getNome() {
        return "recupera_stamina";
    }

    @Override
    public String applica(Personaggio utilizzatore, Personaggio bersaglio) {
        utilizzatore.immagazzinaStamina(numero);
        return utilizzatore.getNome() + " recupera " + numero + " stamina.";
    }
}
