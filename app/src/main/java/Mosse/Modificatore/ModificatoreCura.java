package Mosse.Modificatore;

import Giocatore.Personaggio;

/**
 * Modificatore che cura l'utilizzatore della mossa.
 */
public class ModificatoreCura implements ModificatoreMossa{

    private final int numero;
    public ModificatoreCura(int numero){this.numero = numero;}

    @Override
    public String getNome() {return "cura";}

    @Override
    public String applica(Personaggio utilizzatore, Personaggio bersaglio) {
        utilizzatore.cura(numero);
        return utilizzatore.getNome() + " recupera " + numero + " HP.";
    }
}
