
/**
 * This program helps users split a bill either evenly among all people or based on individual orders, including optional service charges and taxes.
 *
 * @ANGEL TAN KE QIN
 * @Latest version on 6/21/2025
 */

import java.util.Scanner;
public class SplitBill
{
    public static void main(String [] args)
    {
        Scanner cs = new Scanner(System.in);
        //Ask user input type to split bill
        System.out.println("How do you want to split the bill?\n1. Split evenly\n2. Split by individual orders\nEnter your choice (1 or 2):");
        int choice = cs.nextInt();
        
        if ( choice == 1)
        {
            splitEvenly();
        }
        else if (choice == 2)
        {
            splitByIndividualOrder();
        }
        else 
        {
            System.out.println("Please enter a valid choice!");
        }
    }

    public static void splitEvenly()//Method split evenly
    {
        Scanner cs = new Scanner(System.in);
        System.out.println("Total amount: RM");
        double totalAmount = cs.nextDouble();
        System.out.println("Number of people:");
        int numberOfPeople = cs.nextInt();
        //Calculate bill separated
        double bill = totalAmount / numberOfPeople;
        //Display output statement
        System.out.println("\n--- Final Bill Summary ---");
        System.out.println(String.format("Each person has to pay RM %.2f",bill));
    }
    
    public static void splitByIndividualOrder()//Method split by individual order
    {
        Scanner cs = new Scanner(System.in);
        //Ask user input if total amount include extra charge
        System.out.println("Is there any extra charge?\n1. Yes\n2. No\nEnter your choice (1 or 2):");
        int choice2=cs.nextInt();
        System.out.println("Number of people:");
        int numberOfPeople = cs.nextInt();
        if (numberOfPeople <= 0)//Avoid input invalid number of people
        {
            System.out.println("Invalid number of people.");
        }
        //Create arrays to store bill of each person, extra charge of each person, name of each person
        double [] billEach = new double [numberOfPeople];
        double [] extra = new double [numberOfPeople];
        String [] name = new String [numberOfPeople];
        cs.nextLine();
        
        for (int i=0;i<numberOfPeople;i++)//Create loop to input array name
        {
            System.out.println("Enter name person "+(i+1)+":");
            name [i] = cs.nextLine();
        }
        for (int i=0;i<numberOfPeople;i++)//Create loop to input array bill
        {
            System.out.println("For "+name[i]+"...");
            billEach[i]=calcBill(name,i);
        }
        double totalBillOri = calcTotalBillOri(billEach,numberOfPeople);//Call method calculate total bill of each person
        double totalAmount=0;//Initialise total amount
        if (choice2==1)//When extra charge available
        {
            //Input total amount
            System.out.println("Enter total bill amount (including tax and service charge):");
            totalAmount=cs.nextDouble();
            if (totalAmount<=totalBillOri)//Avoid output negative amount
            {
                System.out.println("You enter incorrect price or amount. Please check again!");
            }
            else
            {
                //Calculate
                double totalExtra = totalAmount - totalBillOri;
                double totalExtraCalculated=0;
                
                for (int i=0; i<numberOfPeople;i++)//Create loop to store extra charge in array extra
                {
                   extra [i]= totalExtra*(billEach[i]/totalBillOri);
                   totalExtraCalculated= totalExtraCalculated+extra[i];//Calculate total extra
                }
                
                //OutputStatement
                System.out.println("\n--- Final Bill Summary ---");
                for(int i=0;i<numberOfPeople;i++)
                {
                    System.out.println("For "+name[i]);
                    System.out.println(String.format("Total bill: RM %.2f\nTotal extra charge: RM %.2f\nTotal amount need to pay: RM %.2f",billEach[i],extra[i],(billEach[i]+extra[i])));
                }
            }
        }
        else if (choice2==2)
        {
            System.out.println("\n--- Final Bill Summary ---");
            for(int i=0; i<numberOfPeople;i++)
            {
                System.out.println("For "+name[i]);
                System.out.println("Total amount need to pay: RM "+ billEach[i]);
            }
        }
        else
        {
            System.out.println("Please enter a valid choice!");
        }
    }
    
    public static double calcTotalBillOri(double [] bill,int num)//Method to calculate total bill in array bill
    {
        double total=0;
        for(int i=0;i<num;i++)
        {
            total=total+bill[i];
        }
        return total;
    }
    
    public static double calcBill (String name [],int i)//Method to calculate bill of each person
    {
        Scanner cs = new Scanner(System.in);
        double bill=0;//Initialise bill
        System.out.println("Enter quantity order for "+name[i]+":");
        int quantity = cs.nextInt();//Input quantity order
        for (int c=0;c<quantity;c++)//Create loop to input price of every quantity order
        {
            System.out.println("Enter price for order "+(c+1)+":");
            bill = bill + cs.nextDouble();
        }
        return bill;
    }
}