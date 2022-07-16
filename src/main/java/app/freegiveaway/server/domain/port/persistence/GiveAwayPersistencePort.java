package app.freegiveaway.server.domain.port.persistence;

import app.freegiveaway.server.domain.giveaway.GiveAway;

public interface GiveAwayPersistencePort {

    GiveAway save(GiveAway giveAway);
    GiveAway findById(Long id);
    GiveAway unpublish(GiveAway giveAway);
}
