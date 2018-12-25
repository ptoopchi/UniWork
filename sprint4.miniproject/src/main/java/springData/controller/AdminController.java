package springData.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.domain.OrganizerUser;
import springData.domain.Role;
import springData.repository.RoleRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	RoleRepository repo;
	@Autowired
	private UserRepository productRepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new OrganizerUserValidator(productRepo));
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model, String roleName) {
		model.addAttribute("orgUser", new OrganizerUser());
		return "admin/CreateUser";
	}

	@RequestMapping(value = "/create", params = "add", method = RequestMethod.POST)
	public String addNewUser(@Valid @ModelAttribute("orgUser") OrganizerUser u, BindingResult result,
			@RequestParam("roleName") String roleName, Model model) {
		
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		if(result.hasErrors()) {
			
			return "admin/CreateUser";
		}else {
			u.setRole(repo.findByRole(roleName));
			
			String pw = u.getPassword();
			
			u.setPassword(pe.encode(pw));
			
			productRepo.save(u);
			
			return "admin/done";
		}
			
	}

	@RequestMapping(value = "create", params = "cancel", method = RequestMethod.POST)
	public String cancelNewTodo() {
		return "admin/done";
	}
}
