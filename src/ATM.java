public class ATM {
    private  boolean userAuthentication;
    private int currentAcctNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser dispenser;
    private DepositSlot depositSlot;
    private BankDB bankDB;

    private static final int BALANCE_AMOUNT = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    public ATM(){
        userAuthentication = false;
        currentAcctNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        dispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDB = new BankDB();
    }

    public void runATM(){

        while(true){

            while(!userAuthentication){
                screen.messageToUserLine("\nWelcome");
                authenticateUser();
            }
            performTransactions();
            userAuthentication = false;
            currentAcctNumber = 0;
            screen.messageToUserLine("\n Thank You! Goodbye!");

        }
    }

    private void authenticateUser(){
        screen.messageToUser("Please enter a valid account number: ");
        int acctNumber = keypad.getKeypadInput();
        screen.messageToUser("Please enter your PIN Number: ");
        int pin = keypad.getKeypadInput();

        userAuthentication = bankDB.authenticateUser(acctNumber, pin);

        if (userAuthentication){
            currentAcctNumber = acctNumber;
        }
        else{
            screen.messageToUserLine("The account number or PIN you entered is incorrect.");
        }
    }

    private void performTransactions(){

        boolean userExited = false;

        while (!userExited){
            int mainMenuChoice = displayMainMenu();

            switch (mainMenuChoice){
                case BALANCE_AMOUNT:
                case WITHDRAWAL:
                case DEPOSIT:
                    createTransaction(mainMenuChoice).executeTransaction();
                    break;
                case EXIT:
                    screen.messageToUserLine("\nExiting the System...");
                    userExited = true;
                    break;
                default:
                    screen.messageToUserLine("Invalid Option");
                    break;
            }
        }
    }

    private int displayMainMenu(){
        screen.messageToUserLine("\n Main Menu: ");
        screen.messageToUserLine("1 - View Balance");
        screen.messageToUserLine("2 - Withdraw Cash");
        screen.messageToUserLine("3 - Deposit Funds");
        screen.messageToUserLine("4 - Exit\n");
        screen.messageToUserLine("Please pick an option: ");
        return keypad.getKeypadInput();
    }

    private Transaction createTransaction(int type){
        Transaction temp = null;

        switch(type){
            case BALANCE_AMOUNT:
                temp = new BalanceInquiry(currentAcctNumber, screen, bankDB);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAcctNumber, screen, bankDB, keypad, dispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAcctNumber, screen, bankDB, keypad, depositSlot);
                break;
        }
        return temp;

    }

}
