package Battaglia;

import Factory.FactoryBattaglia;
import Mosse.Mossa;

import java.util.List;

/**
 * Coordina la progressione della partita composta da piu battaglie consecutive.
 * Mantiene l'indice dell'NPC corrente, crea nuove battaglie e offre operazioni
 * di alto livello come reset, riprova e avanzamento al prossimo NPC.
 */
public class SessioneBattaglia {

    private static final String PLAYER_FILE = "Marcolino.json";
    private static final List<String> NPC_FILES = List.of(
            "NPC1.json",
            "NPC2.json",
            "NPC3.json"
    );

    private final FactoryBattaglia factoryBattaglia;

    private Battaglia battagliaCorrente;
    private int indiceNpcCorrente;

    public SessioneBattaglia() {
        this(new FactoryBattaglia());
    }

    public SessioneBattaglia(FactoryBattaglia factoryBattaglia) {
        if (factoryBattaglia == null) {
            throw new IllegalArgumentException("La factory battaglia non puo essere null");
        }

        this.factoryBattaglia = factoryBattaglia;
    }

    public Battaglia inizia() {
        indiceNpcCorrente = 0;
        return creaBattagliaCorrente();
    }

    public Battaglia resetGioco() {
        return inizia();
    }

    public Battaglia riprovaBattagliaCorrente() {
        return creaBattagliaCorrente();
    }

    public Battaglia prossimaBattaglia() {
        if (!haProssimoNpc()) {
            throw new IllegalStateException("Non ci sono altri NPC disponibili");
        }

        indiceNpcCorrente++;
        return creaBattagliaCorrente();
    }

    public void eseguiTurno(Mossa mossa) {
        if (battagliaCorrente == null) {
            throw new IllegalStateException("Nessuna battaglia inizializzata");
        }

        battagliaCorrente.eseguiTurnoGiocatore(mossa);

        if (!battagliaCorrente.tiroSuFine()) {
            battagliaCorrente.eseguiTurnoNpc();
        }
    }

    public Battaglia getBattagliaCorrente() {
        return battagliaCorrente;
    }

    public boolean haProssimoNpc() {
        return indiceNpcCorrente < NPC_FILES.size() - 1;
    }

    public boolean battagliaTerminata() {
        return battagliaCorrente != null && battagliaCorrente.tiroSuFine();
    }

    public boolean giocatoreHaVintoBattaglia() {
        return battagliaTerminata()
                && battagliaCorrente.getVincitore() == battagliaCorrente.getGiocatore();
    }

    public boolean giocoCompletato() {
        return giocatoreHaVintoBattaglia() && !haProssimoNpc();
    }

    private Battaglia creaBattagliaCorrente() {
        battagliaCorrente = factoryBattaglia.creaBattaglia(PLAYER_FILE, NPC_FILES.get(indiceNpcCorrente));
        return battagliaCorrente;
    }
}
