import java.util.Arrays;
import java.util.Scanner;

public class Lab3 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = getArray(sc);
        
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.printf("Mean: %.2f\n", computeMean(arr));
                    break;
                case 2:
                    System.out.printf("Median: %.2f\n", computeMedian(arr));
                    break;
                case 3:
                    System.out.printf("Mode: %d\n", computeMode(arr));
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    public static int[] getArray(Scanner sc) {
        System.out.print("Enter array size: ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.printf("Enter element %d: ", i + 1);
            arr[i] = sc.nextInt();
        }
        return arr;
    }
    
    public static void showMenu() {
        System.out.println("1. Compute Mean");
        System.out.println("2. Compute Median");
        System.out.println("3. Compute Mode");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public static double computeMean(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
    
    public static double computeMedian(int[] arr) {
        Arrays.sort(arr);
        int mid = arr.length / 2;
        if (arr.length % 2 == 0) {
            return (double) (arr[mid - 1] + arr[mid]) / 2;
        } else {
            return (double) arr[mid];
        }
    }
    
    public static int computeMode(int[] arr) {
        int mode = arr[0];
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                mode = arr[i];
                maxCount = count;
            }
        }
        return mode;
    }
}