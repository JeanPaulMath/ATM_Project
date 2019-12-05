public class BalanceInquiry extends Transaction{

    public BalanceInquiry(int accountNumber, Screen screen, BankDB bankDB) {
        super(accountNumber, screen, bankDB);
    }

    @Override
    public void execute() {
        BankDB bankDB = getBankDB();
        Screen screen = getScreen();

        double totalBalance = bankDB.getTotalBalance(getAccountNumber());
        double availableBalance = bankDB.getAvailableBalance(getAccountNumber());

        screen.messageToUserLine("\nBalance Info: ");
        screen.messageToUser("- Available Balance: ");
        screen.displayAcctBalance(availableBalance);
        screen.messageToUser("\n-Total Balance:    ");
        screen.displayAcctBalance(totalBalance);
        screen.messageToUserLine("");
    }

}
