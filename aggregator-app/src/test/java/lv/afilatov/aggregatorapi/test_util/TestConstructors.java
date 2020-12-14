package lv.afilatov.aggregatorapi.test_util;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.Random;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;
import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;

public class TestConstructors {

    public static InsurerModel createInsurerModel(){
        return new InsurerModel()
                .setInsuranceURL("insurer-url-" + randomAlphabetic(4))
                .setPremiumPath("premium-path-" + randomAlphabetic(4))
                .setName("insurer-name-" + randomAlphabetic(4))
                .setInsurerType(InsurerConnectionType.GET_XML)
                .setActive(true);
    }

    public static InsurerRequest createInsurerRequest(){
        return new InsurerRequest()
                .setInsuranceURL("insurer-url-" + randomAlphabetic(4))
                .setPremiumPath("premium-path-" + randomAlphabetic(4))
                .setName("insurer-name-" + randomAlphabetic(4))
                .setInsurerType(InsurerConnectionType.GET_XML);
    }

    public static InsurerRequest createInsurerRequest(InsurerModel insurer){
        return new InsurerRequest()
                .setInsuranceURL(insurer.getInsuranceURL())
                .setPremiumPath(insurer.getPremiumPath())
                .setName("insurer-name-" + randomAlphabetic(4))
                .setInsurerType(insurer.getInsurerType());
    }

    public static UserDataRequest createUserDataRequest(){
        var age = new Random().nextInt(80);
        return new UserDataRequest()
                .setTechnicalPassportNumber("technical-passport-number-" + randomAlphabetic(4))
                .setDrivingExperience(new Random().nextInt(age))
                .setDayOfBirth(now().minusYears(age));
    }

    public static PremiumResponse createPremiumResponse(){
        return new PremiumResponse("name-" + randomAlphabetic(4), new Random().nextDouble());
    }
}
