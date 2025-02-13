package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitController {
    private static final Logger logger = LoggerFactory.getLogger(ExitController.class);
    public static void exit() {
        logger.info("Exit called");
        System.exit(0);
    }
}
