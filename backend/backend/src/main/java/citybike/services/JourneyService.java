package citybike.services;

import citybike.entity.Journey;
import citybike.entity.Station;
import citybike.exceptions.JourneyNotFoundException;
import citybike.exceptions.StationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyService {
    private final JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public List<Journey> getAllJourneys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Journey> journeyPage = journeyRepository.findAll(pageable);
        return journeyPage.getContent();
    }

    public Journey getJourneyById(int id) {
        return journeyRepository.findById(id)
                .orElseThrow(() -> new JourneyNotFoundException("Couldn't find journey with that id"));
    }
}
