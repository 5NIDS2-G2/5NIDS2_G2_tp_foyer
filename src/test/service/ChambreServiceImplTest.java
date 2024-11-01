//package service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import tn.esprit.tpfoyer.entity.Chambre;
//import tn.esprit.tpfoyer.repository.ChambreRepository;
//import tn.esprit.tpfoyer.service.ChambreServiceImpl;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class ChambreServiceImplTest {
//
//    @InjectMocks
//    private ChambreServiceImpl chambreService;
//
//    @Mock
//    private ChambreRepository chambreRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRetrieveAllChambres() {
//        Chambre chambre1 = new Chambre();
//        Chambre chambre2 = new Chambre();
//
//        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre1, chambre2));
//
//        List<Chambre> chambres = chambreService.retrieveAllChambres();
//
//        assertEquals(2, chambres.size());
//        verify(chambreRepository, times(1)).findAll();
//    }
//}
