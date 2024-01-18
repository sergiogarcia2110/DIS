package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ObservationController {

    private static String projectRoot = System.getProperty("user.dir");
    private Gson gson = new Gson();

    @GetMapping("/getMsCodes")
    public List<String> getMsCodes() {
        Map<String, List<Observation>> observationsMap = getAllObservations();
        return observationsMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    @PostMapping("/getObservationsByMsCode")
    public List<Observation> getObservationsByMsCode(@RequestBody Map<String, String> msCodeMap) {
        String msCode = msCodeMap.get("msCode");
        Map<String, List<Observation>> observationsMap = getAllObservations();
        return observationsMap.getOrDefault(msCode, List.of());
    }

    private Map<String, List<Observation>> getAllObservations() {
        String jsonFilePathIn = projectRoot + "/demo/src/main/java/com/example/demo/MsCode_json.json";
        try {
            String json = Files.readString(Path.of(jsonFilePathIn));
            Type type = new TypeToken<Map<String, List<Observation>>>(){}.getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of();
        }
    }
}
