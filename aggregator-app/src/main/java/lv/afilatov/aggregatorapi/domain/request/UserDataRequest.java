package lv.afilatov.aggregatorapi.domain.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class UserDataRequest {

    private String technicalPassportNumber;
    private LocalDate dayOfBirth;
    private int age;
    private int drivingExperience;

    public UserDataRequest() {
    }

    public String getTechnicalPassportNumber() {
        return technicalPassportNumber;
    }

    public UserDataRequest setTechnicalPassportNumber(String technicalPassportNumber) {
        this.technicalPassportNumber = technicalPassportNumber;
        return this;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public String fetchFormattedDayOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dayOfBirth.format(formatter);
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public UserDataRequest setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
        this.age = (int) ChronoUnit.YEARS.between(this.dayOfBirth, LocalDate.now());
        return this;
    }

    public int getAge() {
        return age;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public UserDataRequest setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
        return this;
    }

}
