package com.preservica.cli.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserInputServiceImpl implements UserInputService {

    private final Scanner scanner;

    public UserInputServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readUserInput() {
        return scanner.nextLine();
    }
}
