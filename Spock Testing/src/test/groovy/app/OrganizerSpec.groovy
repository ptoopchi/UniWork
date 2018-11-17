package app

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import app.domain.Organizer
import app.domain.Todo
import spock.lang.*

@ContextConfiguration
@WebMvcTest(controllers=[])
class OrganizerSpec extends Specification {
	
	//Fixtures
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private ResultActions result;
	
	def organizer = new Organizer()
	
	
	//END OF fixtures

		
	
	//Checkpoint
	
	def "0: Decreasing Todo priority from 10 to 9 throws RuntimeException"() {
		
		given: "A todo with priority 10"
			def t = new Todo("some task", "some description")
			t.setPriority(10)
			
		when: "Descreasing priority to 9"
			t.setPriority(9)
			
		then: "RuntimeException is thrown"
			thrown(RuntimeException.class)		
	}
	
	//END OF Checkpoint
	
	//SECTION A
			
	def "1: perform an HTTP get / which should redirect to /list"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
			
		when: "perform an HTTP GET /"
			result = mockMvc.perform(get('/'))
		
		then: "status of the HTTP response should be 302"
			result.andExpect(status().is(302))
		
		and: "should be redirected to URL /list"
			result.andExpect(redirectedUrl('/list'))	
	}


	def "2: organizer with no todos on a get /create should see view CreateTodo with todo description null"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has no todos"
			OrganizerApp.organizer.todos = new ArrayList()
		
		when: "perform an HTTP GET /create"
			result = mockMvc.perform(get('/create'))
			
		then: "status of the HTTP response should be Ok (200)"
			result.andExpect(status().is(200))
			
		and: "should see the view CreateTodo"	
			result.andExpect(view().name('CreateTodo'))
		
		and: " model attribute todo has property description is null"
			result.andExpect(model().attribute('todo',hasProperty('description', is(null))))	
	
	}	

	
	def "3: organizer with no todos on a get /list should get Ok http response and view NoTodo"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
			
		and: "organizer has no todos"
			OrganizerApp.organizer.todos = new ArrayList()
			
		when: "perform an HTTP GET /list"
			result = mockMvc.perform(get('/list'))
		
		then: "status of the HTTP response should be Ok (200)"
			result.andExpect(status().is(200))
			
		and: "should see the view NoTodo"	
			result.andExpect(view().name('NoTodo'))
	}
	
	
	def "4: organizer with todos on a http get /next should get Ok http response and view NextTodo"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has todos"	
			OrganizerApp.organizer.todos = new ArrayList()
			
			def t = new Todo("some task", "some description")
			def t1 = new Todo("another task", "another description")
			
			OrganizerApp.organizer.addTodo(t)
			OrganizerApp.organizer.addTodo(t1)
			
		when: "perform an HTTP GET /next"
			result = mockMvc.perform(get('/next'))
		
		then: "status of the HTTP response should be Ok (200)"	
			result.andExpect(status().is(200))
		
		and: "should see the view NextTodo"
			result.andExpect(view().name('NextTodo'))
	}
	
	
		
	def "5: On a http post /create with params should get Http 302 response and view /list"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
			
		when: "perform a post /create"
			result = mockMvc.perform(post('/create')
				.param('task', 'my Task')
				.param('description', 'my Description')
				.param('priority', '0')
				.param('cancel', ''))
			
		then: "status of the HTTP response should be 302"
			result.andExpect(status().is(302))
		
		and: "should be redirected to /list"
			result.andExpect(redirectedUrl('/list'))
			
	}
	

	
	def "6: On post request /create with params should get ok http response and view CreateTodo and todo priority 4"() {
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
			
		when: "perform a post /create"
			result = mockMvc.perform(post('/create')
				.param('task', '')
				.param('description', 'my Description')
				.param('priority', '4')
				.param('important', '1')
				.param('add', ''))
			
		then: "status of the HTTP response should be Ok (200)"
			result.andExpect(status().is(200))
		
		and: "Should see view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
		
		and: "Model attribute todo has priority"
			result.andExpect(model().attribute('todo', hasProperty('priority', is(4))))
	}
	
	
	//END OF SECTION A
	//--------------------------------------------------------------------------------------------
	//SECTION B
	
	
	def "7: when organizer has no todos it should show the view NoTodo"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has no todos"
			OrganizerApp.organizer.todos = new ArrayList()
			
		when: "HTTP GET request /next"
			result = mockMvc.perform(get('/next'))
			
		then: "should show the view NoTodo"
			result.andExpect(view().name('NoTodo'))
	}
	
	
	//Whenever the organizer has todos, the HTTP GET request /create should show the view CreateTodo.
	
	
	def "8: when organizer has todos it should show the view CreateTodo"(){
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has todos"
			OrganizerApp.organizer.todos = new ArrayList()
			
			def t = new Todo("some task", "some description")
			def t1 = new Todo("another task", "another description")
			
			OrganizerApp.organizer.addTodo(t)
			OrganizerApp.organizer.addTodo(t1)
		
		when: "HTTP GET request /create"
			result = mockMvc.perform(get('/create'))
			
		then: "should show the view CreateTodo"	
			result.andExpect(view().name('CreateTodo'))
	}
	
	
	//The HTTP POST request /create with the values listed below should redirect to URL /list:
	
	def "9: On POST request /create with params should redirect to /list"(){
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
			result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','my Description')
			.param('priority','0')
			.param('cancel',''))
		
		then: "redirect to URL /list"
		result.andExpect(redirectedUrl('/list'))
	}
	
	//The HTTP POST request /create with the values listed below should show the view
	// CreateTodo and the model attribute todo should have errors:
	def "10: On POST request /create with params should view CreateTodo with errors"(){
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','my Description')
			.param('priority','0')
			.param('important','1')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and: "model attribute todo should have errors"	
			result.andExpect(model().attributeHasErrors('todo'))
	}
	//END OF SECTION B
	//--------------------------------------------------------------------------------------------
	//SECTION C
	
	def "11: important todos with no description cause error"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','')
			.param('priority','0')
			.param('important','1')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and: "model attribute todo should have errors"
			result.andExpect(model().attributeHasErrors('todo'))
	}
	
	def "12: important todos with description should not cause an error"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','this is a description')
			.param('priority','0')
			.param('important','0')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and:"model attribute todo should have errors"
			result.andExpect(model().attributeHasErrors('todo'))
	}
	
	def "13: If Todo not important priority must be below 100"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','this is a description')
			.param('priority','101')
			.param('important','0')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and: "model attribute todo should have errors"
			result.andExpect(model().attributeHasErrors('todo'))
	}
	
	
	def "14: non important todos should have Description with length greater than 20 "(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','')
			.param('description','thisislessthan20')
			.param('priority','5')
			.param('important','0')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and: "model attribute todo should have errors"
			result.andExpect(model().attributeHasErrors('todo'))
	}
	
	def "15: Important todos should not have the tasks matching the description"(){
		
		given: "context of the controller is setup"
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		when: "HTTP POST request /create"
		result = mockMvc.perform(post('/create')
			.param('task','NotEmpty')
			.param('description','NotEmpty')
			.param('priority','5')
			.param('important','1')
			.param('add',''))
		
		then: "show the view CreateTodo"
			result.andExpect(view().name('CreateTodo'))
			
		and: "model attribute todo should have errors"
			result.andExpect(model().attributeHasErrors('todo'))
	}
	
	
	def "16: If result has no errors adding a todo redirects to /list"(){
		
		given: "context of the controller is setup"
		
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has one todo"
		
			OrganizerApp.organizer.todos = new ArrayList()
			def t = new Todo("test", "Tessssst")
			OrganizerApp.organizer.addTodo(t)
			
	
		when: "HTTP POST request /create"
		
			result = mockMvc.perform(post('/create')	
			.param('task','bvjhv')
			.param('description','my Description')
			.param('priority','5')
			.param('important', '0')
			.param('add', ''))
			
			
		then: "check the list size of todos"
			assertThat(OrganizerApp.organizer.todos, hasSize(2))
			
		and: "redirect to URL /list"
			result.andExpect(redirectedUrl('/list'))
		
	}
	
	def "17: On deleteTodo(id) should delete todo with iD and redirect to /list"(){
		
		given: "context of the controller is setup"	
			mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
		
		and: "organizer has one todo"	
			OrganizerApp.organizer.todos = new ArrayList()
			def t = new Todo("task", "Tessssst")
			def i = t.getId().toString()
			OrganizerApp.organizer.addTodo(t)
			
			
		when: "HTTP GET request /delete"
		
			result = mockMvc.perform(get('/delete')
					
			.param('task','task')
			.param('description','Tessssst')
			.param('priority','5')
			.param('important', '0')
			.param('add', 't')
			.param('id', i)
			)
			
		then: "Delete todo with id"
			OrganizerApp.organizer.deleteTodo(t.getId())
							
		and: "redirect to URL /list"
			result.andExpect(redirectedUrl('/list'))
	}
	
	
	def "18: Show all todos from ListTodos"(){
		given: "context of the controller is setup"
		mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build()
	
	and: "organizer has one todo"
		OrganizerApp.organizer.todos = new ArrayList()
		def t = new Todo("task", "Tessssst")
		OrganizerApp.organizer.addTodo(t)
		
	when: "HTTP GET request /list"
		result = mockMvc.perform(get('/list'))

	then: "Show view ListTodos"
	result.andExpect(view().name('ListTodos'))
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
