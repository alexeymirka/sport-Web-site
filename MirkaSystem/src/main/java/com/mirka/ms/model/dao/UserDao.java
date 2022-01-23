package com.mirka.ms.model.dao;

import com.mirka.ms.model.entity.User;
import com.mirka.ms.model.entity.UserType;
import com.mysql.jdbc.Driver;
import org.intellij.lang.annotations.Language;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class UserDao {

    @Language("SQL")
    private static final String INSERT_USER = "INSERT INTO users (user_login, user_type_id, user_password) VALUES (?, ?, ?);";

    @Language("SQL")
    private static final String SELECT_PASSWORD_BY_LOGIN = "SELECT user_password FROM users WHERE user_login = ?;";

    @Language("SQL")
    private static final String SELECT_USER_BY_LOGIN = "SELECT U.user_id, U.user_login, U.user_password, UT.user_type_name " +
            "FROM users U INNER JOIN user_types UT ON U.user_type_id = UT.user_type_id WHERE U.user_login = ?;";

    @Language("SQL")
    private static final String CONTAINS_USER_LOGIN = "SELECT EXISTS(SELECT user_login FROM users WHERE user_login = ?) AS user_existence;";

    private static final Properties props = new Properties();

    static {
        try(InputStream inputStream = UserDao.class.getResourceAsStream("/property/db.properties")) {
            props.load(inputStream);
            DriverManager.registerDriver(new Driver());
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public void addUser(User user, String encryptedPassword) {
        try (Connection connection = DriverManager.getConnection((String) props.get("url"), props);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setInt(2, 2);
            preparedStatement.setString(3, encryptedPassword);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int userId = resultSet.getInt(1);
            user.setId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<String> findUserPasswordByLogin(String login) {
        Optional<String> foundPassword = Optional.empty();
        try (Connection connection = DriverManager.getConnection((String) props.get("url"), props);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundPassword = Optional.of(resultSet.getString("user_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundPassword;
    }

    public Optional<User> findUserByLogin(String login) {
        Optional<User> checkingUser = Optional.empty();
        try (Connection connection = DriverManager.getConnection((String) props.get("url"), props);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                checkingUser = Optional.of(buildUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkingUser;
    }

    public boolean existsLogin(String userLogin) {
        boolean result = false;
        try (Connection connection = DriverManager.getConnection((String) props.get("url"), props);
             PreparedStatement preparedStatement = connection.prepareStatement(CONTAINS_USER_LOGIN)) {
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1) != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String userLogin = resultSet.getString("user_login");
        UserType userType = UserType.valueOf(resultSet.getString("user_type_name"));
        return new User(userId, userLogin, userType);
    }
}