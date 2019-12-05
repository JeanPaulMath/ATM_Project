import java.util.Scanner;

public class Keypad {
    private Scanner atmKeys;

    public Keypad(){
        atmKeys = new Scanner(System.in);
    }

    public int getKeypadInput(){
        return atmKeys.nextInt();
    }
}
