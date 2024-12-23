package Elo_Elo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SeleniumWithApiRequest {

    public static void main(String[] args) {
        // Define the curl command string
        String curlCommand = "curl --location 'https://app.fireflink.com/project/optimize/v1/scripts/modules/MOD1002/script?newScript=true' " +
            "--header 'Accept: application/json' " +
            "--header 'Accept-Language: en-GB,en;q=0.9' " +
            "--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJNODJhX1U2VHRFSnFvWV9jdzNfWi1KaWVoZ2hQcjJ4QjVMbnlNZ3RtRGs0In0.eyJleHAiOjE3NDA1NDMyODMsImlhdCI6MTcyNDk5MTI4MywianRpIjoiMGI1OGJjZDItN2NiZi00YTlhLTk2MmItYmFmZDVmYzQ0OWVhIiwiaXNzIjoiaHR0cHM6Ly9hcHAuZmlyZWZsaW5rLmNvbTozMTAwMS9yZWFsbXMvRmlyZUZsaW5rIiwic3ViIjoiZjphZjhmZmUyYS1jMTIyLTQ3ZjAtOTIxYS01ODgyODcwMjVjZmM6bmV3dGVzdGluZzUwMDVAZ21haWwuY29tIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZmxpbmstc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiJmMTFjMmEzMy1lY2ZmLTRmYjYtOGUzOC0yZTNkOGZhNzliZDUiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6ImYxMWMyYTMzLWVjZmYtNGZiNi04ZTM4LTJlM2Q4ZmE3OWJkNSIsImN1cnJlbnRMaWNlbnNlSWQiOiJMSUMyMTE2MCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY3VycmVudFByaXZpbGVnZSI6IlN1cGVyIEFkbWluIiwiZnVsbE5hbWUiOiJOZXcgVGVzdCA1MDA1IiwiYWN0aXZhdGlvblN0YXR1cyI6IkFDVElWRSIsInByaXZpbGVnZSI6IlN1cGVyIEFkbWluIiwibGljZW5zZU5hbWUiOiJmaXJlLWZsaW5rLUxJQzIxMTYwIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiTmV3IFRlc3QgNTAwNSIsInVzZXJOYW1lIjoibmV3dGVzdGluZzUwMDVAZ21haWwuY29tIiwiYmlsbGluZ0N5Y2xlIjoiTi9BIiwiaWQiOiJVU1IxMjY5OTEiLCJsaWNlbnNlSWQiOiJMSUMyMTE2MCIsImVtYWlsIjoibmV3dGVzdGluZzUwMDVAZ21haWwuY29tIn0.Kme864ll0Ow4vm5qkdo4HRypghBeJWg7w3L9zW6BZab6MjMgHrYBqnqtrhO7NubRv98dzUAKXQ1s5Mo-9kFNxcTAHascj00j4nOVqkArerCUNR0PnAhkh4WP7av4n98sWfriTTIgNnKZ-eMKuEiRdwXTjJA0kmgcB5bP5BT_jawVfTM2MMkdnbNFJ_MzlN84iWjQad-CRg9yig4No6gLUvA2oaYFoigOMvctycSOBbpMaaqwvejoXe3YJEIH47rD2IQ_FVXDM033mA7SsqaJy8KvTHOa0-kxQMckzUINJN1facdF2zrDZjVktf864JhB3TaQ8rqphq_ZQ6YbR15MbA' " +
            "--header 'Connection: keep-alive' " +
            "--header 'Content-Type: application/json' " +
            "--header 'Cookie: _ga=GA1.1.2065465369.1724991259; _ga_MYGNRDJ84W=GS1.1.1724991259.1.1.1724991286.0.0.0' " +
            "--header 'Origin: https://app.fireflink.com' " +
            "--header 'Referer: https://app.fireflink.com/testdevelopment/Script' " +
            "--header 'Sec-Fetch-Dest: empty' " +
            "--header 'Sec-Fetch-Mode: cors' " +
            "--header 'Sec-Fetch-Site: same-origin' " +
            "--header 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36' " +
            "--header 'projectId: PJT1001' " +
            "--header 'projectType: Web & Mobile' " +
            "--header 'sec-ch-ua: \"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"' " +
            "--header 'sec-ch-ua-mobile: ?0' " +
            "--header 'sec-ch-ua-platform: \"Windows\"' " +
            "--header 'timeZone: Atlantic/Azores' " +
            "--data '{\"name\":\"Test 6\",\"scriptType\":\"Web & Mobile (Android or iOS)\",\"desc\":\"\",\"parentId\":\"MOD1002\",\"parentName\":\"Test\",\"executionOrder\":6,\"hierarchy\":2,\"testCaseType\":\"Automation\",\"projectLabels\":[],\"type\":\"Script\"}'";

        // Extract URL, headers, and data from the curl command string
        String url = extractUrl(curlCommand);
        Map<String, String> headers = extractHeaders(curlCommand);
        String data = extractData(curlCommand);

        // Perform API request
        try {
            // Create a connection
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");

            // Set headers
            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }

            // Enable input/output streams
            conn.setDoOutput(true);

            // Write JSON input to the output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Print the response
                System.out.println("API Response: " + response.toString());
            }

            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Extract URL from the curl command
    private static String extractUrl(String curlCommand) {
        return curlCommand.split(" --location '")[1].split("'")[0];
    }

    // Extract headers from the curl command
    private static Map<String, String> extractHeaders(String curlCommand) {
        Map<String, String> headers = new HashMap<>();
        String[] parts = curlCommand.split("--header '");
        for (int i = 1; i < parts.length; i++) {
            String header = parts[i].split("'")[0];
            String[] headerParts = header.split(": ");
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }
        return headers;
    }

    // Extract data from the curl command
    private static String extractData(String curlCommand) {
        String[] parts = curlCommand.split("--data '");
        if (parts.length > 1) {
            return parts[1].split("'")[0];
        }
        return "";
    }
}