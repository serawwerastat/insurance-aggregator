package lv.afilatov.aggregatorapi.service.insurer;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;

@Component
public class UpdateInsurerService extends CoreInsurerService {

    InsurerModel updateInsurer(int id, InsurerRequest updateRequest) {
        var insurer = findInsurerById(id);
        updateInsureBaseFields(insurer, updateRequest);
        return insurerRepository.save(insurer);
    }

    InsurerModel changeInsurerActiveState(int id) {
        var insurer = findInsurerById(id);
        insurer.setActive(!insurer.isActive());
        return insurerRepository.save(insurer);
    }
}
