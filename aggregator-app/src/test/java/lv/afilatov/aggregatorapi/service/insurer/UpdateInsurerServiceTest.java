package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestAssertions.fieldsAreEqual;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;

@ExtendWith(MockitoExtension.class)
public class UpdateInsurerServiceTest {

    @InjectMocks
    private UpdateInsurerService updateInsurerService;
    @Mock
    private InsurerRepository insurerRepository;

    @Test
    public void updateInsurerTest() {
        var id = new Random().nextInt();
        var insurer = createInsurerModel();
        var updateRequest = createInsurerRequest();
        when(insurerRepository.findById(id)).thenReturn(Optional.of(insurer));
        when(insurerRepository.save(any(InsurerModel.class))).thenAnswer(i -> i.getArguments()[0]);
        var updatedInsurer = updateInsurerService.updateInsurer(id, updateRequest);
        fieldsAreEqual(updatedInsurer, updateRequest);
    }

    @Test
    public void changeInsurerActiveStateDeactivateTest() {
        var id = new Random().nextInt();
        var insurer = new InsurerModel().setActive(true);
        when(insurerRepository.findById(id)).thenReturn(Optional.of(insurer));
        when(insurerRepository.save(any(InsurerModel.class))).thenAnswer(i -> i.getArguments()[0]);
        var deactivatedInsurer = updateInsurerService.changeInsurerActiveState(id);
        assertThat(deactivatedInsurer.isActive(), is(false));
    }

    @Test
    public void changeInsurerActiveStateActivateTest() {
        var id = new Random().nextInt();
        var insurer = new InsurerModel().setActive(false);
        when(insurerRepository.findById(id)).thenReturn(Optional.of(insurer));
        when(insurerRepository.save(any(InsurerModel.class))).thenAnswer(i -> i.getArguments()[0]);
        var deactivatedInsurer = updateInsurerService.changeInsurerActiveState(id);
        assertThat(deactivatedInsurer.isActive(), is(true));
    }
}
