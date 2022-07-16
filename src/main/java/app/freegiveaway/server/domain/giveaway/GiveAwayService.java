package app.freegiveaway.server.domain.giveaway;

import app.freegiveaway.server.domain.port.persistence.GiveAwayPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiveAwayService {

    private final GiveAwayPersistencePort giveAwayPersistencePort;

    public GiveAway create(GiveAway giveAway){
        //todo: do
        return giveAwayPersistencePort.save(giveAway);
    }

    public GiveAway findById(Long id){
        return giveAwayPersistencePort.findById(id);
    }
}
