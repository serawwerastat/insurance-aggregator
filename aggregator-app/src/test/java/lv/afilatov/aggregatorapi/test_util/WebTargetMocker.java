package lv.afilatov.aggregatorapi.test_util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.mockito.stubbing.OngoingStubbing;

public class WebTargetMocker {

    private final Builder builder = mock(Builder.class);

    private final WebTarget target;

    private WebTargetMocker(WebTarget target) {
        this.target = target;
    }

    public static WebTargetMocker from(WebTarget target) {
        return new WebTargetMocker(target);
    }

    public WebTargetMocker path(String path) {
        when(this.target.path(path)).thenReturn(this.target);
        return this;
    }

    public WebTargetMocker queryParam(String param, String value) {
        when(this.target.queryParam(param, value)).thenReturn(this.target);
        return this;
    }

    public WebTargetMocker queryParam(String param, int value) {
        when(this.target.queryParam(param, value)).thenReturn(this.target);
        return this;
    }

    public WebTargetMocker queryParam(String param, LocalDate value) {
        when(this.target.queryParam(param, value)).thenReturn(this.target);
        return this;
    }

    public WebTargetMocker queryParam(String param, Object[] value) {
        when(this.target.queryParam(param, value)).thenReturn(this.target);
        return this;
    }

    public WebTargetMocker request(MediaType mediaType) {
        when(this.target.request(mediaType)).thenReturn(builder);
        return this;
    }

    public WebTargetMocker request() {
        when(this.target.request()).thenReturn(builder);
        return this;
    }

    public <T> OngoingStubbing<T> get(Class<T> responseClass) {
        return when(builder.get(responseClass));
    }

    public <T> OngoingStubbing<T> get(GenericType<T> responseType) {
        return when(builder.get(responseType));
    }

    public <T> OngoingStubbing<T> post(Entity<?> entity, Class<T> responseClass) {
        return when(builder.post(entity, responseClass));
    }

//    public <T> OngoingStubbing<T> post(Entity<?> entity, GenericType<T> responseType) {
//        return when(builder.post(entity, responseType));
//    }
}
