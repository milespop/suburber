package com.nurturecloud;

import static com.nurturecloud.model.Status.DONE;
import static com.nurturecloud.model.Status.EMPTY;
import static com.nurturecloud.util.Numerics.isValidInt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurturecloud.model.Location;
import com.nurturecloud.model.LocationGrouping;
import com.nurturecloud.model.Status;
import com.nurturecloud.model.LocalityPostcodeTuple;
import com.nurturecloud.util.Haversine;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {
        // Thread pool
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // Jackson mapper to help with conversion from the input file
        ObjectMapper mapper = new ObjectMapper();

        Map<LocalityPostcodeTuple, Status> statusMap = new ConcurrentHashMap<>();
        Map<LocalityPostcodeTuple, LocationGrouping> groupingMap = new ConcurrentHashMap<>();

        // Load/parse the data in:
        URL suburbsJsonURL = App.class.getClassLoader().getResource("aus_suburbs.json");
        List<Location> locationList = Arrays.asList(mapper.readValue(suburbsJsonURL,
            Location[].class))
            .stream()
            .filter(l -> (l.getLatitude() != null && l.getLongitude() != null))
            .collect(Collectors.toList());

        locationList.forEach((location) -> {
            LocalityPostcodeTuple key = LocalityPostcodeTuple.fromLocation(location);
            statusMap.put(key, Status.EMPTY);
            groupingMap.put(key, new LocationGrouping(10, 50));
        });
        locationList.forEach((location) -> {
            executor.execute(() -> {
                getLocationResults(statusMap, groupingMap, locationList, location);
            });
        });

        Scanner command = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.print("Please enter a suburb name: ");
            String suburbName = command.nextLine();
            System.out.print("Please enter the postcode: ");
            String postcode = command.nextLine();

            if (!isValidInt(postcode)) {
                System.out.println("Invalid input for postcode");
                command.reset();
                continue;
            }
            LocalityPostcodeTuple tuple = new LocalityPostcodeTuple(Integer.parseInt(postcode), suburbName);
            Status status = statusMap.get(tuple);
            if (status == null) {
                System.out.println(String.format("Nothing found for %s, %s!",
                    suburbName, postcode));
                command.reset();
                continue;
            } else if (status == EMPTY) {
                // Skipping the running step for brevity, we will overwrite the entry in the map
                // Declare to the executor that this work has been done as not replicate work:
                locationList.forEach((location) -> {
                    getLocationResults(statusMap, groupingMap, locationList, location);
                });
            }
            LocationGrouping grouping = groupingMap.get(tuple);
            System.out.println(grouping.toString());
        }
        command.close();
    }

    public static void getLocationResults(Map<LocalityPostcodeTuple, Status> statusMap,
        Map<LocalityPostcodeTuple, LocationGrouping> groupingMap, List<Location> locationList,
        Location location) {
        LocalityPostcodeTuple key = LocalityPostcodeTuple
            .fromLocation(location);
        if (statusMap.get(key) == DONE) {
            return;
        } else {
            locationList.forEach((loc) -> {
                if (location == loc) return;
                double distance = Haversine.distance(loc.getLatitude(), loc.getLongitude(),
                    location.getLatitude(), location.getLongitude());
                groupingMap.get(key).addLocation(loc, distance);
            });
            statusMap.put(key, DONE);
        }
        groupingMap.get(key).curateListSizes();
    }

}
