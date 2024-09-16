package citybike.services;

import citybike.entity.Station;
import citybike.exceptions.StationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    @Mock private StationRepository stationRepository;
    private StationService stationService;

    @BeforeEach
    public void setUp() {
        stationService = new StationService(stationRepository);
    }

    @Test
    void canGetAllStations() {
        // Given
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);

        Station station1 = new Station(1, "Name1", "Address1", "1.111", "2.222");
        Station station2 = new Station(2, "Name2", "Address2", "2.222", "1.111");

        List<Station> stations = Arrays.asList(station1, station2);
        Page<Station> stationPage = new PageImpl<>(stations, pageable, stations.size());

        when(stationRepository.findAll(pageable)).thenReturn(stationPage);

        // When
        List<Station> result = stationService.getAllStations(page, size);

        // Then
        verify(stationRepository).findAll(pageable);
        verify(stationRepository, times(1)).findAll(pageable);
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(station1, station2);
    }

    @Test
    void canGetStationById() {
        // Given
        int id = 1;
        Station station = new Station(1, "Name1", "Address1", "1.111", "2.222");
        when(stationRepository.findById(id)).thenReturn(Optional.of(station));

        // When
        Station result = stationService.getStationById(id);

        // Then
        verify(stationRepository).findById(id);
        verify(stationRepository, times(1)).findById(id);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(station);
    }

    @Test
    void canNotGetStationById() {
        // Given
        int id = 1;
        when(stationRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(StationNotFoundException.class, () -> {
            stationService.getStationById(id);
        });
    }
}