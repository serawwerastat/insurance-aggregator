package lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerPostJson;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;
import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.service.WebTargetService;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;

@Component
public class InsurerPostJsonService implements Insurance {

    private final InsurerConnectionType insurerConnectionType = InsurerConnectionType.POST_JSON;

    @Inject
    private WebTargetService webTargetService;

    @Override
    public PremiumResponse getPremium(UserDataRequest userDataRequest, InsurerModel insurerModel) {
        var insurerRequest = InsurancePostJsonRequest.from(userDataRequest);
        var response = webTargetService.getTargetFor(insurerModel)
                .request(APPLICATION_JSON_TYPE)
                .post(Entity.json(insurerRequest), InsurancePostJsonResponse.class);
        return new PremiumResponse(insurerModel.getName(), response.getQoute());
    }

    @Override public InsurerConnectionType getInsurerConnectionType() {
        return insurerConnectionType;
    }

}
