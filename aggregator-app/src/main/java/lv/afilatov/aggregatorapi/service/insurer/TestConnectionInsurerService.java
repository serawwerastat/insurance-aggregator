package lv.afilatov.aggregatorapi.service.insurer;

import java.time.LocalDate;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;

@Component
public class TestConnectionInsurerService extends CoreInsurerService {

    Response testConnectionToInsurer(InsurerRequest insurerRequest) {
        var insurerToTest = new InsurerModel().setActive(true);
        updateInsureBaseFields(insurerToTest, insurerRequest);

        var userDataForTesting = getUserDataForTesting();

        var insurerTypeMappedByConnectionType = getInsurerTypeMappedByConnectionType();
        try {
            insurerTypeMappedByConnectionType.get(insurerToTest.getInsurerType()).getPremium(userDataForTesting, insurerToTest);
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).build();
    }

    private UserDataRequest getUserDataForTesting() {
        return new UserDataRequest()
                .setDayOfBirth(LocalDate.now().minusYears(20))
                .setDrivingExperience(10)
                .setTechnicalPassportNumber("number");
    }

}
