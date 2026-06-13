# Marcolino contro il Mondo

## Descrizione

Marcolino contro il Mondo e una base di gioco RPG con loop di combattimento funzionante.
Il progetto implementa una sequenza di battaglie tra un personaggio giocante e una serie di NPC caricati da file JSON.

Il loop principale permette di:

- caricare personaggio giocante e NPC da resources;
- visualizzare statistiche, immagini e mosse disponibili;
- selezionare una mossa;
- eseguire il turno del giocatore;
- eseguire il turno dell'NPC tramite strategy pattern;
- avanzare al prossimo NPC dopo una vittoria;
- riprovare o resettare la partita.

Il progetto e pensato come esercizio di modellazione OOP, applicazione di pattern e separazione delle responsabilita.

## Linguaggio e librerie utilizzate

Il progetto e sviluppato in Java con Gradle.

Tecnologie principali:

- **Java 25 toolchain**: configurata nel build Gradle.
- **Gradle Kotlin DSL**: file `settings.gradle.kts` e `app/build.gradle.kts`.
- **JavaFX 21**: usato per interfaccia grafica, FXML, controlli e immagini.
- **Jackson Databind**: usato per leggere i personaggi dai file JSON.
- **Lombok**: usato per ridurre boilerplate su DTO e modelli.
- **JUnit Jupiter**: configurato per test futuri, anche se al momento non sono presenti test.

Architettura tecnica:

- `ControllerBattaglia` gestisce la UI JavaFX.
- `SessioneBattaglia` gestisce la progressione della partita.
- `Battaglia` contiene la logica del combattimento.
- `FactoryBattaglia`, `FactoryPersonaggio` e `FactoryMossa` costruiscono oggetti di dominio da dati JSON.
- `StrategiaNPC` implementa lo strategy pattern per differenziare il comportamento degli NPC.
- I modificatori delle mosse modellano effetti come cura, recupero stamina, scrutare e salto turno.

## Come buildare

Da terminale, nella root del progetto:

```powershell
cd C:\Users\Sndrx\Inf\Java\progetto3
```

Su Windows:

```powershell
.\gradlew.bat build
```

Su Linux/macOS:

```bash
./gradlew build
```

Per avviare l'applicazione:

```powershell
.\gradlew.bat run
```

oppure:

```bash
./gradlew run
```

Output importanti:

- build compilata: `app/build/`
- jar generato: `app/build/libs/`
- distribuzioni Gradle: `app/build/distributions/`

Queste cartelle sono generate automaticamente e non devono essere pushate su Git.

## Tree e spiegazione veloce delle cartelle

Struttura principale:

```text
.
|-- app/
|   |-- build.gradle.kts
|   |-- src/
|       |-- main/
|           |-- java/
|           |   |-- Battaglia/
|           |   |-- Controller/
|           |   |-- Dto/
|           |   |-- Factory/
|           |   |-- Giocatore/
|           |   |-- Mosse/
|           |   |-- Persistance/
|           |   |-- StrategiaNPC/
|           |   |-- org/example/
|           |-- resources/
|               |-- org/example/
|-- gradle/
|-- gradlew
|-- gradlew.bat
|-- settings.gradle.kts
|-- gradle.properties
|-- README.md
```

Cartelle principali:

- `app/src/main/java/Battaglia/`: contiene il dominio della battaglia, lo stato e la sessione di gioco.
- `app/src/main/java/Controller/`: contiene il controller JavaFX collegato al file FXML.
- `app/src/main/java/Dto/`: contiene i DTO usati per leggere i JSON.
- `app/src/main/java/Factory/`: contiene le factory per creare battaglie, personaggi e mosse.
- `app/src/main/java/Giocatore/`: contiene le classi dei personaggi.
- `app/src/main/java/Mosse/`: contiene le mosse e i modificatori.
- `app/src/main/java/Persistance/`: contiene il loader dei file JSON dalle resources.
- `app/src/main/java/StrategiaNPC/`: contiene le strategie degli NPC e la factory delle strategie.
- `app/src/main/java/org/example/`: contiene l'entry point JavaFX.
- `app/src/main/resources/org/example/`: contiene FXML, JSON dei personaggi e immagini.
- `gradle/`: contiene il Gradle wrapper.

## Disclaimer utilizzo di Intelligenza Artificiale

Durante lo sviluppo e stata utilizzata Intelligenza Artificiale come supporto per:

- bugfix;
- revisione del codice;
- approfondimento di concetti specifici;
- confronto su principi SOLID, clean code, OOP e pattern;
- supporto nella documentazione del progetto.

L'IA e stata usata come strumento di assistenza e ragionamento tecnico, non come sostituto della comprensione del codice o delle scelte progettuali.
