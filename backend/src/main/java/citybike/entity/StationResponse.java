package citybike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StationResponse {
    private Long amountOfStations;
    private List<Station> stations;
}
