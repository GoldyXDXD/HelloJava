import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class NN {
    public static void main(String[] args) throws IOException {
        //код, благодаря которому логи не показываются в консоли
        Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http", "groovyx.net.http"));
        Set<String> artifactoryLoggers = new HashSet<>(Arrays.asList("org.apache.http", "groovyx.net.http"));
        for(String log:artifactoryLoggers) {
            ch.qos.logback.classic.Logger artLogger = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(log);
            artLogger.setLevel(ch.qos.logback.classic.Level.INFO);
            artLogger.setAdditive(false);
        }
        
        Scanner scanner = new Scanner(System.in);
        
        //получение доступа к бд
        String url = "jdbc:mysql://localhost/core?serverTimezone=Europe/Moscow&useSSL=false";
        System.out.println("Введите имя пользователя БД:");
        String user = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        SQLHelper.checkConnection(url, user, password);
        System.out.println("WARNING: MOST OF THE FUNCTIONS WORK ONLY WITH WITH RUB");
        boolean  flag = true;
        //меню приложения
        while(flag) {
            System.out.println("Select action: see short report(1), see DB table(2), add revenue(3), add expense(4), expenses stats(5), revenue stats(6), search(7), task manager(8) - will work later, analytics(9), show USD rate(10), credit calculator(11), readMe(12), exit(13)");
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    SQLHelper.shortReport(url, user, password);
                    break;
                case 2:
                    SQLHelper.fullReport(url,user,password);
                    break;
                case 3:
                    SQLHelper.addRevenue(url, user, password, scanner);
                    break;
                case 4:
                    SQLHelper.addExpense(url, user, password, scanner);
                    break;
                case 5:
                    SQLHelper.expenseStats(url, user, password);
                    break;
                case 6:
                    SQLHelper.revenueStats(url, user, password);
                    break;
                case 7:
                    SQLHelper.search(url, user, password, scanner);
                    break;
                case 8:
                    SQLHelper.tasksManager(url, user, password, scanner);
                    break;
                case 9:
                    SQLHelper.analytics(url, user, password);
                    break;
                case 10:
                    System.out.println("Enter action: choose currency(1), see supported currencies(2): ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter currency: ");
                            String currency = Support.enter(scanner);
                            LiveResponseDemo.sendLiveRequest(currency);
                            LiveResponseDemo.httpClient.close();
                            new BufferedReader(new InputStreamReader(System.in)).readLine();
                            break;
                        case 2:
                            CurrenciesTable.showCurrenciesTable();
                            break;
                    }
                    break;
                case 11:
                    CreditCalculator.annuityCalculator(scanner);
                    break;
                case 12:
                    SQLHelper.readMe();
                    break;
                case 13:
                    System.exit(0);
            }
        }
    }
}
