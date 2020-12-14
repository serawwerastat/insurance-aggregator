package lv.afilatov.aggregatorapi.service.insurer;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;

@Component
public class InsurerService {

    @Inject
    private GetInsurerService getInsurerService;
    @Inject
    private CreateInsurerService createInsurerService;
    @Inject
    private UpdateInsurerService updateInsurerService;
    @Inject
    private TestConnectionInsurerService testConnectionInsurerService;
    @Inject
    private DeleteInsurerService deleteInsurerService;
    @Inject
    private PremiumInsurerService premiumInsurerService;
    @Inject
    private ValidateInsurerService validateInsurerService;

    public List<PremiumResponse> getPremiums(UserDataRequest userDataRequest) {
        return premiumInsurerService.getPremiums(userDataRequest);
    }

    public List<InsurerModel> getAllInsurers() {
        return getInsurerService.getAllInsurers();
    }

    public InsurerModel createInsurer(InsurerRequest createRequest) {
        validateInsurerService.validateInsurerRequest(createRequest);
        return createInsurerService.createInsurer(createRequest);
    }

    public Response testNewInsurer(InsurerRequest insurerRequest) {
        validateInsurerService.validateInsurerRequest(insurerRequest);
        return testConnectionInsurerService.testConnectionToInsurer(insurerRequest);
    }

    public InsurerModel updateInsurer(int id, InsurerRequest updateRequest) {
        validateInsurerService.validateInsurerRequest(updateRequest);
        return updateInsurerService.updateInsurer(id, updateRequest);
    }

    public InsurerModel changeInsurerActiveState(int id) {
        return updateInsurerService.changeInsurerActiveState(id);
    }

    public Response deleteInsurer(int id) {
        return deleteInsurerService.deleteInsurer(id);
    }

}
