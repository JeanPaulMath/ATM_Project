public class NoDebitCardState implements AtmStatePattern {
    @Override
    public void insertDebitCard()
    {
        System.out.println("Processing Debit Card.");

    }

    @Override
    public void ejectDebitCard()
    {

        System.out.println("No Debit Card has been received. Card can not be ejected.");
    }

    @Override
    public void enterPinAndWithdrawMoney()
    {
        System.out.println("No Debit Card has been received. Withdrawal has been cancelled.");

    }
}
