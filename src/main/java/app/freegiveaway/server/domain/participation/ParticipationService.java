package app.freegiveaway.server.domain.participation;

import app.freegiveaway.server.domain.giveaway.GiveAway;
import app.freegiveaway.server.domain.giveaway.GiveAwayService;
import app.freegiveaway.server.domain.port.persistence.ParticipationPersistencePort;
import app.freegiveaway.server.domain.port.persistence.UserPersistencePort;
import app.freegiveaway.server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationPersistencePort participationPersistencePort;

    private final GiveAwayService giveAwayService;

    private final UserPersistencePort userPersistencePort;

    @Transactional
    public Participation submit(User user,Participation participation){
        //check giveaway
        GiveAway giveAway=giveAwayService.findById(participation.getGiveAway().getId());

        //check dates
        LocalDateTime now=LocalDateTime.now();
        if (giveAway.getOpenDate().isAfter(now)){
            throw new RuntimeException("Giveaway is not available yet.");
        }

        if (giveAway.getCloseDate().isBefore(now)){
            throw new RuntimeException("Giveaway is over.");
        }

        //check participants
        Integer participantCount=participationPersistencePort.giveawayParticipationCount(giveAway.getId(),giveAway.getCreationDate());
        if (giveAway.getMaxParticipant()!=-1 && participantCount >= giveAway.getMaxParticipant()) {
            throw new RuntimeException("Maximum number of participants reached.");
        }

        //check ticket count
        if (participation.getTicket() < giveAway.getMinTicketBid()){
            throw new RuntimeException("Minimum amount of ticket for this giveaway is "+giveAway.getMinTicketBid());
        }

        //check if user has tickets
        if (user.getTicket()<participation.getTicket()){
            throw new RuntimeException("Insufficient tickets. You only have "+user.getTicket());
        }

        user.setTicket(user.getTicket()-participation.getTicket());
        userPersistencePort.save(user);

        return participationPersistencePort.submit(participation);
    }
}
