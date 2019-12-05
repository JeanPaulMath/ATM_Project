public class Screen {

    public void messageToUser(String message){
        System.out.println(message);
    }

    public void messageToUserLine(String message){
        System.out.println(message);
    }

    public void displayAcctBalance(double amount){
        System.out.printf("$%,.2f",amount);
    }

}
