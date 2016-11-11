import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Jim
 */
public class Jaroop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String command = "mainMenu";
        boolean running = true;
        boolean deposit = false;
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String transactionAmount = "";
        
        while (running) {
            switch (command) {
                case "mainMenu":
                    System.out.println("Please enter in a command (Deposit, Withdraw, Balance, Exit) :");
                    userInput = scanner.next().toLowerCase();
                    deposit = false;
                    switch (userInput)
                    {
                        case "deposit": 
                        case "withdraw":
                        case "balance":
                            command = userInput;
                            break;
						case "exit":
							running = false;
							break;
                        default:
                            break;
                    }
                    break;
                case "deposit":
                    deposit = true;
                case "withdraw":
                    System.out.println("Please enter an amount to "+userInput+":");
                    transactionAmount = scanner.next().toLowerCase();
                     if(checkTransactionAmount(transactionAmount) == true) {
                         logTransaction(transactionAmount, deposit);
                         command = "mainMenu";
                     }
                     else
                     {
                         command = userInput;
                     }
                     
                    break;
                case "balance":
                    getBalance(); 
                    command = "mainMenu";
                    break;
                default:
                    command = "mainMenu";
                    break;
            }
        }
        
    }
    
    /**
     * Checks whether the user inputed transaction amount is valid
     * @param input
     * @return false for an input that is negative or has more than 2 decimal 
     * places otherwise returns true.
     */
    public static boolean checkTransactionAmount(String input) {
        boolean validInput = true;
        if (input.length() - input.indexOf(".") != 3 || input.startsWith("-") || (input.indexOf(".") < 0)) {
            validInput = false;
        }
        return validInput;
    }
    
    /**
     * Parses the file named log.html in the same directory as the executable 
     * and sums up all the transactions.
     * @throws IOException
     */
    public static void getBalance() throws IOException {
        Document doc = Jsoup.parse(new File("log.html"),"utf-8");//assuming register.html file in e drive 
        Element ele;
        Elements eles;
        double balance = 0;
        String transAmount = "";  
        ele = doc.getElementById("transactions");
        eles = ele.getElementsByTag("td");
        for (Element transaction : eles) {
            transAmount = transaction.ownText();
            balance += Double.parseDouble(transAmount);
        }
        System.out.println("The current balance is: $"+balance);
    }
    
    /**
     * Appends the transaction to table transactions in the file log.html
     * @param amount is the amount to be deposited for withdrawed.
     * @param deposit tells the transaction processor to deposit or withdraw the amount.
     * @throws IOException
     */
    public static void logTransaction(String amount, boolean deposit) throws IOException {
        String transAmount;
        File transactionLog = new File("log.html");
        if (deposit)
        {
            transAmount = amount;
        }
        else
        {
            transAmount = "-" + amount;
        }
        Document doc = Jsoup.parse(transactionLog,"utf-8");
        doc.getElementById("transactions").select("tbody").append("<tr><td>"+transAmount+"</td></tr>");
        BufferedWriter bw = new BufferedWriter(new FileWriter(transactionLog));
        if(transactionLog.canWrite())
        {
            bw.write(doc.toString());
            bw.close(); 
        }
    }
}
