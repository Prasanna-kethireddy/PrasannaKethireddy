package com.myvisaapplication;

public class Main {
    public static void main(String[] args) {
        VisaApplicationManager manager = new VisaApplicationManager();
        InputScanner inputScanner = new InputScanner();
        FileHandler FileHandler = new FileHandler();

        while (true) {
            System.out.println("Visa Processing System");
            System.out.println("1. Submit new visa application");
            System.out.println("2. Process visa applications");
            System.out.println("3. Update application status");
            System.out.println("4. View applications by status");
            System.out.println("5. View top 10 visa types");
            System.out.println("6. Exit");

            int choice = inputScanner.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    String name = inputScanner.readString("Enter applicant name: ");
                    String visaType = inputScanner.readVisaType();
                    VisaApplication application = new VisaApplication(name, visaType);
                    manager.addApplication(application);
                    System.out.println("Application submitted.");
                    break;
                case 2:
                    // Processing logic would go here
                    break;
                case 3:
                    String applicantName = inputScanner.readString("Enter applicant name: ");
                    String status = inputScanner.readString("Enter new status: ");
                    try {
                        manager.updateApplicationStatus(applicantName, status);
                        System.out.println("Application status updated.");
                    } catch (ApplicationNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    String statusFilter = inputScanner.readString("Enter status to filter by: ");
                    manager.getApplicationsByStatus(statusFilter).forEach(System.out::println);
                    break;
                case 5:
                    manager.getTopVisaTypes(10).forEach(entry ->
                            System.out.println(entry.getKey() + ": " + entry.getValue()));
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
