import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int [] ints = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        System.out.println(sum);
        System.out.println("Input your age below:");
        Scanner s = new Scanner(System.in);
        int age = s.nextInt();
        if (age >= 18) {
            System.out.println("You are old enough to vote!");
        }
        if (age >= 30) {
            System.out.println("You are also old enough to run for senate");
        }
        if (age > 80) {
            System.out.println("You are part of the greatest generation");
        } else if (age > 60) {
            System.out.println("You are part of the baby boomers.");
        } else if (age > 40) {
            System.out.println("You are in generation X.");
        } else if (age > 20) {
            System.out.println("You are a millennial.");
        } else {
            System.out.println("You are an iKid.");
        }
    }
}