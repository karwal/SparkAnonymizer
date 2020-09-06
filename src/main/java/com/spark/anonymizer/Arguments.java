package com.spark.anonymizer;


import com.spark.anonymizer.exception.InvalidInputException;

public class Arguments {

    private String input = "./input.txt";
    private String output = "./output";

    public Arguments() {

    }

    public Arguments(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public Arguments(String input) {
        this.input = input;
    }

    public static Arguments parseArguments(String[] args) throws InvalidInputException {
        if (args.length > 2) {
            throw new InvalidInputException();
        }
        if (args.length == 2) {
            return new Arguments(args[0], args[1]);
        } else if (args.length == 1) {
            return new Arguments(args[0]);
        } else {
            return new Arguments();
        }
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
