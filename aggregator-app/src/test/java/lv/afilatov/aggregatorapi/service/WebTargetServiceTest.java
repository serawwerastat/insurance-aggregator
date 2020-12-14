package lv.afilatov.aggregatorapi.service;
import static lv.afilatov.aggregatorapi.test_util.TestConstructors.createInsurerModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WebTargetServiceTest {

    @InjectMocks
    private WebTargetService webTargetService;

    @Test
    public void getTargetForTest(){
            var insurer = createInsurerModel();
            var target = webTargetService.getTargetFor(insurer);
            var expectedUri = String.format("%s/%s", insurer.getInsuranceURL(), insurer.getPremiumPath());
            assertThat(target.getUri().toString(), is(expectedUri));
    }

    @Test
    public void getTargetForNullInsurerTest(){
        assertThrows(IllegalArgumentException.class, () -> webTargetService.getTargetFor(null));
    }

    @Test
    public void getTargetForNullURLTest(){
        var insurer = createInsurerModel().setInsuranceURL(null);
        assertThrows(IllegalArgumentException.class, () -> webTargetService.getTargetFor(insurer));
    }

    @Test
    public void getTargetForBlankURLTest(){
        var insurer = createInsurerModel().setInsuranceURL("");
        assertThrows(IllegalArgumentException.class, () -> webTargetService.getTargetFor(insurer));
    }

    @Test
    public void getTargetForNullPremiumPathTest(){
        var insurer = createInsurerModel().setPremiumPath(null);
        assertThrows(IllegalArgumentException.class, () -> webTargetService.getTargetFor(insurer));
    }

    @Test
    public void getTargetForBlankPremiumPathTest(){
        var insurer = createInsurerModel().setPremiumPath("");
        assertThrows(IllegalArgumentException.class, () -> webTargetService.getTargetFor(insurer));
    }
}
