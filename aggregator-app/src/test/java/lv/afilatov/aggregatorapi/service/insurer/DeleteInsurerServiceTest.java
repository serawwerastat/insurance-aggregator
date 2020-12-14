package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.repository.InsurerRepository;

@ExtendWith(MockitoExtension.class)
public class DeleteInsurerServiceTest {

    @InjectMocks
    private DeleteInsurerService deleteInsurerService;
    @Mock
    private InsurerRepository insurerRepository;

    @Test
    public void deleteInsurerTest() {
        var id = new Random().nextInt();
        var insurer = createInsurerModel();
        when(insurerRepository.findById(id)).thenReturn(Optional.of(insurer));
        var response = deleteInsurerService.deleteInsurer(id);
        assertThat(response.getStatus(), is(200));
        verify(insurerRepository).delete(insurer);
    }

}
