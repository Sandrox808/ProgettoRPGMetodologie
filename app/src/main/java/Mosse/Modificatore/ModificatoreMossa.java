package Mosse.Modificatore;


import Giocatore.Personaggio;



/**
 * Contratto per un effetto aggiuntivo applicabile da una mossa.
 * Il valore restituito descrive l'effetto prodotto e puo essere mostrato nel log battaglia.
 */
public interface ModificatoreMossa {
    String getNome();
    String applica(Personaggio utilizzatore, Personaggio bersaglio);
}
