package lv.afilatov.aggregatorapi.service.insurer;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class DeleteInsurerService extends CoreInsurerService {

    Response deleteInsurer(int id) {
        var insurer = findInsurerById(id);
        insurerRepository.delete(insurer);
        return Response.status(200).build();
    }
}
