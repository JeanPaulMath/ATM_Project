public class Deposit extends Transaction{
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int DEPOSIT_CANCELLED = 0;

    public Deposit(int accountNumber, Screen screen, BankDB bankDB, Keypad atmKeypad, DepositSlot atmDepositSlot) {
        super(accountNumber, screen, bankDB);

        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void executeTransaction() {
        BankDB bankDB = getBankDB();
        Screen screen = getScreen();

        double amount = askForDepositAmount();

        if (amount != DEPOSIT_CANCELLED) {
            screen.messageToUser("\n Insert your deposit envelope of ");
            screen.displayAcctBalance(amount);
            screen.messageToUserLine(".");

            boolean envelopeRecieved = depositSlot.isEnvelopedRecieved();

            if (envelopeRecieved){
                screen.messageToUserLine("\nYour deposit has been received.\nNOTE: your transaction will not be shown until it is processed. ");

                bankDB.creditBalance(getAccountNumber(), amount);
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

        if (input == DEPOSIT_CANCELLED){
            return DEPOSIT_CANCELLED;
        }
        else{
            return (double) input/100;
        }

    }

}
