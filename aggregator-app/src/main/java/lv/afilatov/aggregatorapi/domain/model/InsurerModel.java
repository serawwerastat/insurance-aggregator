package lv.afilatov.aggregatorapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lv.afilatov.aggregatorapi.domain.enums.InsurerConnectionType;

@Entity
@Table(name = "insurers")
public class InsurerModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "insurer_url")
    private String insuranceURL;
    @Column(name = "premium_path")
    private String premiumPath;
    @Column(name = "insurer_type")
    @Enumerated(EnumType.STRING)
    private InsurerConnectionType insurerConnectionType;
    @Column(name = "is_active")
    private boolean isActive = false;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InsurerModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getInsuranceURL() {
        return insuranceURL;
    }

    public InsurerModel setInsuranceURL(String insuranceURL) {
        this.insuranceURL = insuranceURL;
        return this;
    }

    public String getPremiumPath() {
        return premiumPath;
    }

    public InsurerModel setPremiumPath(String premiumPath) {
        this.premiumPath = premiumPath;
        return this;
    }

    public InsurerConnectionType getInsurerType() {
        return insurerConnectionType;
    }

    public InsurerModel setInsurerType(InsurerConnectionType insurerConnectionType) {
        this.insurerConnectionType = insurerConnectionType;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public InsurerModel setActive(boolean active) {
        isActive = active;
        return this;
    }

}
