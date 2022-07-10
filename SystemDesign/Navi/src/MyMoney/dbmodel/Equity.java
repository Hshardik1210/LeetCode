package MyMoney.dbmodel;

public class Equity implements Investment {

    Integer amount;
    float proportion;
    Integer sipAmount;

    public Equity() {
        this.amount = 0;
        this.proportion = 0;
        this.sipAmount = 0;
    }

    public Integer getSipAmount() {
        return sipAmount;
    }

    public void setSipAmount(Integer sipAmount) {
        this.sipAmount = sipAmount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }
}
