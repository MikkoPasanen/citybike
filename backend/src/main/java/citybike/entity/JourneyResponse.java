package citybike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JourneyResponse {
    private Long amountOfJourneys;
    private List<ToReturnJourney> journeys;
}
