package Factory;

import Battaglia.Battaglia;
import Dto.PersonaggioDTO;
import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Persistance.PersonaggioLoader;
import StrategiaNPC.FactoryStrategiaNpc;
import StrategiaNPC.StrategiaNpc;

/**
 * Factory responsabile della creazione di una {@link Battaglia} a partire dai file JSON.
 * Carica i DTO dei personaggi, costruisce gli oggetti di dominio e associa all'NPC
 * la strategia dichiarata nei dati.
 */
public class FactoryBattaglia {

    private final PersonaggioLoader loader;
    private final FactoryPersonaggio factoryPersonaggio;
    private final FactoryStrategiaNpc factoryStrategiaNpc;

    public FactoryBattaglia() {
        this(new PersonaggioLoader(), new FactoryPersonaggio(), new FactoryStrategiaNpc());
    }

    public FactoryBattaglia(
            PersonaggioLoader loader,
            FactoryPersonaggio factoryPersonaggio,
            FactoryStrategiaNpc factoryStrategiaNpc
    ) {
        if (loader == null) {
            throw new IllegalArgumentException("Il loader non puo essere null");
        }

        if (factoryPersonaggio == null) {
            throw new IllegalArgumentException("La factory personaggio non puo essere null");
        }

        if (factoryStrategiaNpc == null) {
            throw new IllegalArgumentException("La factory strategia NPC non puo essere null");
        }

        this.loader = loader;
        this.factoryPersonaggio = factoryPersonaggio;
        this.factoryStrategiaNpc = factoryStrategiaNpc;
    }

    public Battaglia creaBattaglia(String playerFile, String npcFile) {
        validaFile(playerFile, "giocatore");
        validaFile(npcFile, "NPC");

        PersonaggioGiocante giocatore = creaGiocatoreDaFile(playerFile);
        PersonaggioNPC npc = creaNpcDaFile(npcFile);
        StrategiaNpc strategiaNpc = factoryStrategiaNpc.creaStrategia(npc.getStrategia());

        return new Battaglia(giocatore, npc, strategiaNpc);
    }

    private PersonaggioGiocante creaGiocatoreDaFile(String fileName) {
        PersonaggioDTO dto = loader.caricaDaResources(fileName);
        return factoryPersonaggio.creaGiocatore(dto);
    }

    private PersonaggioNPC creaNpcDaFile(String fileName) {
        PersonaggioDTO dto = loader.caricaDaResources(fileName);
        return factoryPersonaggio.creaNpc(dto);
    }

    private void validaFile(String fileName, String tipo) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Il file " + tipo + " non puo essere vuoto");
        }
    }
}
