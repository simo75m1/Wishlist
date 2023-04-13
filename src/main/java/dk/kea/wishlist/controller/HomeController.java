package dk.kea.wishlist.controller;

import dk.kea.wishlist.repository.ListRepository;
import dk.kea.wishlist.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController
{
    private UserRepository userRepo;
    private ListRepository listRepo;
    public HomeController(UserRepository userRepo, ListRepository listRepo)
    {
        this.userRepo = userRepo;
        this.listRepo = listRepo;
    }

    @GetMapping("/")
    public String showFrontpage(HttpSession session, Model model)
    {
        if(session.getAttribute("userID") == null)
        {
            return "redirect:/login";
        }
        int userID = (int) session.getAttribute("userID");
        model.addAttribute("wishlists", listRepo.getAllLists(userID));

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
            session.setAttribute("username", username);
            return "redirect:/";
        }
        return "login";
    }
}
