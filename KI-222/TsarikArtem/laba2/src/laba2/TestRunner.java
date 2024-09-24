package laba2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly 1 argument must be provided");
        }

        // Завантаження класу, що містить тести
        Class<?> testClass = Class.forName(args[0]);
        System.out.println("Class " + testClass.getName() + " successfully loaded");

        // Отримання всіх методів класу
        Method[] methods = testClass.getDeclaredMethods();

        // Лічильники для підрахунку результатів тестів
        int totalMethods = 0;
        int totalTests = 0;
        int successfulTests = 0;
        int failedTests = 0;

        // Прохід по всіх методах класу
        for (Method method : methods) {
            totalMethods++;

            // Перевірка чи метод відповідає критеріям тесту
            if (method.getName().startsWith("test") &&
                Modifier.isPublic(method.getModifiers()) &&
                method.getParameterCount() == 0 &&
                method.getReturnType() == void.class) {

                totalTests++;

                try {
                    // Створення екземпляру класу
                    Object instance = testClass.getDeclaredConstructor().newInstance();

                    // Виклик тестового методу
                    method.invoke(instance);
                    System.out.println("Test: " + method.getName() + " SUCCESSFUL");
                    successfulTests++;
                } catch (Exception e) {
                    System.out.println("Test: " + method.getName() + " FAILED, error: " + e.getCause().getMessage());
                    failedTests++;
                }
            } else {
                System.out.println("Method: " + method.getName() + " is not a test method");
            }
        }

        // Виведення звіту
        System.out.println("Total methods: " + totalMethods);
        System.out.println("Total tests: " + totalTests);
        System.out.println("Successful tests: " + successfulTests);
        System.out.println("Failed tests: " + failedTests);
    }
}
