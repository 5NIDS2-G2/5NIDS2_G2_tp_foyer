package tn.esprit.tpfoyer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlocRepositoryTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private IBlocService blocService;  // Assuming you have a service layer that interacts with the repository

    private Bloc mockBloc;

    @BeforeEach
    public void setUp() {
        mockBloc = new Bloc();
        mockBloc.setIdBloc(1L);
        mockBloc.setNomBloc("Bloc1");
        mockBloc.setCapaciteBloc(100L);
    }

    @Test
    void testFindAllByCapaciteBlocGreaterThan() {
        when(blocRepository.findAllByCapaciteBlocGreaterThan(anyLong())).thenReturn(List.of(mockBloc));

        List<Bloc> blocs = blocRepository.findAllByCapaciteBlocGreaterThan(50L);

        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertEquals("Bloc1", blocs.get(0).getNomBloc());
        verify(blocRepository, times(1)).findAllByCapaciteBlocGreaterThan(anyLong());
    }

    @Test
    void testFindAllByNomBlocStartingWith() {
        when(blocRepository.findAllByNomBlocStartingWith(anyString())).thenReturn(List.of(mockBloc));

        List<Bloc> blocs = blocRepository.findAllByNomBlocStartingWith("Bl");

        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertTrue(blocs.get(0).getNomBloc().startsWith("Bl"));
        verify(blocRepository, times(1)).findAllByNomBlocStartingWith(anyString());
    }

    @Test
    void testFindAllByNomBlocAndCapaciteBloc() {
        when(blocRepository.findAllByNomBlocAndCapaciteBloc(anyString(), anyLong())).thenReturn(List.of(mockBloc));

        List<Bloc> blocs = blocRepository.findAllByNomBlocAndCapaciteBloc("Bloc1", 100L);

        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertEquals("Bloc1", blocs.get(0).getNomBloc());
        assertEquals(100L, blocs.get(0).getCapaciteBloc());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc(anyString(), anyLong());
    }

    @Test
    void testFindByNomBloc() {
        when(blocRepository.findByNomBloc(anyString())).thenReturn(mockBloc);

        Bloc bloc = blocRepository.findByNomBloc("Bloc1");

        assertNotNull(bloc);
        assertEquals("Bloc1", bloc.getNomBloc());
        verify(blocRepository, times(1)).findByNomBloc(anyString());
    }

    @Test
    void testFindBlocByNomBlocAndCapaciteBlocGreaterThan() {
        when(blocRepository.findBlocByNomBlocAndCapaciteBlocGreaterThan(anyString(), anyLong())).thenReturn(mockBloc);

        Bloc bloc = blocRepository.findBlocByNomBlocAndCapaciteBlocGreaterThan("Bloc1", 50L);

        assertNotNull(bloc);
        assertEquals("Bloc1", bloc.getNomBloc());
        verify(blocRepository, times(1)).findBlocByNomBlocAndCapaciteBlocGreaterThan(anyString(), anyLong());
    }

    @Test
    void testFindAllByFoyerIsNull() {
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(List.of(mockBloc));

        List<Bloc> blocs = blocRepository.findAllByFoyerIsNull();

        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertNull(blocs.get(0).getFoyer());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }
}
