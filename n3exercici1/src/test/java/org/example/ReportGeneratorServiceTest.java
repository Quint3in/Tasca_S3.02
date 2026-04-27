package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ReportGeneratorServiceTest {

    private ReportData reportData;

    @BeforeEach
    void setUp() {
        Map<String, String> content = new LinkedHashMap<>();
        content.put("Module", "S3.02 Patterns");
        content.put("Student", "Joel");
        content.put("Status", "Submitted");
        reportData = new ReportData("Project Delivery Report", "Joel", content);
    }

    @Test
    void serviceShouldGenerateHtmlReportWithInjectedStrategy() {
        ReportGeneratorService generatorService = new ReportGeneratorService(ReportStrategy.html());

        String report = generatorService.generateReport(reportData);

        assertThat(report)
                .contains("<html><body>")
                .contains("<h1>Project Delivery Report</h1>")
                .contains("<strong>Author:</strong> Joel");
    }

    @Test
    void serviceShouldSwitchStrategiesAtRuntimeWithoutChangingServiceCode() {
        ReportGeneratorService generatorService = new ReportGeneratorService(ReportStrategy.html());

        String htmlReport = generatorService.generateReport(reportData);

        generatorService.setReportStrategy(ReportStrategy.json());
        String jsonReport = generatorService.generateReport(reportData);

        assertThat(htmlReport).contains("<html><body>");
        assertThat(jsonReport)
                .contains("\"title\": \"Project Delivery Report\"")
                .contains("\"author\": \"Joel\"")
                .doesNotContain("<html><body>");
    }

    @Test
    void serviceShouldAcceptAnyOtherStrategyImplementationSuchAsCsv() {
        ReportGeneratorService generatorService = new ReportGeneratorService(ReportStrategy.csv());

        String report = generatorService.generateReport(reportData);

        assertThat(report)
                .startsWith("title,author,field,value")
                .contains("Project Delivery Report,Joel,Module,S3.02 Patterns")
                .contains("Project Delivery Report,Joel,Student,Joel");
    }
}
