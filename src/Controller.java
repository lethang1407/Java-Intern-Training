import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    List<Person> persons = new ArrayList<>();

    public Controller(List<Person> persons) {
        this.persons = persons;
    }

    // Hien thi menu chuong trinh
    public void showMenu() {
        System.out.println("\n===== Manage Persons =====");
        System.out.println("0. Thoát");
        System.out.println("1. Phân loại theo nhóm tuổi");
        System.out.println("2. Tính tuổi trung bình");
        System.out.println("3. Tuổi lớn nhất và nhỏ nhất");
        System.out.println("4. Đếm số người theo giới tính");
        System.out.println("5. Thêm Person");
        System.out.println("6. Sắp xếp theo tuổi");
        System.out.println("7. Tìm Person theo tên");
        System.out.println("8. Duyệt theo gender");
    }

    // Phan loai theo nhom tuoi
    public void classifyAge() {
        System.out.println("=== Phân loại theo nhóm tuổi ===");
        persons.forEach(p -> {
            int age = p.getAge();
            String group;
            if (age < 18) {
                group = "Trẻ em";
            } else if (age <= 60) {
                group = "Người lớn";
            } else {
                group = "Người cao tuổi";
            }
            System.out.println(p.getName() + " (" + age + " tuổi): " + group);
        });
    }

    // Tinh tuoi trung binh
    public void averageAge() {
        System.out.println("=== Tuổi trung bình ===");
        double avgAge = persons.stream().mapToInt(Person::getAge).average().orElse(0);
//        OptionalDouble avgAge = persons.stream().mapToInt(p -> p.getAge()).average();
        System.out.printf("Tuổi trung bình: %.2f%n", avgAge);
    }

    // Tuoi lon nhat va nho nhat
    public void filterAge() {
        System.out.println("=== Tuổi lớn nhất và nhỏ nhất ===");
        persons.stream().max(Comparator.comparingInt(Person::getAge)).ifPresent(p -> System.out.printf("Người lớn tuổi nhất: %s (%d tuổi)%n", p.getName(), p.getAge()));
        persons.stream().min(Comparator.comparingInt(Person::getAge)).ifPresent(p -> System.out.printf("Người nhỏ tuổi nhất: %s (%d tuổi)%n", p.getName(), p.getAge()));
    }

    // Dem so nguoi theo gioi tinh
    public void countByGender() {
        Map<String, Long> genderCount = persons.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
        genderCount.forEach((gender, count) -> System.out.printf("%s: %d%n", gender, count));
    }

    // Them Person
    public void addPerson() {
        System.out.println("=== Thêm người mới ===");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên, tuổi, giới tính: ");
        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        String gender = scanner.nextLine();

        if (age > 0 && age < 120) {
            Person newPerson = new Person(name, age, gender);
            persons.add(newPerson);
            System.out.println("Thêm thành công: " + newPerson);
        } else {
            System.out.println("Không hợp lệ. Thêm thất bại.");
        }
    }

    // Sap xep theo tuoi
    public void sortByAge() {
        System.out.println("=== Danh sách sắp xếp theo tuổi ===");
        persons.stream().sorted(Comparator.comparingInt(Person::getAge)).forEach(System.out::println);
    }

    // Tim nguoi theo ten
    public void searchPerson() {
        System.out.println("=== Nhập tên để tìm kiếm ===");
        Scanner scanner = new Scanner(System.in);
        String searchName = scanner.nextLine();
        persons.stream().filter(p -> p.getName().equalsIgnoreCase(searchName)).forEach(p -> System.out.println("Tìm thấy: " + p));
    }

    // Duyet theo Gender
    public void filterByGender() {
        System.out.println("\nChào theo giới tính:");
        persons.forEach(p -> {
            String message = switch (p.getGender()) {
                case "Male" -> "Hey boy!";
                case "Female" -> "Hey girl!";
                default -> "Hey man!";
            };
            System.out.printf("%s: %s%n", p.getName(), message);
        });
    }
}
