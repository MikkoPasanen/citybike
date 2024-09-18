 package citybike.services;

 import citybike.entity.Journey;
 import citybike.entity.Station;
 import citybike.entity.ToReturnJourney;
 import citybike.exceptions.JourneysNotFoundException;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Disabled;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.Mock;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;

 import java.time.LocalDateTime;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.List;
 import java.util.Optional;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.assertj.core.api.Assertions.assertThat;
 import static org.mockito.Mockito.*;


 @ExtendWith(MockitoExtension.class)
 class JourneyServiceTest {

     @Mock private JourneyRepository journeyRepository;
     @Mock private StationRepository stationRepository;
     private JourneyService journeyService;

     @BeforeEach
     public void setUp() {
         journeyService = new JourneyService(journeyRepository, stationRepository);
     }

     @Test
     void canGetAllJourneys() {
         // Given
         int page = 0;
         int size = 2;
         Pageable pageable = PageRequest.of(page, size);

         LocalDateTime fixedTime = LocalDateTime.of(2024, 9, 17, 0, 47, 58);

         Journey journey1 = new Journey(1, fixedTime, fixedTime.plusHours(1), 1, 2, 10, 600); // 600 seconds = 10 minutes
         Journey journey2 = new Journey(2, fixedTime, fixedTime.plusHours(2), 2, 1, 20, 1200); // 1200 seconds = 20 minutes
         Station station1 = new Station(1, "Name1", "Address1", "1.111", "2.222");
         Station station2 = new Station(2, "Name2", "Address2", "2.222", "1.111");

         ToReturnJourney toReturn1 = new ToReturnJourney(
                 1,
                 fixedTime,
                 fixedTime.plusHours(1),
                 "Name1",
                 "Name2",
                 "1.111",
                 "2.222",
                 "2.222",
                 "1.111",
                 10,
                 10 // duration in minutes
         );
         ToReturnJourney toReturn2 = new ToReturnJourney(
                 2,
                 fixedTime,
                 fixedTime.plusHours(2),
                 "Name2",
                 "Name1",
                 "2.222",
                 "1.111",
                 "1.111",
                 "2.222",
                 20,
                 20 // duration in minutes
         );

         List<Journey> journeys = Arrays.asList(journey1, journey2);
         Page<Journey> journeyPage = new PageImpl<>(journeys, pageable, journeys.size());

         // Mock repository responses
         when(journeyRepository.findAll(pageable)).thenReturn(journeyPage);
         when(stationRepository.findById(1)).thenReturn(Optional.of(station1));
         when(stationRepository.findById(2)).thenReturn(Optional.of(station2));

         // When
         List<ToReturnJourney> result = journeyService.getAllJourneys(page, size);

         // Then
         verify(journeyRepository).findAll(pageable);
         verify(stationRepository, times(2)).findById(1);
         verify(stationRepository, times(2)).findById(2);

         assertThat(result).isNotNull();
         assertThat(result).hasSize(2);
         assertThat(result).containsExactly(toReturn1, toReturn2);
     }

     @Test
     void shouldThrowErrorIfNoJourneys() {

         // Given
         int page = 0;
         int size = 2;
         Pageable pageable = PageRequest.of(page, size);

         List<Journey> journeys = Collections.emptyList();
         Page<Journey> journeyPage = new PageImpl<>(journeys, pageable, 0);
         when(journeyRepository.findAll(pageable)).thenReturn(journeyPage);

         // When & Then
         assertThrows(JourneysNotFoundException.class, () -> {
             journeyService.getAllJourneys(page, size);
         });

     }

//     @Test
//     void canGetJourneyById() {
//         // Given
//         int id = 1;
//         Journey journey = new Journey(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, 2, 10, 10);
//         when(journeyRepository.findById(id)).thenReturn(Optional.of(journey));
//
//         // When
//         Journey result = journeyService.getJourneyById(id);
//
//         // Then
//         verify(journeyRepository).findById(id);
//         verify(journeyRepository, times(1)).findById(id);
//         assertThat(result).isNotNull();
//         assertThat(result).isEqualTo(journey);
//     }
//
//     @Test
//     void canNotGetJourneyById() {
//         // Given
//         int id = 1;
//         when(journeyRepository.findById(id)).thenReturn(Optional.empty());
//
//         // When & Then
//         assertThrows(JourneyNotFoundException.class, () -> {
//             journeyService.getJourneyById(id);
//         });
//     }
 }