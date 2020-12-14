package lv.afilatov.aggregatorapi.domain.response;

public class PremiumResponse {

    private String name;
    private Double premium;

    public PremiumResponse() {
    }

    public PremiumResponse(String name, Double premium) {
        this.name = name;
        this.premium = premium;
    }

    public String getName() {
        return name;
    }

    public PremiumResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPremium() {
        return premium;
    }

    public PremiumResponse setPremium(Double premium) {
        this.premium = premium;
        return this;
    }
}
