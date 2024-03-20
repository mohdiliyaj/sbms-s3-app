package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Course;
import in.ashokit.repo.CourseRepo;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	private CourseRepo courseRepo;
	
	
	@Override
	public boolean saveCourse(Course course) {
		Course save = courseRepo.save(course);
		return save.getCourseId() != null ? true : false;
	}
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
}
