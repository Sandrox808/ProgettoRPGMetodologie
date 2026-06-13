package Persistance;

import Dto.PersonaggioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.InputStream;

/**
 * Loader dei personaggi salvati nelle resources dell'applicazione.
 * Usa Jackson per trasformare un file JSON in un {@link PersonaggioDTO}.
 */
public class PersonaggioLoader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PersonaggioDTO caricaDaResources(String fileName) {
        try (InputStream inputStream = getClass()
                .getResourceAsStream("/org/example/" + fileName)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("File JSON non trovato: " + fileName);
            }

            return objectMapper.readValue(inputStream, PersonaggioDTO.class);

        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura del file JSON: " + fileName, e);
        }
    }
}
