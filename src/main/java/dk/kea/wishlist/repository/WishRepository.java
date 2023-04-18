package dk.kea.wishlist.repository;

import dk.kea.wishlist.model.Wish;
import dk.kea.wishlist.model.Wishlist;
import dk.kea.wishlist.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository
{
    @Value("${spring.datasource.url}")
    private String HOSTNAME;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    public List<Wish> getAllWishes(int wishlistID)
    {
        List <Wish> wishesList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT wish_ID, product_name, product_price, product_link, reserved_status FROM wishwebapp.wish WHERE wishlist_ID = "+wishlistID;

            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while(resultSet.next())
            {
                int wishID = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                double productPrice = resultSet.getDouble(3);
                String productLink = resultSet.getString(4);
                boolean reservedStatus;
                if(resultSet.getInt(5) == 0)
                {
                    reservedStatus=false;
                }
                else
                {
                    reservedStatus=true;
                }

                Wish newWish = new Wish(wishID, productName, productPrice, productLink, reservedStatus);
                wishesList.add(newWish);
                System.out.println("Found " + newWish);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not get wishes");
        }
        return wishesList;
    }
    public Wish findWishByID(int wishID)
    {
        Wish wish = new Wish();
        wish.setWishID(wishID);
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT product_name, product_price, product_link, reserved_status FROM wishwebapp.wish WHERE wish_ID = "+wishID;

            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while(resultSet.next())
            {
                wish.setProductName(resultSet.getString(1));
                wish.setProductPrice(resultSet.getDouble(2));
                wish.setProductLink(resultSet.getString(3));
                if(resultSet.getInt(4) == 0)
                {
                    wish.setReservedStatus(false);
                }
                else
                {
                    wish.setReservedStatus(true);
                }
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("could not find wish by ID");
        }
        return wish;
    }

    public void createWish(Wish wish)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String CREATE_QUERY = "INSERT INTO wishwebapp.wish(wishlist_ID, product_name, product_price, product_link) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, wish.getWishlist_ID());
            preparedStatement.setString(2, wish.getProductName());
            preparedStatement.setDouble(3, wish.getProductPrice());
            preparedStatement.setString(4, wish.getProductLink());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not create new wish");
        }

    }
    public void editWish(Wish wish)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String UPDATE_QUERY = "UPDATE wishwebapp.wish SET product_name=?, product_price=?,product_link=? WHERE wish_ID = "+wish.getWishID();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1, wish.getProductName());
            preparedStatement.setDouble(2, wish.getProductPrice());
            preparedStatement.setString(3, wish.getProductLink());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("could not update Wish");
        }
    }

    public void deleteWish(int wishID)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String DELETE_QUERY = "DELETE FROM wish WHERE wish_ID ="+wishID;

            statement.executeUpdate(DELETE_QUERY);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not delete wish");
        }
    }
}
