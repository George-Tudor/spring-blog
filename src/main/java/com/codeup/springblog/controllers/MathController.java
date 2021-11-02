package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number1}/and/{number2}")
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2) {
        int sum = number1 + number2;
        return String.format("The sum of %o and %o is %o ", number1, number2, sum);
    }

    @GetMapping("/multiply/{number1}/and/{number2}")
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2) {
        int product = number1 * number2;
        return String.format("The product of %d and %d is %d ", number1, number2, product);
    }

    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2) {
        int result = number2 - number1;
        return String.format("The result of subtracting %d from %d is %d ", number1, number2, result);
    }

    @GetMapping("/divide/{number1}/by/{number2}")
    @ResponseBody
    public String divide(@PathVariable int number1, @PathVariable int number2) {
        int quotient = number1 / number2;
        return String.format("The result of dividing %d by %d is %d ", number1, number2, quotient);
    }

}
