package org.example;

import java.util.Map;

public record ReportData(String title, String author, Map<String, String> content) {
}
