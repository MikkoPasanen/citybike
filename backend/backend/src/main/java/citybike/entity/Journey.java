package citybike.code.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "departure_date_time")
    private LocalDateTime departureTime;

    @Column(name = "return_date_time")
    private LocalDateTime returnTime;

    @Column(name = "departure_station_id")
    private int departureStation;

    @Column(name = "return_station_id")
    private int returnStation;

    @Column(name = "distance")
    private int distance;

    @Column(name = "duration")
    private int duration;

    private Journey() {}
}
