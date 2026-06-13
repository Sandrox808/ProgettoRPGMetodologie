package Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * DTO usato da Jackson per leggere i personaggi dai file JSON.
 * Contiene solo dati grezzi e non logica di dominio.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonaggioDTO {

    private String nome;
    private String immagine;
    private String strategia;

    private int hp;
    private int attacco;
    private int stamina;

    private List<String> mosseAttacco;
    private List<String> mosseDifesa;
    private List<String> mosseAltro;
}
