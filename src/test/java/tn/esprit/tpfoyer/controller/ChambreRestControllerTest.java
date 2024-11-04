package tn.esprit.tpfoyer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.control.ChambreRestController;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Use MockitoExtension to handle @Mock and @InjectMocks initialization
public class ChambreRestControllerTest {

    @Mock
    private IChambreService chambreService;

    @InjectMocks
    private ChambreRestController chambreRestController;

    @Test
    public void testGetChambres() {
        // Arrange: create a list of dummy Chambre objects
        List<Chambre> dummyChambres = new ArrayList<>();
        dummyChambres.add(new Chambre());
        dummyChambres.add(new Chambre());

        // Mock the service method
        when(chambreService.retrieveAllChambres()).thenReturn(dummyChambres);

        // Act: call the controller method
        List<Chambre> result = chambreRestController.getChambres();

        // Assert: check if the result matches the expected output
        assertEquals(2, result.size(), "The number of chambres should be 2");
    }
}
