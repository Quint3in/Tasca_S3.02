package org.example;

public interface ReportStrategy {
    String generate(ReportData reportData);

    static ReportStrategy html() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("<html><body>");
            builder.append("<h1>").append(reportData.title()).append("</h1>");
            builder.append("<p><strong>Author:</strong> ").append(reportData.author()).append("</p>");
            builder.append("<ul>");
            reportData.content().forEach((key, value) ->
                    builder.append("<li><strong>")
                            .append(key)
                            .append(":</strong> ")
                            .append(value)
                            .append("</li>")
            );
            builder.append("</ul>");
            builder.append("</body></html>");
            return builder.toString();
        };
    }

    static ReportStrategy json() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("{\n");
            builder.append("  \"title\": \"").append(reportData.title()).append("\",\n");
            builder.append("  \"author\": \"").append(reportData.author()).append("\",\n");
            builder.append("  \"content\": {\n");

            int index = 0;
            int size = reportData.content().size();
            for (var entry : reportData.content().entrySet()) {
                builder.append("    \"")
                        .append(entry.getKey())
                        .append("\": \"")
                        .append(entry.getValue())
                        .append("\"");
                if (++index < size) {
                    builder.append(",");
                }
                builder.append("\n");
            }

            builder.append("  }\n");
            builder.append("}");
            return builder.toString();
        };
    }

    static ReportStrategy xml() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("<report>");
            builder.append("<title>").append(reportData.title()).append("</title>");
            builder.append("<author>").append(reportData.author()).append("</author>");
            builder.append("<content>");
            reportData.content().forEach((key, value) ->
                    builder.append("<field name=\"")
                            .append(key)
                            .append("\">")
                            .append(value)
                            .append("</field>")
            );
            builder.append("</content>");
            builder.append("</report>");
            return builder.toString();
        };
    }

    static ReportStrategy pdf() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("PDF REPORT\n");
            builder.append("Title: ").append(reportData.title()).append("\n");
            builder.append("Author: ").append(reportData.author()).append("\n");
            reportData.content().forEach((key, value) ->
                    builder.append("- ").append(key).append(": ").append(value).append("\n")
            );
            return builder.toString();
        };
    }

    static ReportStrategy csv() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("title,author,field,value\n");
            reportData.content().forEach((key, value) ->
                    builder.append(reportData.title()).append(",")
                            .append(reportData.author()).append(",")
                            .append(key).append(",")
                            .append(value).append("\n")
            );
            return builder.toString();
        };
    }

    static ReportStrategy excel() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("EXCEL SHEET\n");
            builder.append("Title\t").append(reportData.title()).append("\n");
            builder.append("Author\t").append(reportData.author()).append("\n");
            builder.append("Field\tValue\n");
            reportData.content().forEach((key, value) ->
                    builder.append(key).append("\t").append(value).append("\n")
            );
            return builder.toString();
        };
    }

    static ReportStrategy word() {
        return reportData -> {
            StringBuilder builder = new StringBuilder();
            builder.append("WORD DOCUMENT\n");
            builder.append("# ").append(reportData.title()).append("\n\n");
            builder.append("Author: ").append(reportData.author()).append("\n\n");
            reportData.content().forEach((key, value) ->
                    builder.append(key).append(": ").append(value).append("\n")
            );
            return builder.toString();
        };
    }
}
