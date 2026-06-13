package Factory;

import Dto.PersonaggioDTO;
import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory che converte un {@link PersonaggioDTO} in personaggi di dominio.
 * Si occupa anche di trasformare gli id delle mosse presenti nel DTO in oggetti {@link Mossa}.
 */
public class FactoryPersonaggio {

    private final FactoryMossa mossaFactory = new FactoryMossa();

    public PersonaggioGiocante creaGiocatore(PersonaggioDTO dto) {
        return new PersonaggioGiocante(
                dto.getNome(),
                dto.getImmagine(),
                dto.getHp(),
                dto.getAttacco(),
                dto.getStamina(),
                creaMosseDaDto(dto)
        );
    }

    public PersonaggioNPC creaNpc(PersonaggioDTO dto) {
        return new PersonaggioNPC(
                dto.getNome(),
                dto.getImmagine(),
                dto.getStrategia(),
                dto.getHp(),
                dto.getAttacco(),
                dto.getStamina(),
                creaMosseDaDto(dto)
        );
    }

    private List<Mossa> creaMosseDaDto(PersonaggioDTO dto) {
        List<Mossa> mosse = new ArrayList<>();

        mosse.addAll(mossaFactory.creaListaMosse(dto.getMosseAttacco()));
        mosse.addAll(mossaFactory.creaListaMosse(dto.getMosseDifesa()));
        mosse.addAll(mossaFactory.creaListaMosse(dto.getMosseAltro()));

        return mosse;
    }
}
