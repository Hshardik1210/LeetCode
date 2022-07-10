package MyMoney.service;

import MyMoney.Application;

public class User {
    static int id = 0;
    Integer userId;
    Portfolio portfolio;

    {
        id++;
    }

    public User() {
        userId = Integer.valueOf(id);
        portfolio = new Portfolio();
        Application.addUser(this);
    }

    public void allocate(String month, Integer equityAmount, Integer debtAmount, Integer goldAmount) {
        portfolio.allocateInvestment(month, equityAmount, debtAmount, goldAmount);
    }

    public void addSip(String month, Integer equitySip, Integer debtSip, Integer goldSip) {
        portfolio.addSip(month, equitySip, debtSip, goldSip, false);
    }

    public void showBalanceForMonth(String month) {
        portfolio.showBalanceInGivenMonth(month);
    }

    public void updateBalance(float equityChange, float debtChange, float goldChange, String month) {
        portfolio.updateBalance(equityChange, debtChange, goldChange, month);
    }

    public void reBalance() {
        portfolio.reBalance();
    }

    public void showBalance() {
        portfolio.showBalance();
    }
}
