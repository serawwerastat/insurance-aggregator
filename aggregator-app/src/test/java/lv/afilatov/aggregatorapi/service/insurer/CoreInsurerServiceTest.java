package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestAssertions.fieldsAreEqual;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;

import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;

@ExtendWith(MockitoExtension.class)
public class CoreInsurerServiceTest {

    @InjectMocks
    private TestCoreInsurerService coreInsurerService;
    @Mock
    private InsurerRepository insurerRepository;

    @Test
    public void findInsurerByIdTest() {
        var id = new Random().nextInt();
        var insurer = createInsurerModel();
        when(insurerRepository.findById(id)).thenReturn(Optional.of(insurer));
        var foundInsurer = coreInsurerService.findInsurerById(id);
        assertThat(foundInsurer, is(insurer));
    }

    @Test
    public void findInsurerByIdNotFoundTest() {
        var id = new Random().nextInt();
        when(insurerRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> coreInsurerService.findInsurerById(id));
    }

    @Test
    public void updateInsureBaseFieldsTest() {
        var insurer = createInsurerModel();
        var request = createInsurerRequest();
        coreInsurerService.updateInsureBaseFields(insurer, request);
        fieldsAreEqual(insurer, request);
    }

    @Test
    public void updateInsureBaseFieldsNullRequestFieldsTest() {
        var insurer = createInsurerModel();
        var request = new InsurerRequest();
        coreInsurerService.updateInsureBaseFields(insurer, request);
        assertThat(insurer.getInsuranceURL(), notNullValue());
        assertThat(insurer.getPremiumPath(), notNullValue());
        assertThat(insurer.getName(), notNullValue());
        assertThat(insurer.getInsurerType(), notNullValue());
    }

    private static class TestCoreInsurerService extends CoreInsurerService {

    }
}
