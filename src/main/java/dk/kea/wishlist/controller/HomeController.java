package dk.kea.wishlist.controller;

import dk.kea.wishlist.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController
{
    private UserRepository userRepo;
    public HomeController(UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }
    @GetMapping("/")
    public String showFrontpage()
    {
        return "frontpage";
    }
    @GetMapping("/login")
    public String showLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String tryLogin(@RequestParam("username")String username, @RequestParam("password")String password) throws SQLException
    {
        if(userRepo.checkUser(username, password))
        {
            return "redirect:/";
        }

        return "login";
    }
}
