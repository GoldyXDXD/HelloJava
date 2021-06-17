import java.util.Scanner;

public class Support {
    //позволяет с первого раза ввести нужные данные
    public static String enter(Scanner scanner) {
        String desc = "";
        while (desc == null || desc.equals("") == true) {
            desc = scanner.nextLine();
        }
        return desc;
    }
}
