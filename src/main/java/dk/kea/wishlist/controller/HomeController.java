package dk.kea.wishlist.controller;

import dk.kea.wishlist.model.Wishlist;
import dk.kea.wishlist.repository.ListRepository;
import dk.kea.wishlist.repository.UserRepository;
import dk.kea.wishlist.repository.WishRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController
{
    private UserRepository userRepo;
    private ListRepository listRepo;
    private WishRepository wishRepo;
    public HomeController(UserRepository userRepo, ListRepository listRepo, WishRepository wishRepo)
    {
        this.userRepo = userRepo;
        this.listRepo = listRepo;
        this.wishRepo = wishRepo;
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

    @GetMapping("/wishlist/{id}")
    public String showWishList(@PathVariable("id") int listID, Model model, HttpSession session)
    {
        model.addAttribute("wishesList", wishRepo.getAllWishes(listID));
        session.setAttribute("currentWishlist", listID);
        return "wishlist";
    }

    @GetMapping("/createlist")
    public String showCreateList(HttpSession session)
    {
        return "createlist";
    }

    @PostMapping("/createlist")
    public String createList(@RequestParam("wishlistName")String tempName, HttpSession session)
    {
        Wishlist tempList = new Wishlist();

        tempList.setWishlistName(tempName);
        int tempInt = (int) session.getAttribute("userID");
        tempList.setUser_ID(tempInt);

        listRepo.createList(tempList);

        return "redirect:/";
    }
}
