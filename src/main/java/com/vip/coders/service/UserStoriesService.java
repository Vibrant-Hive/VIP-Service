package com.vip.coders.service;

import com.vip.coders.entity.UserStories;
import com.vip.coders.repository.UserStoriesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserStoriesService<Issue> {
    @Autowired
    private UserStoriesRepository userStoriesRepository;


    private static final String ISSUE_STATE_OPEN = "open";

    private final RestTemplate restTemplate;
    private String githubUrl = "https://api.github.com";
    private String githubToken = "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";

    public UserStoriesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }
    public JSONArray fetchEvents(String eventsUrl) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(eventsUrl, String.class);
        String response = responseEntity.getBody();

        return new JSONArray(response);
    }

    public boolean getRepoIssues(Integer learnerId, String repoName) throws Exception {
        String apiUrl = githubUrl + "/repos/abdularsin/" + repoName + "/issues?state=all";
        String response = callAPI(apiUrl);
        JSONArray json = new JSONArray(response);

        json.forEach(item -> {
            UserStories userStories = new UserStories();
            JSONObject jsonObject = (JSONObject) item;
            userStories.setStoryNumber(jsonObject.getInt("number"));
            userStories.setStoryName(jsonObject.getString("title"));
            userStories.setStoryStatus(jsonObject.getString("state"));
            userStories.setLearnerId(learnerId);
            userStories.setRepositoryName(repoName);

            LocalDateTime createdAt = LocalDateTime.parse(jsonObject.getString("created_at").replace("Z", ""));
            Instant instant = createdAt.toInstant(ZoneOffset.UTC);
            Date createdDate = Date.from(instant);
            userStories.setStoryCreatedDate(createdDate);

            if (!jsonObject.isNull("closed_at")) {
                LocalDateTime closedAt = LocalDateTime.parse(jsonObject.getString("closed_at").replace("Z", ""));
                instant = closedAt.toInstant(ZoneOffset.UTC);
                Date closedDate = Date.from(instant);
                userStories.setStoryCompletedDate(closedDate);
            }

            // Fetch events for the current issue
            int issueNumber = jsonObject.getInt("number");
            String eventsUrl = jsonObject.getString("events_url");
            JSONArray eventsJsonArray = fetchEvents(eventsUrl);

            // Find the last 'assigned' event for the current issue
            JSONObject lastAssignedEvent = findLastAssignedEvent(eventsJsonArray, "assigned");

            // Find the last 'closed' event for the current issue
            JSONObject lastClosedEvent = findLastAssignedEvent(eventsJsonArray, "closed");

            // Extract assignee and assigned date if 'assigned' event exists
            if (lastAssignedEvent != null) {
                JSONObject assigneeObject = lastAssignedEvent.getJSONObject("assignee");
                String assignee = assigneeObject.getString("login");
                String assignedDateStr = lastAssignedEvent.getString("created_at");
                LocalDateTime assignedDate = LocalDateTime.parse(assignedDateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

                // Update userStories object with assignee and assigned date
                userStories.setAssignee(assignee);
                userStories.setStoryAssignedDate(Date.from(assignedDate.toInstant(ZoneOffset.UTC)));
            }

            // Extract completed date if 'closed' event exists
            if (lastClosedEvent != null) {
                String closedDateStr = lastClosedEvent.getString("created_at");
                LocalDateTime closedDate = LocalDateTime.parse(closedDateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

                // Update userStories object with completed date
                userStories.setStoryCompletedDate(Date.from(closedDate.toInstant(ZoneOffset.UTC)));
            }

            // Save the userStories object to the User Stories table
            userStoriesRepository.save(userStories);
        });
        return true;
    }
    private JSONObject findLastAssignedEvent(JSONArray eventsJsonArray, String assigned) {
        for (int i = eventsJsonArray.length() - 1; i >= 0; i--) {
            JSONObject eventObject = eventsJsonArray.getJSONObject(i);
            String eventType = eventObject.getString("event");
            if ("assigned".equals(eventType)) {
                return eventObject; // Found the last 'assigned' event
            }
        }
        return null; // No 'assigned' event found
    }

    public String callAPI(String url) throws Exception {
        // Create a URL object with the API endpoint
        URL apiUrl = new URL(url);

        // Create a connection object
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method (GET, POST, etc.)
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is successful (HTTP 200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Create a BufferedReader to read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            // Read the response line by line
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Return the response as a string
            return response.toString();
        } else {
            // Handle the error case, if needed
            throw new Exception("API request failed with response code: " + responseCode);
        }
    }


}

















