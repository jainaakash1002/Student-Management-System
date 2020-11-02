package com.springboot.app.Controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Model.*;
import com.springboot.app.Repository.*;

@Controller
@RequestMapping("/faculty")
@SessionAttributes("fsession")
public class FacultyController {
	
	@Autowired
	private CoursePaginationRepository cpagirepo;
	@Autowired
	private LeaveRepository leaverepo;
	private final StudentRepository sturepo;
	private CourseRepository prepo;
	private StudentPaginationRepository stupagirepo;
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	public FacultyController(StudentRepository sturepo, CourseRepository prepo, StudentPaginationRepository stupagirepo) {
		this.sturepo = sturepo;
		this.prepo = prepo;
		this.stupagirepo = stupagirepo;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder
	}

	// Master List
	@GetMapping("/masterlist")
	public String facultyHome(Model model) {
		ArrayList<Student> slist = new ArrayList<Student>();
		ArrayList<Course> clist = new ArrayList<Course>();
		slist.addAll(sturepo.findAll());
		clist.addAll(prepo.findAll());
		model.addAttribute("courses", clist);
		model.addAttribute("students", slist);
		return "masterList";
	}

	// Student's CRUD

//	@GetMapping("/listStudent")
//	public String showStudents(Model model) {
//		ArrayList<Student> slist = new ArrayList<Student>();
//		slist.addAll(sturepo.findAll());
//		model.addAttribute("students", slist);
//		return "students";
//
//	}

	// New Commit ListStudent with pagination

	@GetMapping("/listStudent")
	public ModelAndView studentPage(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("studentss");
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Student> students = stupagirepo.findAll(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(students.getTotalPages(), students.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("students", students);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("/leavedetails")
	public String leavedetails(Model model) {

		ArrayList<LeaveDetails> leave = new ArrayList<LeaveDetails>();
		leave.addAll(leaverepo.findAll());
		model.addAttribute("leave", leave);
		return "leavedetails";

	}

	@GetMapping("createLeave")
	public String createLeave(Model model) {
		LeaveDetails leave = new LeaveDetails();
		model.addAttribute("leave", leave);
		return "staffleave";
	}

	@GetMapping("/createStudent")
	public String createStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "studentform2";
	}

	@GetMapping("/saveLeave")
	public String saveLeave(@Valid @ModelAttribute LeaveDetails leave, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "staffLeave";
		}
		leaverepo.save(leave);
		return "redirect:/faculty/leavedetails";
	}

	@GetMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "studentform";
		}
		sturepo.save(student);
		return "forward:/faculty/listStudent";
	}

	@GetMapping("/editLeave/{id}")
	public String editLeave(Model model, @PathVariable("id") Integer id) {
		LeaveDetails leave = leaverepo.findById(id).get();
		model.addAttribute("leave", leave);
		return "staffLeave";
	}

	@GetMapping("/editStudent/{id}")
	public String editStudent(Model model, @PathVariable("id") String id) {
		Student student = sturepo.findById(id).get();
		model.addAttribute("student", student);
		return "studentform";

	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(Model model, @PathVariable("id") String id) {
		Student student = sturepo.findById(id).get();
		sturepo.delete(student);
		return "redirect:/faculty/listStudent";
	}

	@GetMapping("/score")
	public String scoreCard(Model model) {
		ArrayList<Student> students = new ArrayList<Student>();
		students.addAll(sturepo.findAll());
		model.addAttribute("students", students);
		return "scorecard";
	}

	@GetMapping("/edit/{id}")
	public String editScore(Model model, @PathVariable("id") String id) {
		Student student = sturepo.findById(id).get();
		model.addAttribute("student", student);
		return "editform";

	}

	@GetMapping("/saveEdit")
	public String saveEdit(@ModelAttribute Student student) {
		sturepo.updateStudent(student.getId(), student.getStudentName(), student.getGender(), student.getDOB(), student.getDegree(), student.getSemester(), student.getAddress(), student.getMobile(), student.getGpa(), student.getPassword(), student.getEmailID());
		// sturepo.updateGpa(student.getId(), student.getGpa());
		return "forward:/faculty/listStudent";
	}

	@GetMapping("/saveScore")
	public String saveScore(@ModelAttribute Student student) {
		sturepo.updateScore(student.getId(), student.getGpa());
		return "forward:/faculty/listStudent";

	}

	@GetMapping("/student/find")
	public String initfindStudent(Map<String, Object> model) {
		model.put("studentfind", new Student());
		return "findStudent";
	}

	@GetMapping("/students")
	public String studentsFind(@Valid Student student, BindingResult result, Map<String, Object> model) {
		if (student.getStudentName() == null) {
			student.setStudentName("");
		}
		ArrayList<Student> results = sturepo.findByStudentName(student.getStudentName());
		if (results.isEmpty()) {
			result.rejectValue("studentName", "not found", "not found");
			model.put("studentfind", student);
			return "redirect:/faculty/student/find";
		} else if (results.size() > 0) {
			student = results.iterator().next();
			model.put("student", student);
			return "studentDetails";
		} else {
			return "redirect:/faculty/listStudent";
		}

	}
	// Course's CRUD

	@GetMapping("/list")
	public ModelAndView listAll(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("course");
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Course> courses = cpagirepo.findAll(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(courses.getTotalPages(), courses.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("/addCourse")
	public String showAddForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "courseform2";
	}

	@GetMapping("/save")
	public String saveCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "courseform";
		}
		prepo.save(course);
		return "redirect:/faculty/list";
	}

	@GetMapping("/saveCourse")
	public String saveC(@Valid @ModelAttribute Course course, BindingResult bindingResult) {
		prepo.updateDetails(course.getCourseid(), course.getCourseName(), course.getStartDate(), course.getEndDate());
		return "redirect:/faculty/list";
	}

	@GetMapping("/editCourse/{id}")
	public String showEditForm(Model model, @PathVariable("id") String id) {
		Course course = prepo.findById(id).get();
		model.addAttribute("course", course);
		return "courseform";
	}

	@GetMapping("/deleteCourse/{id}")
	public String deleteMethod(Model model, @PathVariable("id") String id) {
		Course course = prepo.findById(id).get();
		prepo.delete(course);
		return "redirect:/faculty/list";
	}

	@GetMapping("/course/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("courseFind", new Course());
		return "findCourses";
	}

	@GetMapping("/courses")
	public String processFindForm(Course course, BindingResult result, Map<String, Object> model) {
		if (course.getCourseName() == null) {
			course.setCourseName("");
		}
//	      for (Course c : prepo.findByCourseName(course.getCourseName())) {
//				System.out.println("Course info: " + c.toString());
//			}
		ArrayList<Course> results = prepo.findByCourseName(course.getCourseName());
		if (results.isEmpty()) {
			result.rejectValue("courseName", "notFound", "not found");
			model.put("courseFind", course);
			return "findCourses";
		} else if (results.size() > 0) {
			course = results.iterator().next();
			model.put("course", course);
			return "courseDetails";
		} else {
			return "redirect:/faculty/list";
		}
	}

	@GetMapping("/course/findStudentByCourse")
	public String getStudentsByCourseName(Map<String, Object> model) {
		model.put("findStuByCourseName", new Course());
		return "findStusByCourseName";
	}

	@GetMapping("/findStudsByCN")
	public String findStudsByCourseName(Course course, BindingResult result, Model model) {
		ArrayList<Student> slist = new ArrayList<Student>();
		if (course.getCourseName() == null) {
			course.setCourseName("");
		}
//	      for (Student s : prepo.findStudentByCourseName(course.getCourseName())) {
//				System.out.println("Student info: " + s.getStudentName());
//			}
		ArrayList<Student> results = prepo.findStudentByCourseName(course.getCourseName());
		if (results.isEmpty()) {
			String s = "Course Name does not found";
			model.addAttribute("findStuByCourseName", course); // **********
			return "findStusByCourseName";
		} else {
			slist.addAll(prepo.findStudentByCourseName(course.getCourseName()));
			model.addAttribute("studentFindByCourse", slist);
			return "StusByCourseName";
		}
	}
}
