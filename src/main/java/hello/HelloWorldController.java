package hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import hello.model.User;
import hello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kebbot")
public class HelloWorldController {

    private static final String template = "Hello, %s! version 0.0.1";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private IUserService userDao;

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public @ResponseBody
    List<User> listUsers() {

        User u = new User();
        u.setName("c");
        userDao.addUser(u);

        return userDao.listUser();
    }

}