package StrategiaNPC;

/**
 * Factory che risolve il nome della strategia letto dal JSON in una strategia NPC concreta.
 */
public class FactoryStrategiaNpc {

    public StrategiaNpc creaStrategia(String nomeStrategia) {
        if (nomeStrategia == null || nomeStrategia.isBlank()) {
            return new StrategiaBase();
        }

        return switch (nomeStrategia) {
            case "base" -> new StrategiaBase();
            case "boss" -> new StrategiaBoss();
            default -> throw new IllegalArgumentException("Strategia NPC sconosciuta: " + nomeStrategia);
        };
    }
}
