package MyMoney.service;

import MyMoney.dbmodel.Debt;
import MyMoney.dbmodel.Equity;
import MyMoney.dbmodel.Gold;
import MyMoney.dbmodel.Investment;
import MyMoney.Application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    Investment gold;
    Investment equity;
    Investment debt;
    String allocationMonth;
    String sipStartMonth;
    boolean isSip;
    Map<String, Map<String, Integer>> investmentTrack;

    Portfolio() {
        investmentTrack = new HashMap<>();
        gold = new Gold();
        equity = new Equity();
        debt = new Debt();
        allocationMonth = "JANUARY";
        sipStartMonth = "FEBRUARY";
    }

    public boolean isAllocated() {
        return investmentTrack.containsKey(allocationMonth);
    }

    public void allocateInvestment(String currentMonth, Integer equityAmount, Integer debtAmount, Integer goldAmount) {
        try {
            if (!Application.validateMonth(currentMonth))
                throw new IOException("No Such Month");
            if (investmentTrack.containsKey(allocationMonth))
                throw new IOException("Already Allocated");
            if (!currentMonth.equals(allocationMonth))
                throw new IOException("Can Only start investing in " + allocationMonth);
            gold.setAmount(goldAmount);
            equity.setAmount(equityAmount);
            debt.setAmount(debtAmount);
            setProportion();
            addTracking(allocationMonth);
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public void _updateBalances(Investment I, float change) {
        I.setAmount((int) (I.getAmount() * (1 + (1.0 * change) / 100)));
    }

    public void _addSips(Investment I) {
        I.setAmount(I.getAmount() + I.getSipAmount());
    }

    public void addTracking(String month) {
        try {
            if (!Application.validateMonth(month))
                throw new IOException("No Such Month");

            Map<String, Integer> track = new HashMap<>();
            track.put("EQUITY", equity.getAmount());
            track.put("GOLD", gold.getAmount());
            track.put("DEBT", debt.getAmount());
            investmentTrack.put(month, track);
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public void setProportion() {
        Integer Total = gold.getAmount() + equity.getAmount() + debt.getAmount();
        gold.setProportion(gold.getAmount() * 100 / Total);
        equity.setProportion(equity.getAmount() * 100 / Total);
        debt.setProportion(debt.getAmount() * 100 / Total);
    }

    public Integer getTotal() {
        Integer Total = gold.getAmount() + equity.getAmount() + debt.getAmount();
        return Total;
    }

    public void updateBalance(float equityChange, float debtChange, float goldChange, String month) {
        if (month != allocationMonth && isSip) {
            addSip(month, equity.getSipAmount(), debt.getSipAmount(), gold.getSipAmount(), true);
        }
        _updateBalances(gold, goldChange);
        _updateBalances(equity, equityChange);
        _updateBalances(debt, debtChange);
        addTracking(month);
    }

    public void reBalance() {
        try {
            if (investmentTrack.size() < 6)
                throw new IOException("CANNOT_REBALANCE");
            Integer total = getTotal();
            gold.setAmount((int) (gold.getProportion() * total / 100));
            equity.setAmount((int) (equity.getProportion() * total / 100));
            debt.setAmount((int) (debt.getProportion() * total / 100));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showBalance() {
        System.out.println(equity.getAmount() + " " + debt.getAmount() + " " + gold.getAmount());
    }

    public void showBalanceInGivenMonth(String month) {
        if (!Application.validateMonth(month)) {
            System.out.println("No Such Month");
            return;
        }
        if (investmentTrack.containsKey(month)) {
            System.out.println(investmentTrack.get(month).get("EQUITY") + " " + investmentTrack.get(month).get("DEBT") + " " + investmentTrack.get(month).get("GOLD"));
        } else {
            System.out.println("No Info.Investment Found");
        }
    }

    public void addSip(String month, Integer equitySip, Integer debtSip, Integer goldSip, boolean marketChange) {
        try {
            if (!Application.validateMonth(month))
                throw new IOException("No Such Month");
            if (!isAllocated())
                throw new IOException("No investment done yet");
            if (equitySip < 0 || debtSip < 0 || goldSip < 0)
                throw new IOException("Can't have negative value in SIP");
            if (investmentTrack.containsKey(sipStartMonth) || month.equals(sipStartMonth)) {
                isSip = true;
                gold.setSipAmount(goldSip);
                equity.setSipAmount(equitySip);
                debt.setSipAmount(debtSip);
                if (marketChange) {
                    _addSips(gold);
                    _addSips(equity);
                    _addSips(debt);
                    addTracking(month);
                }
            } else {
                throw new IOException("Can only start SIP in month" + sipStartMonth);
            }
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
