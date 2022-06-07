package com.preservica.cli.service;

/** Input service. */
public interface UserInputService {

     boolean readConfirmationFromConsole();

     String readStringInputFromConsole();

     Integer readIntegerInputFromConsole();
     Integer readIntegerInputFromConsole(Integer minRange, Integer maxRange);
}
