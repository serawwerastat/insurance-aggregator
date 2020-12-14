package lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerPostJson;

import lv.afilatov.aggregatorapi.domain.request.UserDataRequest;

class InsurancePostJsonRequest {

    private String technicalPassportNr;
    private int age;
    private int experience;

    public static InsurancePostJsonRequest from(UserDataRequest userDataRequest) {
        return new InsurancePostJsonRequest()
                .setTechnicalPassportNr(userDataRequest.getTechnicalPassportNumber())
                .setAge(userDataRequest.getAge())
                .setExperience(userDataRequest.getDrivingExperience());
    }

    public String getTechnicalPassportNr() {
        return technicalPassportNr;
    }

    public InsurancePostJsonRequest setTechnicalPassportNr(String technicalPassportNr) {
        this.technicalPassportNr = technicalPassportNr;
        return this;
    }

    public int getAge() {
        return age;
    }

    public InsurancePostJsonRequest setAge(int age) {
        this.age = age;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public InsurancePostJsonRequest setExperience(int experience) {
        this.experience = experience;
        return this;
    }
}
