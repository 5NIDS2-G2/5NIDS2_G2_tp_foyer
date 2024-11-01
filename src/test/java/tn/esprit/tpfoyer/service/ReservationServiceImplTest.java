package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;

    Set<Etudiant> etudiants = new HashSet<>() {
        {
            add(new Etudiant(12,"a","a",12,new Date(),null));
            add(new Etudiant(13,"b","b",13,new Date(),null));
        }
    };

    List<Reservation> listReservations = new ArrayList<>() {
        {
            add(new Reservation("id1", new Date(), true, etudiants));
            add(new Reservation("id2", new Date(), true, etudiants));
        }
    };
    Reservation reservation = new Reservation("id1", new Date(),true, etudiants );


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllReservations() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(listReservations);

        // Act
        List<Reservation> reservations = reservationService.retrieveAllReservations();

        // Assert
        assertEquals(2, reservations.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void retrieveReservation() {
        // Arrange
        when(reservationRepository.findById("id1")).thenReturn(Optional.of(reservation));

        // Act
        Reservation foundReservation = reservationService.retrieveReservation("id1");

        // Assert
        assertNotNull(foundReservation);
        assertEquals("id1", foundReservation.getIdReservation());
        verify(reservationRepository).findById("id1");
    }

    @Test
    void addReservation() {
        // Arrange
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation createdReservation = reservationService.addReservation(reservation);

        // Assert
        assertNotNull(createdReservation);
        assertEquals("id1", createdReservation.getIdReservation());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void modifyReservation() {
        // Arrange
        reservation.setEstValide(true); // Initial valid status
        lenient().when(reservationRepository.findById("id1")).thenReturn(Optional.of(reservation));

        // Modify the status to check if it updates correctly
        reservation.setEstValide(false); // This is the change we expect

        // Ensure the mock repository returns the updated reservation when saved
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation); // Mock save

        // Act
        Reservation updatedReservation = reservationService.modifyReservation(reservation);

        // Assert
        assertNotNull(updatedReservation, "Updated reservation should not be null");
        assertFalse(updatedReservation.isEstValide(), "Reservation status should be false after modification");
        verify(reservationRepository).save(reservation); // Ensure save is called with the modified reservation
    }

    @Test
    void trouverResSelonDateEtStatus() {
        // Arrange
        Date searchDate = new Date();
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(searchDate, true))
                .thenReturn(listReservations);

        // Act
        List<Reservation> foundReservations = reservationService.trouverResSelonDateEtStatus(searchDate, true);

        // Assert
        assertEquals(2, foundReservations.size());
        verify(reservationRepository).findAllByAnneeUniversitaireBeforeAndEstValide(searchDate, true);
    }

    @Test
    void removeReservation() {

        // Arrange
        lenient().when(reservationRepository.findById("id1")).thenReturn(Optional.of(reservation));

        // Act
        reservationService.removeReservation("id1");

        // Assert
        verify(reservationRepository).deleteById("id1");
    }
}