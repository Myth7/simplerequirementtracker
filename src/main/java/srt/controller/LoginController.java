package srt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import srt.data.domain.management.User;
import srt.data.repository.management.IUserRepository;

@Controller
public class LoginController {
	
	private IUserRepository userRepository;

	@Autowired
	public LoginController(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(Model model){
		User user=new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user,HttpServletResponse response){
		if(user!=null && user.getPassword()!=null && user.getUserName()!=null){
			User registeredUser=userRepository.getUsersByUserName(user.getUserName());
			if(registeredUser!=null){
				if(user.getPassword().equals(registeredUser.getPassword())){
//					return "redirect:/result?userId="+registeredUser.getUserId();
					Cookie cookie = new Cookie("username",user.getUserName());   // 新建Cookie
					cookie.setMaxAge(-1);           // 设置生命周期为MAX_VALUE
					response.addCookie(cookie);                    // 输出到客户端
					return "redirect:/work";
				}
			}
		}
		return "redirect:/login";
	}
}
