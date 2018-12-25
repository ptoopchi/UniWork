package springData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springData.domain.Organizer;
import springData.domain.OrganizerUser;
import springData.domain.Role;
import springData.repository.OrganizerRepository;
import springData.repository.RoleRepository;
import springData.repository.UserRepository;

@SpringBootApplication
public class OrganizerApp implements CommandLineRunner  { 
	
	@Autowired 
	RoleRepository repo;
	@Autowired
	private UserRepository userRepo;
	/**
	 * An organizer object for everyone to use.
	 */
	//public static Organizer organizer = new Organizer();
	
	public static void main(String[] args) {
        SpringApplication.run(OrganizerApp.class, args);
        
    }

	@Override
	public void run(String... args) throws Exception {
		
		// TODO Task 
		
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		
		Role role1 = new Role();
		role1.setId(0);
		role1.setRole("ADMIN");
		
		Role role2 = new Role();
		role2.setId(1);
		role2.setRole("MANAGER");
		
		Role role3 = new Role();
		role3.setId(2);
		role3.setRole("ASSISTANT");
		
		//repo.save(role1);
		repo.save(role2);
		repo.save(role3);
	
		
		OrganizerUser user1 = new OrganizerUser();
		user1.setLogin("admin");
		user1.setPassword(pe.encode("admin"));
		user1.setRole(role1);
		userRepo.save(user1);
	}
}
