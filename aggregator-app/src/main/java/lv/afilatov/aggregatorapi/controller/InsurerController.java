package lv.afilatov.aggregatorapi.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.service.insurer.InsurerService;

@CrossOrigin
@RestController
@RequestMapping(path = "/insurer", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsurerController {

    @Autowired
    private InsurerService insurerService;

    @GetMapping()
    public List<InsurerModel> getAllInsurers() {
        return insurerService.getAllInsurers();
    }

    @PutMapping()
    public InsurerModel createInsurer(@RequestBody InsurerRequest request) {
        return insurerService.createInsurer(request);
    }

    @PutMapping("/test")
    public Response testNewInsurer(@RequestBody InsurerRequest request) {
        return insurerService.testNewInsurer(request);
    }

    @PostMapping("/{id}")
    public InsurerModel updateInsurer(
            @PathVariable("id") int id,
            @RequestBody InsurerRequest request) {
        return insurerService.updateInsurer(id, request);
    }

    @PostMapping("/active/{id}")
    public InsurerModel changeInsurerActiveState(@PathVariable("id") int id) {
        return insurerService.changeInsurerActiveState(id);
    }

    @DeleteMapping("{id}")
    public Response deleteInsurer(@PathVariable("id") int id) {
        return insurerService.deleteInsurer(id);
    }

}
