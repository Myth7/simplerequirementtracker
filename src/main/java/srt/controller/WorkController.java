package srt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import srt.data.domain.management.Data;
import srt.data.domain.management.User;
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
//	@RequestMapping(value="/work", method=RequestMethod.GET)
	@RequestMapping(value="/work", method=RequestMethod.GET)
	public String showWorkPage(Model model){
		Data data=new Data();
		model.addAttribute("data",data);
		return "work";
	}
	
//	@RequestMapping(value="/work", method=RequestMethod.GET)
//	public String WorkPage(){
//		
//		return "work";
//	}
	
	@RequestMapping(value="/work", method=RequestMethod.POST)
	public String workAddData(Data data,@CookieValue("username") String userName){
		data.setCreatedBy(userName);
		dataRepository.addData(data);
		return "work";///////////////////////////////////////////
	}
	
	@RequestMapping(value="/work/getData", method=RequestMethod.GET)
	public String showData(int page, int rows, HttpServletResponse response,@CookieValue("username") String userName) throws IOException{
		
		List<Data> list = dataRepository.getDatasByCreator(userName);
		String jsonString = JSON.toJSONString(list);
		PrintWriter out = response.getWriter();
        out.print(jsonString);         
        //relish
        out.flush();
        out.close();
        out = null;
        Cookie cookie = new Cookie("username",userName);   // 新建Cookie
		cookie.setMaxAge(-1);           // 设置生命周期为MAX_VALUE
		response.addCookie(cookie);                    // 输出到客户端

		return "work";
	}
	
}
