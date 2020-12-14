package lv.afilatov.aggregatorapi.service.insurer;

import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;

@Component
public class ValidateInsurerService extends CoreInsurerService {

    InsurerRequest validateInsurerRequest(InsurerRequest insurerRequest) {
        if (insurerRequest == null) {
            throw new BadRequestException("Received null insurer request");
        }
        if (mandatoryFieldsNotPresent(insurerRequest)) {
            throw new BadRequestException("Received insurer request with one or more mandatory fields not present. " + insurerRequest);
        }
        return insurerRequest;
    }

    private boolean mandatoryFieldsNotPresent(InsurerRequest insurerRequest) {
        return isBlank(insurerRequest.getInsuranceURL())
                || isBlank(insurerRequest.getPremiumPath())
                || isBlank(insurerRequest.getName())
                || insurerRequest.getInsurerType() == null;
    }
}
