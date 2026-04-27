package org.example;

public class ReportGeneratorService {
    private ReportStrategy reportStrategy;

    public ReportGeneratorService(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public String generateReport(ReportData reportData) {
        return reportStrategy.generate(reportData);
    }
}
