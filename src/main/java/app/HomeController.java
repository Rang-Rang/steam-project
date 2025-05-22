package app;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.steam.Library;
import app.model.users.Customer;
import app.model.users.SupportStaff;
import app.model.users.User;

@Controller
public class HomeController {
    private ArrayList<User> users;
	
	public HomeController() {
		users = new ArrayList<>();
		users.add(new Customer("001", "faddd", "fdhl.ahmd.com", "rit"));
		users.add(new SupportStaff("000", "mimin", "mimin@gmail.com", "abcd"));
		
	}

    @GetMapping("/")
    public String home(Model model) {

        return "login"; 
    }
    
    @RequestMapping("/login-action")
    public String loginAction(@RequestParam("input") String input,
            				@RequestParam("password") String password,
            				Model model) {
    	for (User user : users) {
    		if (user.isLoginMatch(input, password)) {
    			if (user instanceof SupportStaff) {
    				return "redirect:/support";
    			} else {
    				return "redirect:/home";
    			}
					}
					}
    	model.addAttribute("error", "Nama/email atau password salah!");
    	return "login";
    }

    
    @GetMapping("/home")
    public String home() {
        return "home"; 
    }
    
    @GetMapping("/support")
    public String support() {
        return "support"; 
    }

	@GetMapping("/library")
    public String library(Library lib) {

        return "library"; 
    }
}
