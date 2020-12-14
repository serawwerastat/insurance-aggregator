package lv.afilatov.aggregatorapi.service.insurer;

import static java.util.Collections.emptyList;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;
import lv.afilatov.aggregatorapi.domain.model.InsurerModel;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;
import lv.afilatov.aggregatorapi.domain.response.PremiumResponse;
import lv.afilatov.aggregatorapi.service.insurer.insurareType.Insurance;
import lv.afilatov.aggregatorapi.util.FutureUtil;

@Component
public class PremiumInsurerService extends CoreInsurerService {

    private static final Logger LOG = Logger.getLogger(PremiumInsurerService.class);

    @Inject
    private FutureUtil futureUtil;

    List<PremiumResponse> getPremiums(UserDataRequest userDataRequest) {
        var activeInsurers = insurerRepository.findAllActive();
        if (isEmpty(activeInsurers)) {
            return emptyList();
        }

        var insurerTypeMappedByConnectionType = getInsurerTypeMappedByConnectionType();
        var premiumsFutures = activeInsurers.stream()
                .map(insurer -> supplyAsync(() -> getPremium(userDataRequest, insurer, insurerTypeMappedByConnectionType)))
                .collect(toList());
        return futureUtil.allOf(premiumsFutures)
                .join()
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(PremiumResponse::getPremium))
                .collect(toList());
    }

    private PremiumResponse getPremium(UserDataRequest userDataRequest, InsurerModel insurer,
            Map<InsurerConnectionType, Insurance> insurerTypeMappedByConnectionType) {
        try {
            return insurerTypeMappedByConnectionType.get(insurer.getInsurerType()).getPremium(userDataRequest, insurer);
        } catch (Exception e) {
            LOG.warn("Could not get premium from insurer " + insurer.getName(), e);
            return null;
        }
    }

}
