package com.springboot.app.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Model.*;
import com.springboot.app.Repository.*;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepository stdrepo;

	@Autowired
	CourseRepository crsrepo;

	@GetMapping("/Home")
	public String getHome() {
		return "StudentHome";
	}

	@GetMapping("/Grades")
	public String Enrolled(Model model, HttpSession session) {
		ArrayList<Student> Elist = new ArrayList<Student>();
		int id = stdrepo.GetidbySessionID(session.getId());
		Elist.add(stdrepo.findxByid(id));
		model.addAttribute("stdlist", Elist);
		return "StudentGrades";
	}

	@GetMapping("/Courses")
	public String listAll(Model model) {
		ArrayList<Course> clist = new ArrayList<Course>();
		clist.addAll(crsrepo.findAll());
		model.addAttribute("listCourse", clist);
		return "StudentCourse";
	}

	@GetMapping("/Details")
	public String Studentdetails(Model model, HttpSession session) {
		int id = stdrepo.GetidbySessionID(session.getId());
		ArrayList<Student> Elist = new ArrayList<Student>();
		Elist.add(stdrepo.findxByid(id));
		model.addAttribute("stdlist", Elist);
		return "StudentParticulars";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView editStudent(Model model, @PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView("StudentParticularsUpdate");
		Student s = stdrepo.findStudentByid(id);
		mav.addObject("std", s);
		return mav;
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("std") Student std) {
		stdrepo.updateDetails(std.getId(),std.getMobile(), std.getEmailID(), std.getAddress());
		//stdrepo.updateDetails(std.getId(),std.getGender(),std.getDOB(), std.getDegree(), std.getPassword(), std.getMobile(), std.getEmailID(), std.getAddress());
		return "redirect:/student/Details";
	}

	@GetMapping("/enrollCourse")
	public String StudnetEnroll(Model model) {

		model.addAttribute("StudentCourse", new StudentCourse());
		return "StudentEnrolCourse";
	}

	@PostMapping("/submit")
	public String SubmitCourse(@ModelAttribute("StudentCourse") StudentCourse StudentCourse, HttpSession session) {
		int id = stdrepo.GetidbySessionID(session.getId());
		Student s = stdrepo.findStudentByid(id);
		stdrepo.insertStudentCourse(s.getId(), StudentCourse.getCourseid(), StudentCourse.getCourseName());
		return "redirect:/student/Home";
	}

	@GetMapping("/logout")
	public ModelAndView getLogoutPage(HttpSession session) {
		session.removeAttribute("userinfo");
		session.invalidate();
		return new ModelAndView("redirect:/home");
	}
	
}
