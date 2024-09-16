package citybike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ToReturnJourney {
    private int id;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private String departureStation;
    private String returnStation;
    private Integer distance;
    private Integer duration;
}
