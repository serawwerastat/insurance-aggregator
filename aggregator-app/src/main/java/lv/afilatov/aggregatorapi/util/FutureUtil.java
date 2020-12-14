package lv.afilatov.aggregatorapi.util;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class FutureUtil {

    @Inject
    private ExecutorService executorService;

    public <T> CompletableFuture<List<T>> allOf(
            Collection<CompletableFuture<T>> futures) {
        return futures.stream()
                .collect(Collectors.collectingAndThen(
                        toList(),
                        l -> CompletableFuture.allOf(l.toArray(new CompletableFuture[0]))
                                .thenApply(__ -> l.stream()
                                        .map(CompletableFuture::join)
                                        .collect(Collectors.toList()))));
    }

    public <T> CompletableFuture<T> computeAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(withMDC(supplier), executorService);
    }

    private static <U> Supplier withMDC(Supplier<U> supplier) {
        Map<String, String> mdc = MDC.getCopyOfContextMap();
        return () -> {
            if (mdc != null) {
                MDC.setContextMap(mdc);
            } else {
                MDC.clear();
            }
            return supplier.get();
        };
    }
}
