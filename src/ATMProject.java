public class ATMProject {
    public static void main(String[] args) {
        ATM project = new ATM();
        project.runATM();
        System.out.println("ATM Machine Current state : " + project.getAtmState().getClass().getName());

        System.out.println();
        project.enterPinAndWithdrawMoney();
        project.ejectDebitCard();
        project.insertDebitCard();

        System.out.println("\n*******************************************************");

        System.out.println("\nATM Machine Current state : "
                + project.getAtmState().getClass().getName());
        System.out.println();

        project.enterPinAndWithdrawMoney();
        project.insertDebitCard();
        project.ejectDebitCard();
        System.out.println("\n*******************************************************");

        System.out.println("\nATM Machine Current state : "
                + project.getAtmState().getClass().getName());


    }
}
