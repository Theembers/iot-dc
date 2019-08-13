package com.theembers.iot;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

import static org.springframework.boot.ansi.AnsiColor.*;

/**
 * @author TheEmbers Guo
 * createTime 2019-08-01 13:59
 */
public class TheEmbersBanner implements Banner {
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (String line : BANNER) {
            out.println(AnsiOutput.toString(BRIGHT_BLUE, line));
        }

        String version = Banner.class.getPackage().getImplementationVersion();
        version = (version == null ? "" : " (v" + version + ")...");
        out.println(AnsiOutput.toString(BRIGHT_YELLOW, SPRING_BOOT, version));
        out.println();
    }
    private static final String SPRING_BOOT = " :: Spring Boot Version :: ";
    private static final String[] BANNER = {
"\n" +
        "  _______ _          ______           _                   \n" +
        " |__   __| |        |  ____|         | |                  \n" +
        "    | |  | |__   ___| |__   _ __ ___ | |__   ___ _ __ ___ \n" +
        "    | |  | '_ \\ / _ \\  __| | '_ ` _ \\| '_ \\ / _ \\ '__/ __|\n" +
        "    | |  | | | |  __/ |____| | | | | | |_) |  __/ |  \\__ \\\n" +
        "    |_|  |_| |_|\\___|______|_| |_| |_|_.__/ \\___|_|  |___/\n" +
        "                                                          \n"
    };
}
