package springData.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springData.domain.OrganizerUser;
import springData.repository.UserRepository;

@Controller
public class AuthenticationController {

	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/user-login", method = RequestMethod.GET)
	public String loginForm() {
		return "security/login-form";
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", true);
		return "security/login-form";
	}

	/**
	 * Successful login is a redirect based on the role of the user
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public String successLogin(Principal principal) {
		
		OrganizerUser user = userRepo.findByLogin(principal.getName());
		
		String view;
		if(user.getRole().getRole().equals("ADMIN")) {
			view = "redirect:/admin/create";
		}
		else {
			view = "redirect:/list";
		}
		

		return view;
	}

	@RequestMapping(value = "/user-logout", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("logout", true);
		return "security/login-form";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String error() {
		return "security/error-message";
	}
}