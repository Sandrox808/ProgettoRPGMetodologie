package Mosse;


import Giocatore.Personaggio;
import Mosse.Modificatore.ModificatoreMossa;
import lombok.ToString;

/**
 * Mossa difensiva o di supporto che consuma stamina e applica un modificatore.
 */
@ToString(callSuper = true)
public class MossaDifesa extends Mossa {

    public MossaDifesa(
            String nome,
            int costoStamina,
            ModificatoreMossa modificatore
    ) {
        super(nome, costoStamina, TipoMossa.DIFESA, modificatore);
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
