package dk.kea.wishlist.controller;

import dk.kea.wishlist.model.Wish;
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
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;

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
    @GetMapping("/createuser")
    public String showCreateLogin()
    {
        return "createuser";
    }

    @GetMapping("/createuserwrongusername")
    public String showCreateWrongUsername()
    {
        return "createuserwrongusername";
    }

    @GetMapping("/createuserwrongpw")
    public String showCreateWrongPw()
    {
        return "createuserwrongpw";
    }

    @PostMapping("/createuser")
    public String createLogin(@RequestParam(name="newusername")String newUsername, @RequestParam(name="newpassword")String newPassword, @RequestParam(name="newpassword2")String newPassword2)
    {
        if(newPassword.equals(newPassword2))
        {
            if(userRepo.createUser(newUsername, newPassword))
            {
                return "redirect:/login";
            }
            return "redirect:/createuserwrongusername";
        }
        return "redirect:/createuserwrongpw";

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
    @GetMapping("/createwish")
    public String showCreateWish(HttpSession session)
    {
        return "createwish";
    }
    @PostMapping("/createwish")
    public String createWish(@RequestParam("wishName")String wishName, @RequestParam("wishPrice")double wishPrice, @RequestParam("wishLink")String wishLink, HttpSession session)
    {
        int tempListID = (int) session.getAttribute("currentWishlist");
        Wish tempWish = new Wish(tempListID, wishName, wishPrice, wishLink);
        wishRepo.createWish(tempWish);
        return "redirect:/wishlist/"+tempListID;
    }

    @GetMapping("/wish/{id}")
    public String showEditWish(@PathVariable("id")int wishID, HttpSession session, Model model)
    {
        Wish editWish = wishRepo.findWishByID(wishID);
        model.addAttribute("wish", editWish);

        return "editwish";
    }

    @PostMapping("/editwish")
    public String editWish(@RequestParam("wishID")int tempWishID, @RequestParam("wishName")String editName,@RequestParam("wishPrice")double editPrice, @RequestParam("wishLink")String editLink, HttpSession session)
    {
        int tempListID = (int) session.getAttribute("currentWishlist");
        Wish tempWish = new Wish(tempListID, editName, editPrice, editLink);
        tempWish.setWishID(tempWishID);
        wishRepo.editWish(tempWish);

        return "redirect:/wishlist/"+tempListID;
    }

    @GetMapping("/deletewish/{id}")
    public String deleteWish(@PathVariable("id")int deleteID, HttpSession session)
    {
        wishRepo.deleteWish(deleteID);
        int tempListID = (int) session.getAttribute("currentWishlist");

        return "redirect:/wishlist/"+tempListID;
    }

}
