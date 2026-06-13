package Battaglia;

import Giocatore.Personaggio;
import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;
import StrategiaNPC.StrategiaNpc;
import lombok.Getter;

/**
 * Modello di dominio che rappresenta un singolo combattimento tra giocatore e NPC.
 * Applica i turni, aggiorna lo stato della battaglia e delega alla strategia NPC
 * la scelta della mossa avversaria.
 */
@Getter
public class Battaglia {
    private final PersonaggioGiocante giocatore;
    private final PersonaggioNPC npc;
    private final StrategiaNpc strategiaNpc;

    private String ultimoMessaggio;
    private StatoBattaglia statoBattaglia;

    public Battaglia(PersonaggioGiocante giocatore, PersonaggioNPC npc, StrategiaNpc strategiaNpc){
        if (giocatore == null) {throw new IllegalArgumentException("Il giocatore non può essere null");}

        if (npc == null) {throw new IllegalArgumentException("L'NPC non può essere null");}

        if (strategiaNpc == null) {throw new IllegalArgumentException("La strategia NPC non può essere null");}

        this.giocatore = giocatore;
        this.npc = npc;
        this.strategiaNpc = strategiaNpc;
        this.ultimoMessaggio = "Battaglia Pronta";
        this.statoBattaglia = statoBattaglia.CORSO;
    }

    public void eseguiTurnoGiocatore(Mossa mossa){
        if (statoBattaglia != StatoBattaglia.CORSO) {ultimoMessaggio = "La battaglia è già terminata."; return;}

        if (giocatore.haProssimoTurnoAnnullato()) {giocatore.consumaAnnullamentoTurno();ultimoMessaggio += "\n" + giocatore.getNome() + " non può agire in questo turno."; return;}

        if (mossa == null) {ultimoMessaggio = "Nessuna mossa selezionata."; return;}

        if (!mossa.utilizzabile(giocatore)) {ultimoMessaggio = giocatore.getNome() + " non ha abbastanza stamina per usare " + mossa.getNome() + ".";return;}

        String effettoMossa = mossa.esegui(giocatore, npc);

        ultimoMessaggio = giocatore.getNome() + " usa " + mossa.getNome() + ".";
        aggiungiEffettoMossa(effettoMossa);

        aggiornaStatoBattaglia();
    }

    public void eseguiTurnoNpc() {
        if (statoBattaglia != StatoBattaglia.CORSO){return;}
        if (npc.haProssimoTurnoAnnullato()) {npc.consumaAnnullamentoTurno();ultimoMessaggio += "\n" + npc.getNome() + " non può agire in questo turno."; return;}

        Mossa mossaNpc = scegliMossaNpc();

        if (mossaNpc == null) {
            ultimoMessaggio += "\n" + npc.getNome() + " non può usare nessuna mossa.";
            return;
        }

        String effettoMossa = mossaNpc.esegui(npc, giocatore);

        ultimoMessaggio += "\n" + npc.getNome() + " usa " + mossaNpc.getNome() + ".";
        aggiungiEffettoMossa(effettoMossa);

        aggiornaStatoBattaglia();
    }

    private Mossa scegliMossaNpc() {
        return strategiaNpc.scegliMossa(npc, giocatore);
    }

    private void aggiornaStatoBattaglia() {
        if (!giocatore.tiroSuVivo()) {
            statoBattaglia = StatoBattaglia.SCONFITTA;
            ultimoMessaggio += "\n" + giocatore.getNome() + " è stato sconfitto.";
            return;
        }

        if (!npc.tiroSuVivo()) {
            statoBattaglia = StatoBattaglia.VITTORIA;
            ultimoMessaggio += "\n" + npc.getNome() + " è stato sconfitto.";
        }
    }

    public boolean tiroSuFine(){return statoBattaglia != statoBattaglia.CORSO;}

    public Personaggio getVincitore() {
        return switch (statoBattaglia) {
            case VITTORIA -> giocatore;
            case SCONFITTA -> npc;
            case CORSO -> null;
        };
    }

    private void aggiungiEffettoMossa(String effettoMossa) {
        if (effettoMossa != null && !effettoMossa.isBlank()) {
            ultimoMessaggio += "\n" + effettoMossa;
        }
    }

}
