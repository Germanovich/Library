package com.epam.library.service.impl;

import com.epam.library.dao.OrderDao;
import com.epam.library.dao.UserDao;
import com.epam.library.dao.impl.OrderDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.exception.BusinessException;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;
import com.epam.library.resource.ConfigurationManager;
import com.epam.library.resource.ResourceManager;
import com.epam.library.service.UserService;
import com.epam.library.util.MD5Util;
import com.epam.library.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static UserService instance;

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        if (user.getRole() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.user.data"));
        }
        user.setDateOfRegistration(new Date());
        verifyUserData(user);
        user.setPassword(MD5Util.md5Encode(user.getPassword()));
        userDao.add(user);
        LOGGER.info("User added");
    }

    @Override
    public void removeUser(Integer id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.user.exists"));
        }
        if (orderDao.getListByUserId(id).isEmpty()) {
            userDao.delete(id);
            LOGGER.info("User deleted");
            return;
        }
        throw new BusinessException(ResourceManager.getInstance().getProperty("message.exception.user.has.orders"));
    }

    @Override
    public void updateUser(User newUser) {
        verifyUserData(newUser);
        if (newUser.getId() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.user.data"));
        }
        User oldUser = userDao.getById(newUser.getId());
        if (oldUser == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.user.exists"));
        }
        if (newUser.getRole() == null) {
            newUser.setRole(oldUser.getRole());
        }
        if (newUser.getDateOfRegistration() == null) {
            newUser.setDateOfRegistration(oldUser.getDateOfRegistration());
        }

        userDao.update(newUser);
        LOGGER.info("User updated");
    }

    @Override
    public Integer getNumberOfRows() {
        return userDao.getNumberOfRows();
    }

    @Override
    public User getUser(Integer id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.no.such.user.exists"));
        }
        return user;
    }

    @Override
    public User getUserByUsernameAndPassword(String login, String password) {
        User user = userDao.getByLoginAndPassword(login, MD5Util.md5Encode(password));
        if (user != null) {
            return user;
        }
        throw new BusinessException(ResourceManager.getInstance()
                .getProperty("message.exception.no.such.user.exists"));
    }

    @Override
    public Boolean isUserExist(String login) {
        return userDao.getByLogin(login) != null;
    }

    @Override
    public Boolean isUserExist(String login, String password) {
        return userDao.getByLoginAndPassword(login, MD5Util.md5Encode(password)) != null;
    }

    @Override
    public List<User> getUserList() {
        return userDao.getAll();
    }

    @Override
    public List<User> getUserList(Integer currentPage) {
        return userDao.getAll(currentPage,
                Integer.parseInt(ConfigurationManager.getProperty("app.recordsPerPage")));
    }

    @Override
    public List<User> getUserListByRole(UserRole role) {
        return userDao.getListByRole(role);
    }

    private void verifyUserData(User user) {
        if (user.getName() == null ||
                user.getSurname() == null ||
                user.getDateOfBirth() == null ||
                user.getLogin() == null ||
                user.getPassword() == null) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.user.data"));
        }

        if (Boolean.FALSE.equals(Validator.validateString(user.getName())) ||
                Boolean.FALSE.equals(Validator.validateString(user.getSurname())) ||
                Boolean.FALSE.equals(Validator.validateString(user.getLogin())) ||
                Boolean.FALSE.equals(Validator.validatePassword(user.getPassword()))) {
            throw new BusinessException(ResourceManager.getInstance()
                    .getProperty("message.exception.not.found.all.user.data"));
        }
        LOGGER.info("User verified");
    }
}
