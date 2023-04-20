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
        List<Wishlist> wlList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT wishlist_ID, wishlist_name FROM wishwebapp.wishlist WHERE user_ID= " + userID;

            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next())
            {
                int wishlistID = resultSet.getInt(1);
                String wishlistName = resultSet.getString(2);

                Wishlist wishlist = new Wishlist(wishlistID, wishlistName);
                wlList.add(wishlist);
                System.out.println("Found " + wishlist);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not get lists");
        }
        return wlList;
    }

    public void createList(Wishlist wishlist)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String CREATE_QUERY = "INSERT INTO wishwebapp.wishlist(user_ID, wishlist_name) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, wishlist.getUser_ID());
            preparedStatement.setString(2, wishlist.getWishlistName());

            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not create new wishlist");
        }

    }

    public void deleteList(int wishlistID)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String DELETEWISH_QUERY = "DELETE FROM wishwebapp.wish WHERE wishlist_ID =" + wishlistID;
            final String DELETELIST_QUERY = "DELETE FROM wishwebapp.wishlist WHERE wishlist_ID =" + wishlistID;
            statement.executeUpdate(DELETEWISH_QUERY);
            statement.executeUpdate(DELETELIST_QUERY);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not delete wishlist");
        }
    }

    public String getListNameByID(int listID)
    {
        String listName = "no name";
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT wishlist_name FROM wishwebapp.wishlist WHERE wishlist_ID =" + listID;
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            resultSet.next();
            listName = resultSet.getString(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not get list name by id");
        }

        return listName;
    }

    public void editWishlistName(Wishlist wishlist)
    {
        try
        {
            Connection connection = ConnectionManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
            final String SQL_QUERY = "UPDATE wishwebapp.wishlist SET wishlist_name = ? WHERE wishlist_ID =" + wishlist.getWishlistID();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            String tempName = wishlist.getWishlistName();
            preparedStatement.setString(1, tempName);
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Could not edit wishlist name");
        }

    }

    public Wishlist findWishlistByID(int listID)
    {
        Wishlist findList = new Wishlist();

        String tempName = getListNameByID(listID);
        findList.setWishlistName(tempName);

        return findList;
    }
}
