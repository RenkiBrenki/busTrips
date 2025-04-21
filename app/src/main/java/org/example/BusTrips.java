package org.example;

import org.example.model.Stop;
import org.example.model.StopTime;
import org.example.util.ReadDataUtil;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class BusTrips {
    public static void main(String[] args) {
        if (!validateArguments(args)) {
            return;
        }
        Integer stationId = Integer.parseInt(args[0]);
        Integer numberOfBusses = Integer.parseInt(args[1]);
        String format = args[2];

        LocalTime from = LocalTime.of(12, 0);
        LocalTime to = from.plusHours(2);

        ReadDataUtil dataUtil = new ReadDataUtil("src/main/java/org/example/gtfs/", from, to);

        Stop stop = dataUtil.getStation(stationId);
        if (stop == null) {
            System.out.println("Stop not found");
            return;
        }

        HashMap<Integer, List<StopTime>> map =
                dataUtil.getStopTimesForStation(stationId, numberOfBusses);
        System.out.println(stop.getName());
        printStopTimes(map, format, from);
    }

    private static void printStopTimes(
            HashMap<Integer, List<StopTime>> stopTimes, String format, LocalTime from) {
        stopTimes.forEach(
                (key, value) -> {
                    System.out.print(key + ": ");
                    for (StopTime stopTime : value) {
                        if (format.equals("absolute")) {
                            System.out.print(stopTime.getArrivalTime() + " ");
                        } else {
                            System.out.print(
                                    ChronoUnit.MINUTES.between(from, stopTime.getArrivalTime())
                                            + "min ");
                        }
                    }
                    System.out.println();
                });
    }

    private static boolean validateArguments(String[] args) {
        if (args.length != 3 || args == null) {
            System.out.println(
                    "Invalid arguments, format: 'stationId numberOfBusses relavite/absolute'");
            return false;
        }

        try {
            Integer.parseInt(args[0]);
            Integer num = Integer.parseInt(args[1]);

            if (!args[2].equals("relative") && !args[2].equals("absolute") || num < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid arguments, format: 'stopId numberOfBusses relavite/absolute'");
            return false;
        }
        return true;
    }
}
