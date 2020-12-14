package lv.afilatov.aggregatorapi.service;

import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;

@Component
public class WebTargetService {

    public WebTarget getTargetFor(InsurerModel insurerModel) {
        if (insurerModel == null) {
            throw new IllegalArgumentException("Could not create web target. Received null insurer model");
        }
        if (isBlank(insurerModel.getInsuranceURL()) || isBlank(insurerModel.getPremiumPath())) {
            var exceptionMsg = String.format("Could not create web target. Received insurer model had null URL or premium "
                    + "path. URL: %s. Premium path: %s", insurerModel.getInsuranceURL(), insurerModel.getPremiumPath());
            throw new IllegalArgumentException(exceptionMsg);
        }
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
        return client.target(insurerModel.getInsuranceURL()).path(insurerModel.getPremiumPath());
    }
}
