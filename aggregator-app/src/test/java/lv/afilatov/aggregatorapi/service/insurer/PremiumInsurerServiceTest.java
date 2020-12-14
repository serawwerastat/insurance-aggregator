package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType.GET_XML;
import static lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType.POST_JSON;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createPremiumResponse;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createUserDataRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import static java.util.Collections.emptyList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.repository.InsurerRepository;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerGetXml.InsurerGetXmlService;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerPostJson.InsurerPostJsonService;
import lv.afilatov.aggregatorapi.util.FutureUtil;

@ExtendWith(MockitoExtension.class)
public class PremiumInsurerServiceTest {

    @InjectMocks
    private PremiumInsurerService premiumInsurerService;
    @Mock
    private List<Insurance> insurances;
    @Mock
    private InsurerGetXmlService insurerGetXmlService;
    @Mock
    private InsurerPostJsonService insurerPostJsonService;
    @Mock
    private InsurerRepository insurerRepository;

    @Spy
    private FutureUtil futureUtil;

    @Test
    public void getPremiumsTest() {
        var request = createUserDataRequest();

        var insurer1 = createInsurerModel().setInsurerType(GET_XML);
        var insurer2 = createInsurerModel().setInsurerType(POST_JSON);
        when(insurerRepository.findAllActive()).thenReturn(List.of(insurer1, insurer2));

        when(insurances.stream()).thenReturn(Stream.of(insurerGetXmlService, insurerPostJsonService));

        when(insurerGetXmlService.getInsurerConnectionType()).thenReturn(GET_XML);
        when(insurerGetXmlService.getPremium(request, insurer1)).thenReturn(createPremiumResponse());

        when(insurerPostJsonService.getInsurerConnectionType()).thenReturn(POST_JSON);
        when(insurerPostJsonService.getPremium(request, insurer2)).thenReturn(createPremiumResponse());


        var premiums = premiumInsurerService.getPremiums(request);
        assertThat(premiums.size(), is(2));
        assertThat(premiums.get(0).getPremium() <= premiums.get(1).getPremium(), is(true));
    }

    @Test
    public void getPremiumsNoInsurersTest() {
        var request = createUserDataRequest();
        when(insurerRepository.findAllActive()).thenReturn(emptyList());
        var premiums = premiumInsurerService.getPremiums(request);
        assertThat(premiums, is(emptyList()));

    }


}
