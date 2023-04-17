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

}
