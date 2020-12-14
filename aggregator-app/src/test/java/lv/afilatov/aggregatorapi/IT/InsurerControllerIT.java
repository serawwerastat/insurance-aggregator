package lv.afilatov.aggregatorapi.IT;

import static lv.afilatov.aggregatorapi.test_util.TestAssertions.fieldsAreEqual;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;

public class InsurerControllerIT extends RestServiceTest {

    @Inject
    private InsurerRepository insurerRepository;

    @Test
    public void getAllInsurersTest() throws Exception {
        var result = mockMvc.perform(getAllInsurers())
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse().getContentAsString();
        var insurers = objectMapper.readValue(response, new TypeReference<List<InsurerModel>>(){});
        var allInsurers = insurerRepository.findAll();
        assertThat(insurers.size(), is(not(0)));
        assertThat(insurers.size(), is(allInsurers.size()));
    }

    @Test
    public void createInsurerTest() throws Exception {
        var request = createInsurerRequest();

        var result = mockMvc.perform(createInsurer(request))
                .andExpect(status().isOk())
                .andReturn();
        var response = result.getResponse().getContentAsString();

        var insurer = objectMapper.readValue(response, InsurerModel.class);
        fieldsAreEqual(insurer, request);
        assertThat(insurer.getId(), is(not(0)));
        insurerRepository.delete(insurer);
    }

    @Test
    public void testNewInsurerTest() throws Exception {
        var workingInsurer = insurerRepository.findAll().get(0);
        var request = createInsurerRequest(workingInsurer);

        mockMvc.perform(testNewInsurer(request))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateInsurerTest() throws Exception {
        var createRequest = createInsurerRequest();
        var createResult = mockMvc.perform(createInsurer(createRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var createdInsurer = objectMapper.readValue(createResult, InsurerModel.class);

        var updateRequest = createInsurerRequest();
        var updateResult = mockMvc.perform(updateInsurer(createdInsurer.getId(), updateRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var updatedInsurer = objectMapper.readValue(updateResult, InsurerModel.class);
        fieldsAreEqual(updatedInsurer, updateRequest);
        insurerRepository.delete(updatedInsurer);
    }

    @Test
    public void changeInsurerActiveStateTest() throws Exception {
        var createRequest = createInsurerRequest();
        var createResult = mockMvc.perform(createInsurer(createRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var createdInsurer = objectMapper.readValue(createResult, InsurerModel.class);

        var changeActiveStateResult = mockMvc.perform(changeInsurerActiveState(createdInsurer.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var insurerWithChangedActiveState = objectMapper.readValue(changeActiveStateResult, InsurerModel.class);
        assertThat(insurerWithChangedActiveState.isActive(), is(not(createdInsurer.isActive())));
        insurerRepository.delete(insurerWithChangedActiveState);
    }

    @Test
    public void deleteInsurerTest() throws Exception {
        var createRequest = createInsurerRequest();
        var createResult = mockMvc.perform(createInsurer(createRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var createdInsurer = objectMapper.readValue(createResult, InsurerModel.class);

        mockMvc.perform(deleteInsurer(createdInsurer.getId()))
                .andExpect(status().isOk())
                .andReturn();
        var notFoundInsurer = insurerRepository.findById(createdInsurer.getId());
        assertThat(notFoundInsurer.isPresent(), is(false));
    }





}
