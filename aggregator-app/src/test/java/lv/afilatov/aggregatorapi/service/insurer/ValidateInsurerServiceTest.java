package lv.afilatov.aggregatorapi.service.insurer;

import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.BadRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidateInsurerServiceTest {

    @InjectMocks
    private ValidateInsurerService validateInsurerService;

    @Test
    public void createInsurerTest() {
        var request = createInsurerRequest();
        var validatedRequest = validateInsurerService.validateInsurerRequest(request);
        assertThat(validatedRequest, is(request));
    }

    @Test
    public void createInsurerNullRequestTest() {
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(null) ) ;
    }

    @Test
    public void createInsurerNullUrlTest() {
        var request = createInsurerRequest().setInsuranceURL(null);
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerBlankUrlTest() {
        var request = createInsurerRequest().setInsuranceURL("");
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerNullPremiumPathTest() {
        var request = createInsurerRequest().setPremiumPath(null);
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerBlankPremiumPathTest() {
        var request = createInsurerRequest().setPremiumPath("");
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerNullNameTest() {
        var request = createInsurerRequest().setName(null);
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerBlankNameTest() {
        var request = createInsurerRequest().setName("");
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

    @Test
    public void createInsurerNullInsureTypeTest() {
        var request = createInsurerRequest().setInsurerType(null);
        assertThrows(BadRequestException.class, () -> validateInsurerService.validateInsurerRequest(request) ) ;
    }

}
