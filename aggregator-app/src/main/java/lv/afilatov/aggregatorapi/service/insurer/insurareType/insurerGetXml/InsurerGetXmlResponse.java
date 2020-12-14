package lv.afilatov.aggregatorapi.service.insurer.insurareType.insurerGetXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Quote")
@XmlAccessorType(XmlAccessType.FIELD)
class InsurerGetXmlResponse {

    @XmlElement
    private Double premium;

    public InsurerGetXmlResponse() {
    }

    public Double getPremium() {
        return premium;
    }

    public InsurerGetXmlResponse setPremium(Double premium) {
        this.premium = premium;
        return this;
    }
}
