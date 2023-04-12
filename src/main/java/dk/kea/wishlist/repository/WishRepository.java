package dk.kea.wishlist.repository;

import org.springframework.beans.factory.annotation.Value;

public class WishRepository
{
    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;
}
