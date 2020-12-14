package lv.afilatov.aggregatorapi.service.insurer.insurareType;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;

public interface Insurance {

    PremiumResponse getPremium(UserDataRequest userDataRequest, InsurerModel insurerModel);

    InsurerConnectionType getInsurerConnectionType();
}
