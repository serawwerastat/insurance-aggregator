package lv.afilatov.aggregatorapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.service.insurer.InsurerService;

@CrossOrigin
@RestController
@RequestMapping(path = "/premium", produces = MediaType.APPLICATION_JSON_VALUE)
public class PremiumController {

    @Autowired
    private InsurerService insurerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PremiumResponse> getPremiums(@RequestBody UserDataRequest userDataRequest) {
        return insurerService.getPremiums(userDataRequest);
    }

}
