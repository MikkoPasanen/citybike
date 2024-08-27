package citybike.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Journey {
    private int id;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private int departureStation;
    private int returnStation;
    private int distance;
    private int duration;
}
