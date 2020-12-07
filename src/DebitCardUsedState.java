public class DebitCardUsedState implements AtmStatePattern{
    @Override
    public void insertDebitCard()
    {
        System.out.println("Debit Card is already there,So you cannot insert the Debit Card.");

    }

    @Override
    public void ejectDebitCard()
    {

        System.out.println("Debit Card has been ejected.");
    }

    @Override
    public void enterPinAndWithdrawMoney()
    {
        System.out.println("Pin number has been accepted, dispensing cash now.");

    }
}
