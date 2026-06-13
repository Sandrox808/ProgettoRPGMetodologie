package Factory;


import Mosse.Modificatore.ModificatoreCura;
import Mosse.Modificatore.ModificatoreScruta;
import Mosse.Modificatore.ModificatoreStamina;
import Mosse.Modificatore.ModificatoreTurno;
import Mosse.Modificatore.NessunModificatore;
import Mosse.Mossa;
import Mosse.MossaAltro;
import Mosse.MossaAttacco;
import Mosse.MossaDifesa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory delle mosse disponibili nel gioco.
 * Usa una registry di creatori per associare un id testuale del JSON a una nuova
 * istanza di {@link Mossa}, permettendo di registrare mosse aggiuntive senza
 * modificare la logica di creazione.
 */
public class FactoryMossa {

    private final Map<String, Supplier<Mossa>> mosseRegistrate = new HashMap<>();

    public FactoryMossa() {
        registraMossePredefinite();
    }

    public void registraMossa(String id, Supplier<Mossa> creatoreMossa) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id della mossa non puo essere vuoto");
        }

        if (creatoreMossa == null) {
            throw new IllegalArgumentException("Il creatore della mossa non puo essere null");
        }

        mosseRegistrate.put(id, creatoreMossa);
    }

    public Mossa creaMossa(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }

        Supplier<Mossa> creatoreMossa = mosseRegistrate.get(id);

        if (creatoreMossa == null) {
            throw new IllegalArgumentException("Mossa sconosciuta: " + id);
        }

        return creatoreMossa.get();
    }

    public List<Mossa> creaListaMosse(List<String> ids) {
        List<Mossa> mosse = new ArrayList<>();

        if (ids == null) {
            return mosse;
        }

        for (String id : ids) {
            Mossa mossa = creaMossa(id);

            if (mossa != null) {
                mosse.add(mossa);
            }
        }

        return mosse;
    }

    private void registraMossePredefinite() {
        registraMossa("colpo", () -> new MossaAttacco(
                "Colpo",
                3,
                5,
                new NessunModificatore()
        ));

        registraMossa("colpo_pesante", () -> new MossaAttacco(
                "Colpo pesante",
                8,
                15,
                new NessunModificatore()
        ));

        registraMossa("blocca", () -> new MossaDifesa(
                "blocca",
                0,
                new NessunModificatore()
        ));

        registraMossa("cura", () -> new MossaAltro(
                "Cura",
                5,
                new ModificatoreCura(20)
        ));

        registraMossa("recupera_stamina", () -> new MossaAltro(
                "Recupera stamina",
                0,
                new ModificatoreStamina(34)
        ));

        registraMossa("scruta", () -> new MossaAltro(
                "Scruta",
                1,
                new ModificatoreScruta()
        ));

        registraMossa("dilly_dally", () -> new MossaAltro(
                "DD",
                0,
                new ModificatoreTurno()
        ));
    }
}
