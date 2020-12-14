package lv.afilatov.aggregatorapi.domain.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;

public class InsurerRequest {

    private String name;
    private String insuranceURL;
    private String premiumPath;
    @Enumerated(EnumType.STRING)
    private InsurerConnectionType insurerConnectionType;

    public String getName() {
        return name;
    }

    public InsurerRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getInsuranceURL() {
        return insuranceURL;
    }

    public InsurerRequest setInsuranceURL(String insuranceURL) {
        this.insuranceURL = insuranceURL;
        return this;
    }

    public String getPremiumPath() {
        return premiumPath;
    }

    public InsurerRequest setPremiumPath(String premiumPath) {
        this.premiumPath = premiumPath;
        return this;
    }

    public InsurerConnectionType getInsurerType() {
        return insurerConnectionType;
    }

    public InsurerRequest setInsurerType(InsurerConnectionType insurerConnectionType) {
        this.insurerConnectionType = insurerConnectionType;
        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        InsurerRequest that = (InsurerRequest) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(insuranceURL, that.insuranceURL)
                .append(premiumPath, that.premiumPath)
                .append(insurerConnectionType, that.insurerConnectionType)
                .isEquals();
    }

    @Override public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(insuranceURL)
                .append(premiumPath)
                .append(insurerConnectionType)
                .toHashCode();
    }

    @Override public String toString() {
        return "InsurerRequest{" +
                "name='" + name + '\'' +
                ", insuranceURL='" + insuranceURL + '\'' +
                ", premiumPath='" + premiumPath + '\'' +
                ", insurerType=" + insurerConnectionType +
                '}';
    }
}
