package Mosse;


import Giocatore.Personaggio;
import Mosse.Modificatore.ModificatoreMossa;

/**
 * Mossa non offensiva e non difensiva, usata per effetti speciali come cura,
 * scrutare il nemico o alterare il turno.
 */
public class MossaAltro extends Mossa{
    public MossaAltro(String nome, int costoStamina, ModificatoreMossa modificatore) {
        super(nome, costoStamina, TipoMossa.ALTRO, modificatore);
    }

    @Override
    public String esegui(Personaggio utilizzatore, Personaggio bersaglio) {
        if (!utilizzabile(utilizzatore)) {
            return "";
        }

        utilizzatore.consumaStamina(getCosto());

        return applicaModificatore(utilizzatore, bersaglio);
    }
}
