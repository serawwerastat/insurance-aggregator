package lv.afilatov.aggregatorapi.service.insurer;

import java.util.List;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;

@Component
public class GetInsurerService extends CoreInsurerService {

    List<InsurerModel> getAllInsurers() {
        return insurerRepository.findAll();
    }

}
