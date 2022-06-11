package com.preservica.cli.dp;

import com.preservica.cli.dp.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLIController implements CommandLineRunner {
    @Autowired
    private CommandFactory commandFactory;

    @Override
    public void run(String... args) throws Exception {;
        commandFactory.getMainMenuCommand().execute();
    }
}
