package com.bit2015.guestbook3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2015.guestbook3.dao.GuestBookDao;
import com.bit2015.guestbook3.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		List<GuestBookVo> list = guestBookDao.getList();
		model.addAttribute("list", list);
		
		return "index";
	}
	
	@RequestMapping(value="/deleteform", method = RequestMethod.GET)
	public String deleteform(){
		
		return "deleteform";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestBookVo guestBookVo){
		
		guestBookDao.insert(guestBookVo);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestBookVo guestBookVo){
		
		guestBookDao.delete(guestBookVo);
		
		return "redirect:/list";
	}
}
