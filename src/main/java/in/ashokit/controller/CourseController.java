package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.ashokit.entity.Course;
import in.ashokit.service.ICourseService;
import in.ashokit.utils.S3Utils;


@Controller
public class CourseController {
	
	private S3Utils s3Service;
	private ICourseService courseService;
	
	public CourseController(S3Utils s3Service, ICourseService courseService) {
		this.s3Service = s3Service;
		this.courseService = courseService;
	}

	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("course", new Course());
		return "index.html";
	}
	
	@PostMapping("/save-course")
	public String saveCourse(@ModelAttribute("course") Course course, @RequestParam("courseImageUrl") MultipartFile courseImage, Model model) {
		String putObject = s3Service.putObject(courseImage);
		course.setCourseImage(putObject);
		boolean saveCourse = courseService.saveCourse(course);
		if(saveCourse) {
			model.addAttribute("succMsg", "Course saved successfully");
		}else {
			model.addAttribute("errMsg", "Error while saving the course");
		}
		return "index";
	}
	
	
	@GetMapping("/show-courses")
	public String showCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}
}
