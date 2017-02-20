package srt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import srt.data.domain.management.Data;
import srt.data.repository.management.IDataRepository;

@Controller
public class WorkController {
	
	@Autowired
	private IDataRepository dataRepository;
//
//	@Autowired
//	public WorkController(IUserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
	@RequestMapping(value="/work", method=RequestMethod.GET)
	public String showRegisterPage(){
		
		return "work";
	}
	
	@RequestMapping(value="/work/add", method=RequestMethod.POST)
	public String register(Data data,@CookieValue("username") String userName){
		data.setCreatedBy(userName);
		dataRepository.addData(data);
		return "work";///////////////////////////////////////////
	}
	
}
