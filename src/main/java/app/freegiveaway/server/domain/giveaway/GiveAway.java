package app.freegiveaway.server.domain.giveaway;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GiveAway {

    private Long id;
    private String title;
    private String description;
    private String bannerURL;
    private GiveAwayType type;
    private Integer minTicketBid;
    private Integer maxParticipant;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private LocalDateTime creationDate;
}
