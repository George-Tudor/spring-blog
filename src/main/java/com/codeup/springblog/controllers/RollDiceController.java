package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice() {
        return "/views-lec/roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String rollDiceSide(@PathVariable int number) {
        int userNumber = number;
        double diceNumber = Math.round(Math.random() * 6);
        if(userNumber == (int) diceNumber) {
            return "<h2>The number was </h2>";
        }
        return "<h2></h2>";
    }
}

//Passing data to views
//Create a page at /roll-dice that asks the user to guess a number. There should be links on this page for 1 through 6 that should make a GET request to /roll-dice/n where n is the number guessed. This page should display a random number (the dice roll), the user's guess and a message based on whether or not they guessed the correct number.
//Bonus
//"Roll" a series of dice on each page load, as opposed to an individual die. Show all the rolls to the user and display how many rolls matched their guess.
//@GetMapping("/multiply/{number1}/and/{number2}")
//@ResponseBody
//public String multiply(@PathVariable int number1, @PathVariable int number2) {
//    int product = number1 * number2;
//    return String.format("The product of %d and %d is %d ", number1, number2, product);
//}