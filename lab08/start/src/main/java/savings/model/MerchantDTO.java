package savings.model;

import java.math.BigDecimal;

public class MerchantDTO {

    public MerchantDTO(Merchant merchant) {
        this.name = merchant.getName();
        this.number = merchant.getNumber();
        this.payback = merchant.getPayback().asBigDecimal();
    }

    private String number;

    private String name;

    private BigDecimal payback;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPayback() {
        return payback;
    }

    public void setPayback(BigDecimal payback) {
        this.payback = payback;
    }
}
