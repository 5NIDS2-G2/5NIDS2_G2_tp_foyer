package tn.esprit.tpfoyer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationRestControllerTest {

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationRestController reservationController;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    void setup() {
        reservation1 = new Reservation("id1", new Date(), true, null);
        reservation2 = new Reservation("id2", new Date(), false, null);
    }

    @Test
    void getReservations() {
        // Arrange
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationService.retrieveAllReservations()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationController.getReservations();

        // Assert
        assertEquals(2, result.size());
        verify(reservationService).retrieveAllReservations();
    }

    @Test
    void retrieveReservation() {
        // Arrange
        String reservationId = "id1";
        when(reservationService.retrieveReservation(reservationId)).thenReturn(reservation1);

        // Act
        Reservation result = reservationController.retrieveReservation(reservationId);

        // Assert
        assertEquals(reservation1, result);
        verify(reservationService).retrieveReservation(reservationId);
    }

    @Test
    void retrieveReservationParDateEtStatus() {
        // Arrange
        Date date = new Date();
        boolean status = true;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);

        when(reservationService.trouverResSelonDateEtStatus(date, status)).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationController.retrieveReservationParDateEtStatus(date, status);

        // Assert
        assertEquals(1, result.size());
        assertEquals(reservation1, result.get(0));
        verify(reservationService).trouverResSelonDateEtStatus(date, status);
    }

    @Test
    void addReservation() {
        // Arrange
        when(reservationService.addReservation(reservation1)).thenReturn(reservation1);

        // Act
        Reservation result = reservationController.addReservation(reservation1);

        // Assert
        assertEquals(reservation1, result);
        verify(reservationService).addReservation(reservation1);
    }

    @Test
    void removeReservation() {
        // Arrange
        String reservationId = "id1";

        // Act
        reservationController.removeReservation(reservationId);

        // Assert
        verify(reservationService).removeReservation(reservationId);
    }

    @Test
    void modifyReservation() {
        // Arrange
        when(reservationService.modifyReservation(reservation1)).thenReturn(reservation1);

        // Act
        Reservation result = reservationController.modifyReservation(reservation1);

        // Assert
        assertEquals(reservation1, result);
        verify(reservationService).modifyReservation(reservation1);
    }
}
