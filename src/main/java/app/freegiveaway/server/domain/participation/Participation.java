package app.freegiveaway.server.domain.participation;

import app.freegiveaway.server.domain.giveaway.GiveAway;
import app.freegiveaway.server.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Participation {
    private Long id;
    private User user;
    private GiveAway giveAway;
    private LocalDateTime date;
    private Integer ticket;
}
