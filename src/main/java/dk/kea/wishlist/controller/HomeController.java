package dk.kea.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/login")
    public String showLogin()
    {
        return "login";
    }
}
