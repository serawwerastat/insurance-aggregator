package lv.afilatov.aggregatorapi.service.insurer;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;

@Component
public class CreateInsurerService extends CoreInsurerService {

    InsurerModel createInsurer(InsurerRequest createRequest) {
        var newInsurer = new InsurerModel().setActive(true);
        updateInsureBaseFields(newInsurer, createRequest);
        return insurerRepository.save(newInsurer);
    }
}
