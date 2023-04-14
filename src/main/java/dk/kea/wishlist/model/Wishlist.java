package dk.kea.wishlist.model;

public class Wishlist
{
    private int wishlistID;
    private String wishlistName;
    private int user_ID;


    public Wishlist(int wishlistID, String wishlistName) {
        this.wishlistID = wishlistID;
        this.wishlistName = wishlistName;
    }
    public Wishlist()
    {}

    public int getUser_ID()
    {
        return user_ID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setUser_ID(int user_ID)
    {
        this.user_ID = user_ID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistID=" + wishlistID +
                ", wishlistName='" + wishlistName + '\'' +
                '}';
    }
}
