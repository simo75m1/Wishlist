package dk.kea.wishlist.repository;

import dk.kea.wishlist.model.Wishlist;
import dk.kea.wishlist.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ListRepository
{
    @Value("${spring.datasource.url}")
    private String HOSTNAME;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;


    public List<Wishlist> getAllLists(int userID)
    {
        List <Wishlist> wlList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT wishlist_ID, wishlist_name FROM wishwebapp.wishlist WHERE user_ID= "+userID;

            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while(resultSet.next())
            {
                int wishlistID = resultSet.getInt(1);
                String wishlistName = resultSet.getString(2);

                Wishlist wishlist = new Wishlist(wishlistID, wishlistName);
                wlList.add(wishlist);
                System.out.println("Found " + wishlist);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not get lists");
        }
        return wlList;
    }



}
