import java.util.Scanner;

public class PersonalFinanceManager {
    static String[] operations = new String[100];
    static double[] amounts = new double[100];
    static String[] descriptions = new String[100];
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Додати операцію (дохід/витрата)");
            System.out.println("2. Переглянути баланс");
            System.out.println("3. Переглянути історію транзакцій");
            System.out.println("4. Оновити запис");
            System.out.println("5. Видалити запис");
            System.out.println("6. Вийти");
            System.out.print("Виберіть дію: ");
            int choice = getIntInput(scanner);

            scanner.nextLine();

            switch (choice) {
                case 1:
                    addOperation(scanner);
                    break;
                case 2:
                    viewBalance();
                    break;
                case 3:
                    viewHistory();
                    break;
                case 4:
                    updateOperation(scanner);
                    break;
                case 5:
                    deleteOperation(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте знову.");
            }
        }
        scanner.close();
    }

    public static void addOperation(Scanner scanner) {
        if (count >= operations.length) {
            System.out.println("Досягнуто максимальну кількість операцій.");
            return;
        }

        System.out.print("Введіть тип операції (дохід/витрата): ");
        String operation = scanner.nextLine().trim().toLowerCase();
        while (!operation.equals("дохід") && !operation.equals("витрата")) {
            System.out.print("Невірний тип операції. Введіть 'дохід' або 'витрата': ");
            operation = scanner.nextLine().trim().toLowerCase();
        }

        System.out.print("Введіть суму: ");
        double amount = getDoubleInput(scanner);

        System.out.print("Введіть опис: ");
        String description = scanner.nextLine().trim();

        operations[count] = operation;
        amounts[count] = amount;
        descriptions[count] = description;
        count++;

        System.out.println("Операція додана.");
    }

    public static void viewBalance() {
        double balance = 0;
        for (int i = 0; i < count; i++) {
            if (operations[i].equalsIgnoreCase("дохід")) {
                balance += amounts[i];
            } else if (operations[i].equalsIgnoreCase("витрата")) {
                balance -= amounts[i];
            }
        }
        System.out.println("Поточний баланс: " + balance);
    }

    public static void viewHistory() {
        System.out.println("Історія транзакцій:");
        if (count == 0) {
            System.out.println("Немає транзакцій.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + operations[i] + ": " + amounts[i] + " - " + descriptions[i]);
            }
        }
    }

    public static void updateOperation(Scanner scanner) {
        System.out.print("Введіть номер операції для оновлення: ");
        int index = getIntInput(scanner) - 1;

        if (index >= 0 && index < count) {
            System.out.print("Введіть новий тип операції (дохід/витрата): ");
            String operation = scanner.nextLine().trim().toLowerCase();
            while (!operation.equals("дохід") && !operation.equals("витрата")) {
                System.out.print("Невірний тип операції. Введіть 'дохід' або 'витрата': ");
                operation = scanner.nextLine().trim().toLowerCase();
            }

            System.out.print("Введіть нову суму: ");
            double amount = getDoubleInput(scanner);

            System.out.print("Введіть новий опис: ");
            String description = scanner.nextLine().trim();

            operations[index] = operation;
            amounts[index] = amount;
            descriptions[index] = description;

            System.out.println("Операцію оновлено.");
        } else {
            System.out.println("Невірний номер операції.");
        }
    }

    public static void deleteOperation(Scanner scanner) {
        System.out.print("Введіть номер операції для видалення: ");
        int index = getIntInput(scanner) - 1;

        if (index >= 0 && index < count) {
            for (int i = index; i < count - 1; i++) {
                operations[i] = operations[i + 1];
                amounts[i] = amounts[i + 1];
                descriptions[i] = descriptions[i + 1];
            }
            count--;
            System.out.println("Операцію видалено.");
        } else {
            System.out.println("Невірний номер операції.");
        }
    }

    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Невірний ввід. Введіть ціле число: ");
            }
        }
    }

    public static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Невірний ввід. Введіть число: ");
            }
        }
    }
}
