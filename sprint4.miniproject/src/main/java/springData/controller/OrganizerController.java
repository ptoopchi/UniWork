package springData.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.OrganizerApp;
import springData.domain.Organizer;
import springData.domain.OrganizerUser;
import springData.domain.Role;
import springData.domain.Todo;
import springData.repository.OrganizerRepository;
import springData.repository.RoleRepository;
import springData.repository.TodoRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/")
public class OrganizerController {

	@Autowired
	TodoRepository todo;
	@Autowired
	 RoleRepository role;
	@Autowired
	 UserRepository user;
	@Autowired
	 OrganizerRepository organ;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new TodoValidator());
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("todo", new Todo());
		return "CreateTodo";
	}

	@RequestMapping(value = "create", params = "add", method = RequestMethod.POST)
	public String addNewTodo(@Valid @ModelAttribute("todo") Todo t, BindingResult result,Principal principal ,Model model) {
		
		OrganizerUser person = user.findByLogin(principal.getName());
				
		Organizer org1;
		
		if (result.hasErrors()) {
			return "CreateTodo";
		} else {
			if(person.getOrganizers().isEmpty()) {
				org1 = new Organizer();
				org1.setOwner(person);
				org1.addTodo(t);
				person.getOrganizers().add(org1);
			}else {
			person.getOrganizers().get(0).addTodo(t);
			}
			user.save(person);
						
			return "redirect:/list";
		}
	}

	@RequestMapping(value = "create", params = "cancel", method = RequestMethod.POST)
	public String cancelNewTodo() {
		return "redirect:/list";
	}

	@RequestMapping(value = "delete", params = "id", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam("id") int id, Principal principal) {
		
		
		OrganizerUser p = user.findByLogin(principal.getName());
		p.getOrganizers().stream().forEach(o -> o.getTodos().removeIf(t -> t.getId() == id));
		
		user.save(p);
		
		return "redirect:/list";
	}
}
