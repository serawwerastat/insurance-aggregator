package lv.afilatov.aggregatorapi.util;

import java.util.function.Function;

public class UpdateUtil {

    public static <T, U> void updateModel(Function<T, U> updater, T value) {
        if (value instanceof String && ((String) value).isBlank()) {
            return;
        }
        if (value != null) {
            updater.apply(value);
        }
    }
}
