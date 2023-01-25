import java.util.Scanner;
class lab1{
      
        public static void main(String[] args) {  
            /* 
             *  Java Program that will collect your basic details that fall into different data types and displays them.
             */
        String Name;
        int Age;
        String Class;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name, Age,Class :");
        Name = scan.nextLine();
        Age = Integer.parseInt(scan.nextLine());
        Class = scan.nextLine();
        System.out.println("Name: "+Name+"\nAge: "+Age+"/nClass: "+ Class);
      
        String gender;
        
        System.out.println("What is your gender, m or f?");
        gender = scan.nextLine();

        if( gender.equals("f"))
        {
            System.out.println("FEMALE" );
        }
        else if( gender.equals("m"));
        {
            System.out.println("MALE");
        }
      }
    }