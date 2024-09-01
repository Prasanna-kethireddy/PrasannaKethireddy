package com.myvisaapplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VisaApplicationManager {
    private List<VisaApplication> applications;
    private Map<String, Employee> employees;

    public VisaApplicationManager() {
        applications = new ArrayList<>();
        employees = new HashMap<>();
    }

    public void addApplication(VisaApplication application) {
        applications.add(application);
    }

    public void removeApplication(String applicantName) throws ApplicationNotFoundException {
        boolean removed = applications.removeIf(app -> app.getApplicantName().equalsIgnoreCase(applicantName));
        if (!removed) {
            throw new ApplicationNotFoundException("Application not found for applicant: " + applicantName);
        }
    }

    public void updateApplicationStatus(String applicantName, String newStatus) throws ApplicationNotFoundException {
        for (VisaApplication application : applications) {
            if (application.getApplicantName().equalsIgnoreCase(applicantName)) {
                application.setStatus(newStatus);
                application.setProcessingDate(LocalDateTime.now());
                return;
            }
        }
        throw new ApplicationNotFoundException("Application not found for applicant: " + applicantName);
    }

    public List<VisaApplication> getApplications() {
        return applications;
    }

    public List<VisaApplication> getApplicationsByStatus(String status) {
        return applications.stream()
                .filter(app -> app.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Map<String, Long> countVisaTypes() {
        return applications.stream()
                .collect(Collectors.groupingBy(VisaApplication::getVisaType, Collectors.counting()));
    }

    public List<Map.Entry<String, Long>> getTopVisaTypes(int limit) {
        return countVisaTypes().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
