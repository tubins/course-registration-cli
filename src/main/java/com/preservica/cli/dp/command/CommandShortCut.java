package com.preservica.cli.dp.command;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum CommandShortCut {
    N("New"),
    L("List"),
    A("Assign"),
    R("Remove"),
    B("Back"),
    Q("Quit"),
    S("Student"),
    C("Course"),
    T("Teacher"),
    M("Main");
    private final String description;

    private static final Set<CommandShortCut> NEED_NO_BACK = EnumSet.of(M, Q, B);

    CommandShortCut(final String description) {
        this.description = description;
    }

    public static boolean isBackButtonRequired(final CommandShortCut commandShortCut) {
        return !NEED_NO_BACK.contains(commandShortCut);
    }
}
