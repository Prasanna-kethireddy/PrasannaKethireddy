package com.myvisaapplication;

import java.time.LocalDateTime;

public class VisaApplication {
    private String applicantName;
    private String visaType;
    private String status;
    private LocalDateTime submissionDate;
    private LocalDateTime processingDate;

    public VisaApplication(String applicantName, String visaType) {
        this.applicantName = applicantName;
        this.visaType = visaType;
        this.status = "Submitted";
        this.submissionDate = LocalDateTime.now();
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
    }

    @Override
    public String toString() {
        return "VisaApplication{" +
                "applicantName='" + applicantName + '\'' +
                ", visaType='" + visaType + '\'' +
                ", status='" + status + '\'' +
                ", submissionDate=" + submissionDate +
                ", processingDate=" + processingDate +
                '}';
    }

    public void setSubmissionDate(LocalDateTime submissionDate2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSubmissionDate'");
    }
}
