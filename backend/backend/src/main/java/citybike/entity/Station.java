package citybike.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Station {
    private int id;
    private String stationName;
    private String stationAddress;
    private String coordinateX;
    private String coordinateY;
}
