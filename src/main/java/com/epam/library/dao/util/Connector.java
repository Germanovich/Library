package com.epam.library.dao.util;

import com.epam.library.exception.DaoException;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private final Logger logger = LogManager.getLogger();
    private static Connector instance;
    private Connection connection;

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            connect();
        }

        try {
            if (connection.isClosed()) {
                connect();
                logger.info("Try to reconnect");
            }
        } catch (SQLException e) {
            logger.error(ResourceManager.getInstance().getProperty("message.exception.database"));
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database"));
        }
        return connection;
    }

    private void connect() {
        try {
            Class.forName(ConfigurationManager.getProperty("jdbc.driver"));

            connection = DriverManager.getConnection(ConfigurationManager.getProperty("jdbc.url"),
                    ConfigurationManager.getProperty("jdbc.user"),
                    ConfigurationManager.getProperty("jdbc.password"));
        } catch (ClassNotFoundException e) {
            logger.error(ResourceManager.getInstance().getProperty("message.exception.driver"));
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.driver"));
        } catch (SQLException e) {
            logger.error(ResourceManager.getInstance().getProperty("message.exception.driver"));
            throw new DaoException(ResourceManager.getInstance().getProperty("message.exception.database"));
        }
    }
}
