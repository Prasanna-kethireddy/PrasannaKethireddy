package com.myvisaapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisaApplicationProcessingSystem {
    public static void main(String[] args) {
        // Creating a thread pool with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        VisaApplicationManager manager = new VisaApplicationManager();
        InputScanner inputScanner = new InputScanner();

        while (true) {
            System.out.println("Visa Processing System");
            System.out.println("1. Submit new visa application");
            System.out.println("2. Exit");

            int choice = inputScanner.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    String name = inputScanner.readString("Enter applicant name: ");
                    String visaType = inputScanner.readVisaType();
                    VisaApplication application = new VisaApplication(name, visaType);
                    manager.addApplication(application);
                    System.out.println("Application submitted.");

                    // Submitting visa application processing task to the executor
                    executor.submit(() -> {
                        processVisaApplication(manager, application);
                    });
                    break;
                case 2:
                    System.out.println("Exiting the program.");
                    executor.shutdown(); // Shut down the executor service
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void processVisaApplication(VisaApplicationManager manager, VisaApplication application) {
        // Processing logic goes here
        // For demonstration, let's update the status of the application after some delay
        try {
            Thread.sleep(2000); // Simulating processing time
            synchronized (manager) {
                manager.updateApplicationStatus(application.getApplicantName(), "Processed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        }
    }
}
