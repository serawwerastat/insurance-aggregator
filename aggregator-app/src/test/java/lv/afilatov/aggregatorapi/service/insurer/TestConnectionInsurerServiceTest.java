package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createPremiumResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerGetXml.InsurerGetXmlService;

@ExtendWith(MockitoExtension.class)
public class TestConnectionInsurerServiceTest {

    @InjectMocks
    private TestConnectionInsurerService testConnectionInsurerService;
    @Mock
    private List<Insurance> insurances;
    @Mock
    private InsurerGetXmlService insurerGetXmlService;

    @Test
    public void testConnectionToInsurerTest() {
        var request = createInsurerRequest();
        when(insurances.stream()).thenReturn(Stream.of(insurerGetXmlService));
        when(insurerGetXmlService.getInsurerConnectionType()).thenReturn(request.getInsurerType());
        when(insurerGetXmlService.getPremium(any(), any())).thenReturn(createPremiumResponse());
        var response = testConnectionInsurerService.testConnectionToInsurer(request);
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testConnectionToInsurerFailedTest() {
        var request = createInsurerRequest();
        when(insurances.stream()).thenReturn(Stream.of(insurerGetXmlService));
        when(insurerGetXmlService.getInsurerConnectionType()).thenReturn(request.getInsurerType());
        when(insurerGetXmlService.getPremium(any(), any())).thenThrow(RuntimeException.class);
        var response = testConnectionInsurerService.testConnectionToInsurer(request);
        assertThat(response.getStatus(), is(500));
    }

}
