package lv.afilatov.aggregatorapi.service.insurer;

import static java.util.stream.Collectors.toMap;
import static lv.afilatov.aggregatorapi.util.UpdateUtil.updateModel;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;
import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;

public abstract class CoreInsurerService {

    @Inject
    List<Insurance> insurances;
    @Inject
    InsurerRepository insurerRepository;

    protected InsurerModel findInsurerById(int id) {
        return insurerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Insurer not found. Insure id: " + id));
    }

    protected void updateInsureBaseFields(InsurerModel insurer, InsurerRequest request) {
        updateModel(insurer::setInsuranceURL, request.getInsuranceURL());
        updateModel(insurer::setPremiumPath, request.getPremiumPath());
        updateModel(insurer::setName, request.getName());
        updateModel(insurer::setInsurerType, request.getInsurerType());
    }

    protected Map<InsurerConnectionType, Insurance> getInsurerTypeMappedByConnectionType() {
        return insurances.stream().collect(toMap(Insurance::getInsurerConnectionType, Function.identity()));
    }
}
