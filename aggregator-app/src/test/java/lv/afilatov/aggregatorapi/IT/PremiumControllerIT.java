package lv.afilatov.aggregatorapi.IT;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.repository.InsurerRepository;
import lv.afilatov.aggregatorapi.test_util.TestConstructors;


public class PremiumControllerIT extends RestServiceTest {


    @Inject
    private InsurerRepository insurerRepository;

    @Test
    void getPremiumsTest() throws Exception {
        var useData = TestConstructors.createUserDataRequest();

        var result = mockMvc.perform(getPremiums(useData))
                .andExpect(status().isOk())
                .andReturn();

        var response = result.getResponse().getContentAsString();
        var premiums = objectMapper.readValue(response, new TypeReference<List<PremiumResponse>>(){});
        var allInsurers = insurerRepository.findAll();
        assertThat(premiums.size(), is(not(0)));
        assertThat(premiums.stream().allMatch(premium -> premium.getPremium() != null && !isBlank(premium.getName())), is(true));
        assertThat(premiums.size(), is(allInsurers.size()));
    }


}
