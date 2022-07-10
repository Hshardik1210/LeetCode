package MyMoney;

import MyMoney.service.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Application {
    static Map<String, Integer> months = new HashMap<>();
    static ArrayList<User> users = new ArrayList<>();

    static {
        months.put("JANUARY", Integer.valueOf(1));
        months.put("FEBRUARY", Integer.valueOf(2));
        months.put("MARCH", Integer.valueOf(3));
        months.put("APRIL", Integer.valueOf(4));
        months.put("MAY", Integer.valueOf(5));
        months.put("JUNE", Integer.valueOf(6));
        months.put("JULY", Integer.valueOf(7));
        months.put("AUGUST", Integer.valueOf(8));
        months.put("SEPTEMBER", Integer.valueOf(9));
        months.put("OCTOBER", Integer.valueOf(10));
        months.put("NOVEMBER", Integer.valueOf(11));
        months.put("DECEMBER", Integer.valueOf(12));
    }

    public static void marketChange(float equityChange, float debtChange, float goldChange, String month) {
        for (User user : users) {
            user.updateBalance(equityChange, debtChange, goldChange, month);
        }
    }

    public static void reBalance() {
        for (User user : users) {
            user.reBalance();
        }
    }

    public static void addUser(User u) {
        users.add(u);
    }

    public static boolean validateMonth(String monthName) {
        return months.containsKey(monthName);
    }

    public static void sampleTest1() {
        System.out.println("\n");
        User u1 = new User();
        u1.allocate("JANUARY", 8000, 6000, 3500);
        u1.addSip("FEBRUARY", 3000, 2000, 1000);
        marketChange(11.0f, 9.0f, 4.0f, "JANUARY");
        marketChange(-6.00f, 21.00f, -3.00f, "FEBRUARY");
        marketChange(12.50f, 18.00f, 12.50f, "MARCH");
        marketChange(23.00f, -3.00f, 7.00f, "APRIL");
        u1.showBalanceForMonth("MARCH");
        u1.showBalanceForMonth("APRIL");
        reBalance();
    }

    public static void sampleTest2() {
        System.out.println("\n");
        User u1 = new User();
        u1.allocate("JANUARY", 6000, 3000, 1000);
        u1.addSip("FEBRUARY", 2000, 1000, 500);
        marketChange(4.0f, 10.0f, 2.0f, "JANUARY");
        marketChange(-10.00f, 40.00f, 0.00f, "FEBRUARY");
        marketChange(12.50f, 12.50f, 12.50f, "MARCH");
        marketChange(8.00f, -3.00f, 7.00f, "APRIL");
        marketChange(13.00f, 21.00f, 10.50f, "MAY");
        marketChange(10.00f, 8.00f, -5.00f, "JUNE");
        u1.showBalanceForMonth("MARCH");
        reBalance();
        u1.showBalance();
    }

    public static void main(String[] args) {

        sampleTest1();
        sampleTest2();
        return;
    }
}