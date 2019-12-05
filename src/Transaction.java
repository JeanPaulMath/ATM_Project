public  abstract class Transaction {
    private int accountNumber;
    private Screen screen;
    private BankDB bankDB;

    public Transaction(int accountNumber, Screen screen, BankDB bankDB) {
        this.accountNumber = accountNumber;
        this.screen = screen;
        this.bankDB = bankDB;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    public BankDB getBankDB() {
        return bankDB;
    }

    abstract public void execute();
}
