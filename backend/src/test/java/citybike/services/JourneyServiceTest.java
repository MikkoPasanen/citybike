// package citybike.services;

// import citybike.entity.Journey;
// import citybike.exceptions.JourneyNotFoundException;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

// import java.time.LocalDateTime;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;


// @ExtendWith(MockitoExtension.class)
// class JourneyServiceTest {

//     @Mock private JourneyRepository journeyRepository;
//     private JourneyService journeyService;

//     @BeforeEach
//     public void setUp() {
//         journeyService = new JourneyService(journeyRepository);
//     }

// //    @Disabled
// //    @Test
// //    void canGetAllJourneys() {
// //        // Given
// //        int page = 0;
// //        int size = 2;
// //        Pageable pageable = PageRequest.of(page, size);
// //
// //        Journey journey1 = new Journey(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, 2, 10, 10);
// //        Journey journey2 = new Journey(2, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, 1, 20, 20);
// //
// //        List<Journey> journeys = Arrays.asList(journey1, journey2);
// //        Page<Journey> journeyPage = new PageImpl<>(journeys, pageable, journeys.size());
// //
// //        when(journeyRepository.findAll(pageable)).thenReturn(journeyPage);
// //
// //        // When
// //        List<Journey> result = journeyService.getAllJourneys(page, size);
// //
// //        // Then
// //        verify(journeyRepository).findAll(pageable);
// //        verify(journeyRepository, times(1)).findAll(pageable);
// //        assertThat(result).isNotNull();
// //        assertThat(result).hasSize(2);
// //        assertThat(result).containsExactly(journey1, journey2);
// //    }

//     @Test
//     void canGetJourneyById() {
//         // Given
//         int id = 1;
//         Journey journey = new Journey(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, 2, 10, 10);
//         when(journeyRepository.findById(id)).thenReturn(Optional.of(journey));

//         // When
//         Journey result = journeyService.getJourneyById(id);

//         // Then
//         verify(journeyRepository).findById(id);
//         verify(journeyRepository, times(1)).findById(id);
//         assertThat(result).isNotNull();
//         assertThat(result).isEqualTo(journey);
//     }

//     @Test
//     void canNotGetJourneyById() {
//         // Given
//         int id = 1;
//         when(journeyRepository.findById(id)).thenReturn(Optional.empty());

//         // When & Then
//         assertThrows(JourneyNotFoundException.class, () -> {
//             journeyService.getJourneyById(id);
//         });
//     }
// }