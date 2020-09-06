package com.spark.anonymizer;

import com.spark.anonymizer.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ArgumentsTest {

    @Test
    public void testArgumentParseWithTwoArguments() {

        String[] args = new String[]{"./input.txt", "./output"};
        try {
            Arguments arguments = Arguments.parseArguments(args);
            assertEquals("./input.txt", arguments.getInput());
            assertEquals("./output", arguments.getOutput());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArgumentParseWithSingleArgument() {

        String[] args = new String[]{"./input.txt"};
        try {
            Arguments arguments = Arguments.parseArguments(args);
            assertEquals("./input.txt", arguments.getInput());
            assertEquals("./output", arguments.getOutput());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArgumentParseWithNoArgument() {

        String[] args = new String[]{};
        try {
            Arguments arguments = Arguments.parseArguments(args);
            assertEquals("./input.txt", arguments.getInput());
            assertEquals("./output", arguments.getOutput());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
