package springData.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springData.domain.Organizer;
import springData.domain.OrganizerUser;
import springData.domain.Todo;
import springData.repository.OrganizerRepository;
import springData.repository.RoleRepository;
import springData.repository.TodoRepository;
import springData.repository.UserRepository;

@Controller
@RequestMapping("/")
public class DisplayTodoController {
	
	
	@Autowired
	TodoRepository todo;
	@Autowired
	private RoleRepository role;
	@Autowired
	private UserRepository user;
	@Autowired
	private OrganizerRepository organ;
	
	@RequestMapping("/")
	public String start() {
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String listTodos(Model model,Principal principal) {
		OrganizerUser person = user.findByLogin(principal.getName());
		List<Todo> todos = new ArrayList<Todo>();
		if(person.getRole().getRole().equals(("MANAGER"))) {
			for(Organizer organizer:person.getOrganizers())
				todos.addAll(organizer.getTodos());
		}
		else if (person.getRole().getRole().equals(("ASSISTANT"))) {
			todos = (List<Todo>) todo.findAll();
		}
		if (todos.isEmpty()) {
			return "NoTodo";
		}
		else {		
			model.addAttribute("todos", todos);
			return "ListTodos";
		}
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String next(Model model,Principal principal) {
		Todo t = null;
		OrganizerUser person = user.findByLogin(principal.getName());
		List<Todo> todos = new ArrayList<Todo>();
	
		Organizer organizer1 = new Organizer();
		
			
		try {
			//t = OrganizerApp.organizer.getHighestPrioTodo();
			if(person.getRole().getRole().equals("MANAGER")) {
				for(Organizer organizer:person.getOrganizers()) {
					organizer1.addTodo(organizer.getHighestPrioTodo());
				}	
			}
			else if(person.getRole().getRole().equals("ASSISTANT")) {
				for(Organizer organizer: organ.findAll()) {
					organizer1.addTodo(organizer.getHighestPrioTodo());
				}
				
			}
			t= organizer1.getHighestPrioTodo();	
			
		} catch (Exception e) {
			return "NoTodo";
		}
		model.addAttribute("todo", t);
		return "NextTodo";
	}

}
