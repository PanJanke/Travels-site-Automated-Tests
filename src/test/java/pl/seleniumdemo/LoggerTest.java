package pl.seleniumdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.trace("trace"); //1
        logger.debug("debug"); // 2
        logger.info("info"); //3
        logger.warn("Warn");//4
        logger.error("Error");//5
        logger.fatal("fatal"); //6

    }
}
