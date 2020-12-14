package lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerGetXml;

import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createUserDataRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lv.afilatov.aggregatorapi.service.WebTargetService;
import lv.afilatov.aggregatorapi.test_util.WebTargetMocker;

@ExtendWith(MockitoExtension.class)
public class InsurerGetXmlServiceTest {

    @InjectMocks
    private InsurerGetXmlService insurerGetXmlService;
    @Mock
    private WebTargetService webTargetService;
    @Mock
    private WebTarget target;

    @Test
    public void getPremiumTest(){
        var insurer = createInsurerModel();
        var userDataRequest = createUserDataRequest();

        var expectedPremium = 1.234d;
        var premiumXml = String.format("<Quote><premium>%s</premium></Quote>", expectedPremium);

        when(webTargetService.getTargetFor(insurer)).thenReturn(target);
        WebTargetMocker.from(target)
                .queryParam("tpnr", userDataRequest.getTechnicalPassportNumber())
                .queryParam("dob", userDataRequest.fetchFormattedDayOfBirth())
                .request(APPLICATION_XML_TYPE)
                .get(String.class)
                .thenReturn(premiumXml);

        var premium = insurerGetXmlService.getPremium(userDataRequest, insurer);
        assertThat(premium.getPremium(), is(expectedPremium));
        assertThat(premium.getName(), is(insurer.getName()));
    }

}
