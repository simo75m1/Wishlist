package dk.kea.wishlist.controller;

import dk.kea.wishlist.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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
    public String showFrontpage(HttpSession session)
    {
        if(session.getAttribute("userID") == null)
        {
            return "redirect:/login";
        }
        return "frontpage";
    }
    @GetMapping("/login")
    public String showLogin(HttpSession session)
    {
        if(session.getAttribute("userID") != null)
        {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String tryLogin(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) throws SQLException
    {
        if(userRepo.checkUser(username, password))
        {
            session.setAttribute("userID", userRepo.getUserID(username));
            return "redirect:/";
        }
        return "login";
    }
}
