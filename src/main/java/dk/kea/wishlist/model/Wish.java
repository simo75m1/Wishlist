package dk.kea.wishlist.model;

public class Wish
{
    private int wishID;
    private String productName;
    private double productPrice;
    private String productLink;
    private boolean reservedStatus;

    public Wish(int wishID, String productName, double productPrice, String productLink, boolean reservedStatus)
    {
        this.wishID = wishID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLink = productLink;
        this.reservedStatus = reservedStatus;
    }

    public Wish(String productName, double productPrice, String productLink)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLink = productLink;
    }

    public int getWishID()
    {
        return wishID;
    }

    public String getProductName()
    {
        return productName;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public String getProductLink()
    {
        return productLink;
    }

    public boolean getReservedStatus()
    {
        return reservedStatus;
    }

    public void setWishID(int wishID)
    {
        this.wishID = wishID;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public void setProductLink(String productLink)
    {
        this.productLink = productLink;
    }

    public void setReservedStatus(boolean reservedStatus)
    {
        this.reservedStatus = reservedStatus;
    }

    @Override
    public String toString()
    {
        return "Wish{" +
                "wishID=" + wishID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productLink='" + productLink + '\'' +
                ", reservedStatus=" + reservedStatus +
                '}';
    }
}
