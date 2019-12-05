public class Deposit extends Transaction{
    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELLED = 0;

    public Deposit(int accountNumber, Screen screen, BankDB bankDB, Keypad atmKeypad, DepositSlot atmDepositSlot) {
        super(accountNumber, screen, bankDB);

        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute() {
        BankDB bankDB = getBankDB();
        Screen screen = getScreen();

        amount = askForDepositAmount();

        if (amount != CANCELLED) {
            screen.messageToUser("\n Insert your deposit envelope of ");
            screen.displayAcctBalance(amount);
            screen.messageToUserLine(".");

            boolean envelopRecieved = depositSlot.isenvelopedRecieved();

            if (envelopRecieved){
                screen.messageToUserLine("\nYour deposit has been received.\nNOTE: your transaction will not be shown until it is processed. ");

                bankDB.creditBalance(getAccountNumber(),amount);
            }
            else {
                screen.messageToUserLine("Deposit cancelled. The envelope was not inserted.");
            }

        }
        else{
            screen.messageToUserLine("\nTransaction Cancelled...");
        }

    }

    private double askForDepositAmount(){
        Screen screen = getScreen();

        screen.messageToUser("\nPlease enter a deposit amount in CENTS (or 0 to cancel): ");
        int input = keypad.getKeypadInput();

        if (input == CANCELLED){
            return CANCELLED;
        }
        else{
            return (double) input/100;
        }

    }

}
