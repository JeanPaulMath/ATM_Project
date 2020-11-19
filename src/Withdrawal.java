public class Withdrawal extends Transaction {
    private int amount;
    private Keypad keypad;
    private CashDispenser dispenser;

    private final static int WITHDRAWAL_CANCELLED = 6;

    public Withdrawal(int accountNumber, Screen screen, BankDB bankDB, Keypad atmKeypad, CashDispenser atmDispenser) {
        super(accountNumber, screen, bankDB);

        keypad = atmKeypad;
        dispenser = atmDispenser;
    }


    @Override
    public void executeTransaction() {
        boolean cashDispensed = false;
        double availableBalance;

        BankDB bankDB = getBankDB();
        Screen screen = getScreen();

        do {
            amount = displayMenuOfAmounts();

            if (amount != WITHDRAWAL_CANCELLED){
                availableBalance = bankDB.getAvailableBalance(getAccountNumber());

                if (amount<= availableBalance){

                    if (dispenser.sufficientCashAvailable(amount)){
                        bankDB.debitBalance(getAccountNumber(),amount);

                        dispenser.dispenseCash(amount);
                        cashDispensed = true;
                        screen.messageToUserLine("\nYour cash is being dispensed.");
                    }
                    else {
                        screen.messageToUserLine("\nInsufficient cash available in the ATM. Please choose a smaller amount.");
                    }

                }
                else {
                    screen.messageToUserLine("Your account has insufficient funds. Choose a smaller amount.");
                }

            }
            else{
                screen.messageToUserLine("\nTransaction Cancelled.");
                return;
            }
        } while (!cashDispensed);
    }

    private int displayMenuOfAmounts(){
        int userChoice = 0;

        Screen screen = getScreen();
        int[] amounts = {0,20,40,60,100,200};

        while (userChoice == 0){
            screen.messageToUserLine("\n Withdrawal Options: ");
            screen.messageToUserLine("1 - $20");
            screen.messageToUserLine("2 - $40");
            screen.messageToUserLine("3 - $60");
            screen.messageToUserLine("4 - $100");
            screen.messageToUserLine("5 - $200");
            screen.messageToUserLine("6 - Cancel Withdrawal");
            screen.messageToUserLine("\n Choose a Withdrawal amount: ");

            int input = keypad.getKeypadInput();

            switch (input){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case WITHDRAWAL_CANCELLED:
                    userChoice = WITHDRAWAL_CANCELLED;
                    break;
                default:
                    screen.messageToUserLine("Invalid choice. Try Again.");
            }
        }
        return userChoice;

    }


}
