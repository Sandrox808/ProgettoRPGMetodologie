package Controller;

import Battaglia.Battaglia;
import Battaglia.SessioneBattaglia;
import Giocatore.PersonaggioGiocante;
import Giocatore.PersonaggioNPC;
import Mosse.Mossa;
import Mosse.TipoMossa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * Controller JavaFX della schermata di battaglia.
 * Gestisce gli eventi della UI, la selezione delle mosse e l'aggiornamento
 * dei controlli grafici delegando la conduzione della partita a {@link SessioneBattaglia}.
 */
public class ControllerBattaglia {

    @FXML
    private Label personaggioGiocanteLabel;

    @FXML
    private Label personaggioGiocanteHpLabel;

    @FXML
    private Label personaggioGiocanteStaminaLabel;

    @FXML
    private Label personaggioGiocanteAttaccoLabel;

    @FXML
    private Label personaggioNPCLabel;

    @FXML
    private Label personaggioNPCHpLabel;

    @FXML
    private Label personaggioNPCStaminaLabel;

    @FXML
    private Label personaggioNpcAttaccoLabel;

    @FXML
    private ImageView personaggioGiocanteImmagine;

    @FXML
    private ImageView personaggioNPCImmagine;

    @FXML
    private ToggleButton bottoneAttacco1;

    @FXML
    private ToggleButton bottoneAttacco2;

    @FXML
    private ToggleButton bottoneDifesa1;

    @FXML
    private ToggleButton bottoneDifesa2;

    @FXML
    private ToggleButton bottoneAltro1;

    @FXML
    private ToggleButton bottoneAltro2;

    @FXML
    private TextArea LogBattaglia;

    @FXML
    private Button bottoneIndietro;

    @FXML
    private Button bottoneAvanti;

    private ToggleGroup moveToggleGroup;

    private Mossa mossaSelezionata;

    private SessioneBattaglia sessioneBattaglia;

    private Battaglia battaglia;

    @FXML
    public void initialize() {
        sessioneBattaglia = new SessioneBattaglia();

        configuraToggleGroup();
        configuraBottoniDiNavigazione();

        inizializzaBattaglia();
    }

    private void inizializzaBattaglia() {
        preparaBattaglia(sessioneBattaglia.inizia());
    }

    private void preparaBattaglia(Battaglia nuovaBattaglia) {
        battaglia = nuovaBattaglia;
        preparaControlliPerBattagliaInCorso();
        configuraBottoniMosse();
        aggiornaUI();
    }

    private void configuraToggleGroup() {
        moveToggleGroup = new ToggleGroup();

        bottoneAttacco1.setToggleGroup(moveToggleGroup);
        bottoneAttacco2.setToggleGroup(moveToggleGroup);
        bottoneDifesa1.setToggleGroup(moveToggleGroup);
        bottoneDifesa2.setToggleGroup(moveToggleGroup);
        bottoneAltro1.setToggleGroup(moveToggleGroup);
        bottoneAltro2.setToggleGroup(moveToggleGroup);
    }

    private void configuraBottoniDiNavigazione() {
        bottoneAvanti.setOnAction(event -> eseguiMossaSelezionata());
        bottoneIndietro.setOnAction(event -> annullaSelezione());
    }

    private void configuraBottoniMosse() {
        PersonaggioGiocante giocatore = battaglia.getGiocatore();

        List<Mossa> mosseAttacco = giocatore.getMossePerTipo(TipoMossa.ATTACCO);
        List<Mossa> mosseDifesa = giocatore.getMossePerTipo(TipoMossa.DIFESA);
        List<Mossa> mosseAltro = giocatore.getMossePerTipo(TipoMossa.ALTRO);

        configuraBottoneMossa(bottoneAttacco1, getMossaOrNull(mosseAttacco, 0));
        configuraBottoneMossa(bottoneAttacco2, getMossaOrNull(mosseAttacco, 1));

        configuraBottoneMossa(bottoneDifesa1, getMossaOrNull(mosseDifesa, 0));
        configuraBottoneMossa(bottoneDifesa2, getMossaOrNull(mosseDifesa, 1));

        configuraBottoneMossa(bottoneAltro1, getMossaOrNull(mosseAltro, 0));
        configuraBottoneMossa(bottoneAltro2, getMossaOrNull(mosseAltro, 1));
    }

    private void configuraBottoneMossa(ToggleButton bottone, Mossa mossa) {
        bottone.setSelected(false);

        if (mossa == null) {
            bottone.setText("-");
            bottone.setDisable(true);
            bottone.setOnAction(null);
            return;
        }

        bottone.setText(mossa.getNome());
        bottone.setDisable(false);

        bottone.setOnAction(event -> {
            if (bottone.isSelected()) {
                mossaSelezionata = mossa;
                LogBattaglia.setText("Mossa selezionata: " + mossa.getNome());
            } else {
                mossaSelezionata = null;
                LogBattaglia.setText("Nessuna mossa selezionata.");
            }
        });
    }

    private Mossa getMossaOrNull(List<Mossa> mosse, int index) {
        if (mosse == null || index < 0 || index >= mosse.size()) {
            return null;
        }

        return mosse.get(index);
    }

    private void annullaSelezione() {
        moveToggleGroup.selectToggle(null);
        mossaSelezionata = null;
        LogBattaglia.setText("Selezione annullata.");
    }

    private void eseguiMossaSelezionata() {
        if (battaglia == null) {
            LogBattaglia.setText("Nessuna battaglia inizializzata.");
            return;
        }

        if (mossaSelezionata == null) {
            LogBattaglia.setText("Seleziona una mossa prima di continuare.");
            return;
        }

        sessioneBattaglia.eseguiTurno(mossaSelezionata);
        battaglia = sessioneBattaglia.getBattagliaCorrente();

        moveToggleGroup.selectToggle(null);
        mossaSelezionata = null;

        aggiornaUI();

        if (sessioneBattaglia.battagliaTerminata()) {
            gestisciFineBattaglia();
        }
    }

    private void aggiornaUI() {
        PersonaggioGiocante giocatore = battaglia.getGiocatore();
        PersonaggioNPC npc = battaglia.getNpc();

        personaggioGiocanteLabel.setText(giocatore.getNome());
        personaggioGiocanteHpLabel.setText("HP: " + giocatore.getHp());
        personaggioGiocanteStaminaLabel.setText("Stamina: " + giocatore.getStamina());
        personaggioGiocanteAttaccoLabel.setText("Attacco: " + giocatore.getAttacco());
        aggiornaImmagine(personaggioGiocanteImmagine, giocatore.getImmagine());

        personaggioNPCLabel.setText(npc.getNome());
        personaggioNPCHpLabel.setText("HP: " + npc.getHp());
        personaggioNPCStaminaLabel.setText("Stamina: " + npc.getStamina());
        personaggioNpcAttaccoLabel.setText("Attacco: " + npc.getAttacco());
        aggiornaImmagine(personaggioNPCImmagine, npc.getImmagine());

        LogBattaglia.setText(battaglia.getUltimoMessaggio());

        if (battaglia.tiroSuFine() && battaglia.getVincitore() != null) {
            LogBattaglia.appendText("\nVincitore: " + battaglia.getVincitore().getNome());
        }
    }

    private void aggiornaImmagine(ImageView imageView, String percorsoImmagine) {
        if (percorsoImmagine == null || percorsoImmagine.isBlank()) {
            imageView.setImage(null);
            return;
        }

        var resource = getClass().getResource(percorsoImmagine);
        if (resource == null) {
            imageView.setImage(null);
            LogBattaglia.appendText("\nImmagine non trovata: " + percorsoImmagine);
            return;
        }

        imageView.setImage(new Image(resource.toExternalForm()));
    }


    private void gestisciFineBattaglia() {
        disabilitaBottoniMosse();

        bottoneIndietro.setText("Reset gioco");
        bottoneIndietro.setDisable(false);
        bottoneIndietro.setOnAction(event -> resetGioco());

        if (sessioneBattaglia.giocatoreHaVintoBattaglia()) {
            gestisciVittoriaGiocatore();
        } else {
            gestisciSconfittaGiocatore();
        }
    }

    private void gestisciVittoriaGiocatore() {
        if (sessioneBattaglia.haProssimoNpc()) {
            bottoneAvanti.setDisable(false);
            bottoneAvanti.setText("Prossimo NPC");
            bottoneAvanti.setOnAction(event -> cambiaNPC());
        } else {
            bottoneAvanti.setDisable(true);
            LogBattaglia.appendText("\nHai battuto il gioco. Congratulazioni!");
        }
    }

    private void gestisciSconfittaGiocatore () {
        bottoneAvanti.setDisable(false);
        bottoneAvanti.setText("Riprova");
        bottoneAvanti.setOnAction(event -> riprovaBattaglia());
    }

    private void cambiaNPC() {
        preparaBattaglia(sessioneBattaglia.prossimaBattaglia());
    }

    private void riprovaBattaglia() {
        preparaBattaglia(sessioneBattaglia.riprovaBattagliaCorrente());
    }

    private void resetGioco() {
        preparaBattaglia(sessioneBattaglia.resetGioco());
    }

    private void preparaControlliPerBattagliaInCorso() {
        moveToggleGroup.selectToggle(null);
        mossaSelezionata = null;

        bottoneAvanti.setText("Avanti");
        bottoneAvanti.setDisable(false);
        bottoneAvanti.setOnAction(event -> eseguiMossaSelezionata());

        bottoneIndietro.setText("Indietro");
        bottoneIndietro.setDisable(false);
        bottoneIndietro.setOnAction(event -> annullaSelezione());
    }

    private void disabilitaBottoniMosse() {
        bottoneAttacco1.setDisable(true);
        bottoneAttacco2.setDisable(true);
        bottoneDifesa1.setDisable(true);
        bottoneDifesa2.setDisable(true);
        bottoneAltro1.setDisable(true);
        bottoneAltro2.setDisable(true);
    }
}
