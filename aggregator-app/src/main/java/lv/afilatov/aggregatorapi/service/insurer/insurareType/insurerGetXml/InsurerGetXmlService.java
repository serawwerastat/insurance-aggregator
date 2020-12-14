package lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerGetXml;

import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;
import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.service.WebTargetService;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;

@Component
public class InsurerGetXmlService implements Insurance {

    private final InsurerConnectionType insurerConnectionType = InsurerConnectionType.GET_XML;

    @Inject
    private WebTargetService webTargetService;

    @Override
    public PremiumResponse getPremium(UserDataRequest userDataRequest, InsurerModel insurerModel) {
        var response = webTargetService.getTargetFor(insurerModel)
                .queryParam("tpnr", userDataRequest.getTechnicalPassportNumber())
                .queryParam("dob", userDataRequest.fetchFormattedDayOfBirth())
                .request(APPLICATION_XML_TYPE)
                .get(String.class);
        //had to use String.class then XmlMapper,
        //because jax-rs xml converter produced MessageBodyProviderNotFoundException for some reasons
        try {
            var quote = new XmlMapper().readValue(response, InsurerGetXmlResponse.class);
            return new PremiumResponse(insurerModel.getName(), quote.getPremium());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("Failed to convert xml response to premium response", e);
        }
    }

    @Override public InsurerConnectionType getInsurerConnectionType() {
        return insurerConnectionType;
    }

}
