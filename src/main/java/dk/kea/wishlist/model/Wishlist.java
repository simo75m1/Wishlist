package dk.kea.wishlist.model;

public class Wishlist
{
    private int wishlistID;
    private String wishlistName;

    public Wishlist(int wishlistID, String wishlistName) {
        this.wishlistID = wishlistID;
        this.wishlistName = wishlistName;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public String getWishlistName() {
        return wishlistName;
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
