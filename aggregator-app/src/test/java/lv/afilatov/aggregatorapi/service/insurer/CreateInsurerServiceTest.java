package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestAssertions.fieldsAreEqual;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;

@ExtendWith(MockitoExtension.class)
public class CreateInsurerServiceTest {

    @InjectMocks
    private CreateInsurerService createInsurerService;
    @Mock
    private InsurerRepository insurerRepository;

    @Test
    public void createInsurerTest() {
        var createRequest = createInsurerRequest();
        when(insurerRepository.save(any(InsurerModel.class))).thenAnswer(i -> i.getArguments()[0]);
        var createdInsurer = createInsurerService.createInsurer(createRequest);
        fieldsAreEqual(createdInsurer, createRequest);
        assertThat(createdInsurer.isActive(), is(true));
    }
}
