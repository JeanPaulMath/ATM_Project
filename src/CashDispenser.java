public class CashDispenser {
    private final static int BILLS_AVAIALBLE = 500;
    private int count;

    public CashDispenser(){
        count = BILLS_AVAIALBLE;
    }

    public void dispenseCash(int amount){
        int billsNeeded = amount / 20;
        count -= billsNeeded;
    }

    public boolean sufficientCashAvailable(int amount){
        int billsNeeded = amount / 20;

        if (count >=  billsNeeded) {
            return true;
        }
        else {
            return false;
        }

    }
}
