package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static String mainMenu = "Write action (buy, fill, take, remaining, exit):";
    private static String message = "";
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
    }

    public static String next() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static Integer nextInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        boolean status;
        do {
            status = coffeeMachine.process();
        } while (status);
    }

    //fill
    public void fill(int fillWater, int fillMilk, int fillBeans, int fillCups) {
        this.water += fillWater;
        this.milk += fillMilk;
        this.beans += fillBeans;
        this.cups += fillCups;
    }

    public void doFill() {
        println("Write how many ml of water do you want to add: ");
        int water = nextInt();
        println("Write how many ml of milk do you want to add: ");
        int milk = nextInt();
        println("Write how many grams of coffee beans do you want to add:");
        int beans = nextInt();
        println("Write how many disposable cups of coffee do you want to add:");
        int cups = nextInt();
        this.fill(water, milk, beans, cups);
    }

    //remaining
    public void remaining() {
        message = "The coffee machine has:\n";
        message += this.water + " of water\n"
                + this.milk + " of milk\n"
                + this.beans + " of coffee beans\n"
                + this.cups + " of disposable cups\n"
                + "$" + this.money + " of money";
        println(message);
    }

    //take
    public void take() {
        message = "I gave you" + " " + "$" + this.money + "\n";
        this.money = 0;
        println(message);
    }

    //buy
    public void buy() {
        int water = 0;
        int milk = 0;
        int beans = 0;
        int money = 0;
        //CoffeeOptions coffeeSelection;
        String buyOption = next();
        CoffeeOptions coffeeSelection = CoffeeOptions.findByValue(buyOption);
        switch (coffeeSelection) {
            case ESPRESSO:
                water = 250;
                milk = 0;
                beans = 16;
                money = 4;
                break;
            case LATTE:
                water = 350;
                milk = 75;
                beans = 20;
                money = 7;
                break;
            case CAPPUCCINO:
                water = 200;
                milk = 100;
                beans = 12;
                money = 6;
                break;
            case BACK:
                return;
            default:
                break;
        }
        if (this.water < water) {
            println("Sorry, not enough water!");
        } else if (this.milk < milk) {
            println("Sorry, not enough milk!");
        } else if (this.beans < beans) {
            println("Sorry, not enough beans!");
        } else if (this.cups < cups) {
            println("Sorry, not enough cups!");
        } else {
            println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.cups--;
            this.money += money;
        }
    }

    public void buyCoffee() {
        String menu = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ";
        println(menu);
        buy();
    }

    public boolean process() {
        String input;
        Operations operations;
        println(mainMenu);
        input = next();
        operations = Operations.valueOf(input.toUpperCase());
        switch (operations) {
            case BUY:
                buyCoffee();
                break;
            case FILL:
                doFill();
                break;
            case TAKE:
                take();
                break;
            case REMAINING:
                remaining();
                break;
            case EXIT:
                return false;
            default:
                break;
        }
        return true;
    }

    public enum Operations {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT
    }

    public enum CoffeeOptions {
        ESPRESSO("1"),
        LATTE("2"),
        CAPPUCCINO("3"),
        BACK("back");

        String option;

        CoffeeOptions(String option) {
            this.option = option;
        }

        public static CoffeeOptions findByValue(String option) {
            for (CoffeeOptions value : values()) {
                if (option.equals(value.option)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(option+" invalid selection");
        }
    }

}
