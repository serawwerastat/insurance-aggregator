package lv.afilatov.aggregatorapi.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lv.afilatov.aggregatorapi.domain.request.InsurerRequest;
import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceTest {

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected MockHttpServletRequestBuilder getPremiums(UserDataRequest useData) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(useData);
        return post("/premium", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
    }

    protected MockHttpServletRequestBuilder getAllInsurers() {
        return get("/insurer", 42L)
                .contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder createInsurer(InsurerRequest request) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(request);
        return put("/insurer", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
    }

    protected MockHttpServletRequestBuilder testNewInsurer(InsurerRequest request) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(request);
        return put("/insurer/test", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
    }


    protected MockHttpServletRequestBuilder updateInsurer(int id, InsurerRequest request) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(request);
        return post("/insurer/" + id, 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
    }

    protected MockHttpServletRequestBuilder changeInsurerActiveState(int id){
        return post("/insurer/active/" + id, 42L)
                .contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder deleteInsurer(int id){
        return delete("/insurer/" + id, 42L)
                .contentType(MediaType.APPLICATION_JSON);
    }



}
