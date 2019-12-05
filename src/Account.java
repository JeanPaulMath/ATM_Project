public class Account {
    private int accountNumber;
    private int pinNumber;
    private double availableBalance;
    private double totalBalance;

    public Account(int acctNum, int pin, double availBal, double totalBal){
        accountNumber = acctNum;
        pinNumber = pin;
        availableBalance = availBal;
        totalBalance = totalBal;
    }

    public boolean validatePIN(int enteredPIN){
        if (enteredPIN == pinNumber){
            return true;
        }
        else {
            return false;
        }
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void creditBalance(double amount){
        totalBalance += amount;
    }

    public void debitBalance(double amount){
        availableBalance -= amount;
        totalBalance -= amount;
    }

}
