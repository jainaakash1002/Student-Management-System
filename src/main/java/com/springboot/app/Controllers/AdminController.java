package com.springboot.app.Controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Model.*;
import com.springboot.app.Repository.*;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private final StudentRepository sturepo;
	private CourseRepository prepo;
	private DepartmentRepository deptpo;
	private FacultyRepository facultpo;
	private LeaveRepository leavepo;

	public AdminController(StudentRepository sturepo, CourseRepository prepo, DepartmentRepository deptpo, FacultyRepository facultpo, LeaveRepository leavepo) {
		this.sturepo = sturepo;
		this.prepo = prepo;
		this.deptpo = deptpo;
		this.facultpo = facultpo;
		this.leavepo = leavepo;
	}

	@GetMapping("/AdminHome")
	public String adminHome() {
		return "AdminHome";
	}

	@GetMapping("/listDepartment")
	public String showDepartments(Model model) {
		ArrayList<Department> dlist = new ArrayList<Department>();
		dlist.addAll(deptpo.findAll());
		model.addAttribute("department", dlist);
		return "AdminDepartment";
	}

	@GetMapping("/createDepartment")
	public String createDepartment(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "AdminDepartmentCreate";
	}

	@PostMapping("/saveDepartment")
	public String saveDepartment(@Valid @ModelAttribute Department department, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "AdminDepartmentCreate";
		}
		deptpo.save(department);
		return "redirect:/Admin/listDepartment";
	}

	@GetMapping("/editDepartment/{id}")
	public String editDepartment(Model model, @PathVariable("id") Integer id) {
		Department department = deptpo.findById(id).get();
		deptpo.delete(department);
		model.addAttribute("department", department);
		return "departmentform";

	}

	@GetMapping("/listStaff")
	public String showStaffs(Model model) {
		ArrayList<Staff> stflist = new ArrayList<Staff>();
		stflist.addAll(facultpo.findAll());
		model.addAttribute("staffs", stflist);
		return "AdminViewandCreateStaff";
	}

	@GetMapping("/createStaff")
	public String createStaff(Model model) {
		Staff staff = new Staff();
		model.addAttribute("staff", staff);
		return "AdminCreateStaff";
	}

	@PostMapping("/saveStaff")
	public String saveStaff(@Valid @ModelAttribute Staff staff, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "staffform";
		}
		Random r = new Random();
		int temp = r.nextInt(9999);
		facultpo.insertStaff(temp, staff.getName(), staff.getPassword(), staff.getRole(), staff.getDepartmentNameid());
		return "redirect:/Admin/listStaff";
	}

	@GetMapping("/editStaff/{id}")
	public String editStaff(Model model, @PathVariable("id") Integer id) {
		Staff staff = facultpo.findById(id).get();
		facultpo.delete(staff);
		model.addAttribute("staff", staff);
		return "staffform";
	}

	@GetMapping("/leaveList")
	public String showLeave(Model model) {
		ArrayList<LeaveDetails> leaveList = new ArrayList<LeaveDetails>();
		leaveList.addAll(leavepo.findAll());
		model.addAttribute("leaves", leaveList);
		return "AdminViewLeaveManagement";
	}

	@GetMapping("/editLeave/{id}")
	public String editLeave(Model model, @PathVariable("id") Integer id) {
		LeaveDetails leavetails = leavepo.findById(id).get();		
		model.addAttribute("leavedetails", leavetails);
		return "EditLeaveApplication";
	}

	@PostMapping("/saveLeave")
	public String saveLeave(@Valid @ModelAttribute LeaveDetails leaves, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "EditLeaveApplication";
		}		
		leavepo.updateLeaveStatuse(leaves.getStatus(), leaves.getLeaveId());
		return "redirect:/Admin/leaveList";
	}
	
	@GetMapping("/logout")
	public ModelAndView getLogoutPage() {
		return new ModelAndView("redirect:/home"); 
	}

}