package app.freegiveaway.server.domain.port.persistence;

import app.freegiveaway.server.domain.participation.Participation;

import java.time.LocalDateTime;

public interface ParticipationPersistencePort {
    Participation submit(Participation participation);
    Participation cancel(Participation participation);
    Integer giveawayParticipationCount(Long giveawayId, LocalDateTime creationDate);
}
