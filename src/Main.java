import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(List.of(
                new Person("An", 15, "Male"),
                new Person("Bình", 25, "Male"),
                new Person("Chi", 17, "Female"),
                new Person("Dương", 61, "Other"),
                new Person("Hà", 30, "Female"),
                new Person("Hưng", 70, "Male"),
                new Person("Lan", 45, "Female"),
                new Person("Minh", 10, "Other"),
                new Person("Ngọc", 20, "Female"),
                new Person("Phúc", 80, "Male")
        ));

        Controller personController = new Controller(persons);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            personController.showMenu();
            String input = scanner.nextLine();
            switch (input){
                case "0" -> {
                    System.out.println("Exit.");
                    return;
                }
                case "1" -> personController.classifyAge();
                case "2" -> personController.averageAge();
                case "3" -> personController.filterAge();
                case "4" -> personController.countByGender();
                case "5" -> personController.addPerson();
                case "6" -> personController.sortByAge();
                case "7" -> personController.searchPerson();
                case "8" -> personController.filterByGender();
                default -> System.out.println("Invalid choice. Please again.");
            }
        }

    }
}
