package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Course;

public interface ICourseService {
	
	public boolean saveCourse(Course course);
	
	public List<Course> getAllCourses();
}
