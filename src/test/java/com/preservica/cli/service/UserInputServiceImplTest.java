package com.preservica.cli.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for @{@link UserInputServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class UserInputServiceImplTest {
    private UserInputServiceImpl userInputService;

    @ParameterizedTest
    @CsvSource({"y,true", "Y,true", "n,false", "N,false"})
    void readConfirmationFromConsole(final String input, boolean expectedResult) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        userInputService = new UserInputServiceImpl();
        boolean confirmation = userInputService.readConfirmationFromConsole();
        assertEquals(expectedResult, confirmation);
    }

}