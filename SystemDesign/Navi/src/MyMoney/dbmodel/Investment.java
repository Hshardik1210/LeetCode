package MyMoney.dbmodel;

public interface Investment {
    public Integer getSipAmount();

    public void setSipAmount(Integer sipAmount);

    public Integer getAmount();

    public void setAmount(Integer amount);

    public float getProportion();

    public void setProportion(float proportion);
}
