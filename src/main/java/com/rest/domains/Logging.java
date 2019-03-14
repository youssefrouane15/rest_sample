package com.rest.domains;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Logging {


    public Logging(Level log,  Logger logger) {
        logger.setLevel(log);
    }
    public Logging(Level log, String packageName) {
        Logger logger = (Logger) LoggerFactory.getLogger(packageName);
        logger.setLevel(log);
    }
}
