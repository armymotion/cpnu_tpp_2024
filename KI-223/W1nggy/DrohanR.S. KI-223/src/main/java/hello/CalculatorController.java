package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    @GetMapping("/calculate")
    public String showCalculator() {
        // Отображаем страницу калькулятора
        return "calculator"; // Название HTML-шаблона
    }

    @GetMapping("/calculate/result")
    @ResponseBody
    public String calculate(@RequestParam double num1, @RequestParam double num2, @RequestParam String operation) {
        double result;
        try {
            result = switch (operation.toLowerCase()) {
                case "add" -> num1 + num2;
                case "subtract" -> num1 - num2;
                case "multiply" -> num1 * num2;
                case "divide" -> {
                    if (num2 != 0) {
                        yield num1 / num2;
                    } else {
                        throw new ArithmeticException("Error: Division by zero.");
                    }
                }
                default -> throw new IllegalArgumentException("Error: Invalid operation. Please use add, subtract, multiply, or divide.");
            };
        } catch (IllegalArgumentException | ArithmeticException e) {
            return e.getMessage();
        }
        return "Result: " + result;
    }
}
