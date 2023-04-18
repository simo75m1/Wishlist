package dk.kea.wishlist.repository;

import dk.kea.wishlist.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository
{
    @Value("${spring.datasource.url}")
    private String HOSTNAME;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    public boolean checkUser(String username, String password) throws SQLException
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String SQL_QUERY = "SELECT * FROM wishwebapp.user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultset = preparedStatement.executeQuery();
            int rowCount = 0;
            while(resultset.next())
            {
                rowCount++;
            }
            if(rowCount == 0)
            {
                return false;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Cant check user in database");
        }

        return true;
    }

    public boolean checkUsername(String username)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String SQL_QUERY = "SELECT * FROM wishwebapp.user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);

            preparedStatement.setString(1, username);

            ResultSet resultset = preparedStatement.executeQuery();
            int rowCount = 0;
            while(resultset.next())
            {
                rowCount++;
            }
            if(rowCount == 0)
            {
                return false;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Cant check user in database");
        }
        return true;
    }

    public int getUserID(String username)
    {

        int id = -1;

        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String SQL_QUERY = "SELECT user_ID FROM wishwebapp.user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("user_ID");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not get user ID");
        }
        return id;
    }

    public boolean createUser(String username, String password)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String CREATE_QUERY = "INSERT INTO wishwebapp.user(username, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            if(!checkUsername(username)) {
                preparedStatement.executeUpdate();
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not create new wish");
        }
        return false;
    }
}
