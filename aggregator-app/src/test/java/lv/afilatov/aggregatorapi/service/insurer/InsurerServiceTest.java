package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createPremiumResponse;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createUserDataRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InsurerServiceTest {

    @InjectMocks
    private InsurerService insurerService;
    @Mock
    private GetInsurerService getInsurerService;
    @Mock
    private CreateInsurerService createInsurerService;
    @Mock
    private UpdateInsurerService updateInsurerService;
    @Mock
    private TestConnectionInsurerService testConnectionInsurerService;
    @Mock
    private DeleteInsurerService deleteInsurerService;
    @Mock
    private PremiumInsurerService premiumInsurerService;
    @Mock
    private ValidateInsurerService validateInsurerService;

    @Test
    public void getPremiumsTest(){
        var userDataRequest = createUserDataRequest();
        var premiums = List.of(createPremiumResponse());
        when(premiumInsurerService.getPremiums(userDataRequest)).thenReturn(premiums);
        var premiumsReturned = insurerService.getPremiums(userDataRequest);
        assertThat(premiumsReturned, is(premiums));
    }

    @Test
    public void getAllInsurersTest(){
        var insurers = List.of(createInsurerModel());
        when(getInsurerService.getAllInsurers()).thenReturn(insurers);
        var insurerReturned = insurerService.getAllInsurers();
        assertThat(insurerReturned, is(insurers));
    }

    @Test
    public void createInsurerTest() {
        var createRequest = createInsurerRequest();
        var createdInsurer = createInsurerModel();
        when(validateInsurerService.validateInsurerRequest(createRequest)).thenReturn(createRequest);
        when(createInsurerService.createInsurer(createRequest)).thenReturn(createdInsurer);
        var createdInsurerReturned = insurerService.createInsurer(createRequest);
        assertThat(createdInsurerReturned, is(createdInsurer));
    }

    @Test
    public void testNewInsurerTest() {
        var request = createInsurerRequest();
        var response = Response.status(200).build();
        when(validateInsurerService.validateInsurerRequest(request)).thenReturn(request);
        when(testConnectionInsurerService.testConnectionToInsurer(request)).thenReturn(response);
        var responseReturned = insurerService.testNewInsurer(request);
        assertThat(responseReturned, is(response));
    }

    @Test
    public void updateInsurerTest() {
        var id = new Random().nextInt();
        var updateRequest = createInsurerRequest();
        var updatedInsurer = createInsurerModel();
        when(validateInsurerService.validateInsurerRequest(updateRequest)).thenReturn(updateRequest);
        when(updateInsurerService.updateInsurer(id, updateRequest)).thenReturn(updatedInsurer);
        var updatedInsurerReturned = insurerService.updateInsurer(id, updateRequest);
        assertThat(updatedInsurerReturned, is(updatedInsurer));
    }

    @Test
    public void changeInsurerActiveStateTest() {
        var id = new Random().nextInt();
        var insurerWithChangedActiveStatus = createInsurerModel();
        when(updateInsurerService.changeInsurerActiveState(id)).thenReturn(insurerWithChangedActiveStatus);
        var insurerWithChangedActiveStatusReturned = insurerService.changeInsurerActiveState(id);
        assertThat(insurerWithChangedActiveStatusReturned, is(insurerWithChangedActiveStatus));
    }

    @Test
    public void deleteInsurerTest() {
        var id = new Random().nextInt();
        var response = Response.status(200).build();
        when(deleteInsurerService.deleteInsurer(id)).thenReturn(response);
        var responseReturned = insurerService.deleteInsurer(id);
        assertThat(responseReturned, is(response));
    }
}
