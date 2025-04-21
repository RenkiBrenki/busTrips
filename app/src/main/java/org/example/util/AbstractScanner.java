package org.example.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class AbstractScanner {
    public static AbstractScanner INSTANCE = new AbstractScanner();

    private AbstractScanner() {}

    public <T> List<T> readData(Class<T> clazz, String path, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            // skip first line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            Field[] fields = clazz.getDeclaredFields();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                T instance = clazz.getDeclaredConstructor().newInstance();

                for (int i = 0; i < values.length && i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Object parsedValue = parseValue(field.getType(), values[i].trim());
                    field.set(instance, parsedValue);
                }
                if (condition.test(instance)) {
                    result.add(instance);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("stops.txt file not found.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in file.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error.");
            e.printStackTrace();
        }
        return result;
    }

    public <T, ID> T readDataByField(
            Class<T> clazz, String path, Function<T, ID> getter, ID targetId) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            // skip first line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            Field[] fields = clazz.getDeclaredFields();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                T instance = clazz.getDeclaredConstructor().newInstance();

                for (int i = 0; i < values.length && i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Object parsedValue = parseValue(field.getType(), values[i].trim());
                    field.set(instance, parsedValue);
                }

                if (getter.apply(instance).equals(targetId)) {
                    scanner.close();
                    return instance;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("stops.txt file not found.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in file.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error.");
            e.printStackTrace();
        }
        return null;
    }

    private Object parseValue(Class<?> type, String value) {
        if (value.equals("") || value == null) {
            return null;
        }
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt(value);
        } else if (type == Double.class || type == double.class) {
            return Double.parseDouble(value);
        } else if (type == String.class) {
            return value;
        } else if (type == LocalTime.class) {
            return LocalTime.parse(value);
        }
        return null;
    }
}
