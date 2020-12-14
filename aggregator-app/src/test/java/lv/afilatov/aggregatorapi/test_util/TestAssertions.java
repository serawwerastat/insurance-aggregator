package lv.afilatov.aggregatorapi.test_util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;

public class TestAssertions {

    public static void fieldsAreEqual(InsurerModel insurer, InsurerRequest request){
        assertThat(insurer.getInsuranceURL(), is(request.getInsuranceURL()));
        assertThat(insurer.getPremiumPath(), is(request.getPremiumPath()));
        assertThat(insurer.getName(), is(request.getName()));
        assertThat(insurer.getInsurerType(), is(request.getInsurerType()));
    }

}
