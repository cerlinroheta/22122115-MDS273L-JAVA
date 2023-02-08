import java.util.Scanner;

public class menuDriven {
  private static final int MAX_NAMES = 1024;
  private static String[] names = new String[MAX_NAMES];
  private static int numNames = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int option;

    do {
      System.out.println("--- Menu ---");
      System.out.println("1. Enter a name");
      System.out.println("2. Search for a name");
      System.out.println("3. Remove a name");
      System.out.println("4. Show all names");
      System.out.println("5. Exit");
      System.out.print("Enter your option: ");

      option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          enterName(sc);
          break;
        case 2:
          searchName(sc);
          break;
        case 3:
          removeName(sc);
          break;
        case 4:
          showNames();
          break;
        case 5:
          System.out.println("Exiting program...");
          break;
        default:
          System.out.println("Invalid option, try again");
      }
    } while (option != 5);

    sc.close();
  }

  private static void enterName(Scanner sc) {
    System.out.print("Enter a name: ");
    String name = sc.nextLine();

    if (name.isEmpty() || numNames == MAX_NAMES) {
      System.out.println("Invalid name or maximum limit reached");
      return;
    }

    for (int i = 0; i < numNames; i++) {
      if (names[i].equalsIgnoreCase(name)) {
        System.out.println("Name already exists");
        return;
      }
    }

    names[numNames++] = name;
    System.out.println("Name added successfully");
  }

  private static void searchName(Scanner sc) {
    System.out.print("Enter a name to search: ");
    String search = sc.nextLine();

    boolean found = false;
    for (int i = 0; i < numNames; i++) {
      if (names[i].equalsIgnoreCase(search)) {
        System.out.println("Name found at index " + i);
        found = true;
        break;
      }
    }

    if (!found) {
      System.out.println("Name not found");
    }
  }

  private static void removeName(Scanner sc) {
    System.out.print("Enter a name to remove: ");
    String remove = sc.nextLine();

    int index = -1;
    for (int i = 0; i < numNames; i++) {
      if (names[i].equalsIgnoreCase(remove)) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("Name not found");
    } else {
        for (int i = index; i < numNames - 1; i++) {
        names[i] = names[i + 1];
        }
        numNames--;
        System.out.println("Name removed successfully");
        }
        }
        
        private static void showNames() {
        if (numNames == 0) {
        System.out.println("No names to show");
        return;
        }
        System.out.println("--- Names ---");
for (int i = 0; i < numNames; i++) {
  System.out.println((i + 1) + ". " + names[i]);
}
        }
    }