import java.util.Objects;

public class BankDB {
    private Account[] accounts;

    public BankDB(){
        accounts = new Account[2];
        accounts[0] = new Account(12345,54321,1000.0,1200.0);
        accounts[1] = new Account(98765,56789,200.0,200.0);

    }

    private Account getAccount(int accountNumber){
        for (Account currentAccount : accounts) {
            //return current account if match
            if (currentAccount.getAccountNumber() == accountNumber){
                return currentAccount;
            }
        }
        return null; //for when no match is found
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN){
        Account userAccount = getAccount(userAccountNumber);

        if (userAccount != null){
            return userAccount.validatePIN(userPIN);
        }
        else {
            return false;
        }
    }

    public double getAvailableBalance(int userAccountNumber){
        return Objects.requireNonNull(getAccount(userAccountNumber)).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber){
        return Objects.requireNonNull(getAccount(userAccountNumber)).getTotalBalance();
    }

    public void creditBalance(int userAccountNumber, double amount){
        Objects.requireNonNull(getAccount(userAccountNumber)).creditBalance(amount);
    }

    public void debitBalance(int userAccountNumber, double amount){
        Objects.requireNonNull(getAccount(userAccountNumber)).debitBalance(amount);
    }

}
