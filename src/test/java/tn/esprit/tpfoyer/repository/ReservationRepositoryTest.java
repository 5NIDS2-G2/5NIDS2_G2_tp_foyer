package tn.esprit.tpfoyer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationRepositoryTest reservationRepositoryTest;

    private List<Reservation> reservations;

    @BeforeEach
    void setup() {
        // Initialize test data
        reservations = new ArrayList<>();
        reservations.add(new Reservation("id1", new Date(2023, 1, 1), true, null));
        reservations.add(new Reservation("id2", new Date(2022, 1, 1), false, null));
        reservations.add(new Reservation("id3", new Date(2024, 1, 1), true, null));
    }

    @Test
    void testFindAllByAnneeUniversitaireBeforeAndEstValide() {
        // Arrange
        Date testDate = new Date(2023, 1, 1);
        boolean estValide = true;

        // Mock the behavior of the repository
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(testDate, estValide))
                .thenReturn(List.of(reservations.get(0))); // Only return the reservation that meets the criteria

        // Act
        List<Reservation> result = reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(testDate, estValide);

        // Assert
        assertEquals(1, result.size());
        assertEquals("id1", result.get(0).getIdReservation());
    }
}
