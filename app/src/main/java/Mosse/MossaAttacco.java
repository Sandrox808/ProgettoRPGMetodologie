package Mosse;


import Giocatore.Personaggio;
import Mosse.Modificatore.ModificatoreMossa;
import lombok.Getter;
import lombok.ToString;

/**
 * Mossa offensiva che infligge danno al bersaglio.
 * Il danno deriva dall'attacco dell'utilizzatore sommato alla potenza della mossa.
 */
@Getter
@ToString(callSuper = true)
public class MossaAttacco extends Mossa {

    private final int potenza;

    public MossaAttacco(
            String nome,
            int costoStamina,
            int potenza,
            ModificatoreMossa modificatore
    ) {
        super(nome, costoStamina, TipoMossa.ATTACCO, modificatore);

        if (potenza < 0) {
            throw new IllegalArgumentException("La potenza non può essere negativa");
        }

        this.potenza = potenza;
    }

    @Override
    public String esegui(Personaggio utilizzatore, Personaggio bersaglio) {
        if (!utilizzabile(utilizzatore)) {
            return "";
        }

        utilizzatore.consumaStamina(getCosto());

        int danno = utilizzatore.getAttacco() + potenza;
        bersaglio.danneggia(danno);

        String effettoModificatore = applicaModificatore(utilizzatore, bersaglio);
        String effetto = bersaglio.getNome() + " subisce " + danno + " danni.";

        if (effettoModificatore == null || effettoModificatore.isBlank()) {
            return effetto;
        }

        return effetto + "\n" + effettoModificatore;
    }
}
