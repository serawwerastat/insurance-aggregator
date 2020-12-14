package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.repository.InsurerRepository;

@ExtendWith(MockitoExtension.class)
public class GetInsurerServiceTest {

    @InjectMocks
    private GetInsurerService getInsurerService;

    @Mock
    private InsurerRepository insurerRepository;

    @Test
    public void getAllInsurersTest() {
        var insurer1 = createInsurerModel();
        var insurer2 = createInsurerModel();
        var insurer3 = createInsurerModel();
        var expectedInsurers = List.of(insurer1, insurer2, insurer3);
        when(insurerRepository.findAll()).thenReturn(expectedInsurers);
        var foundInsurers = getInsurerService.getAllInsurers();
        assertThat(foundInsurers, is(expectedInsurers));
    }

}
