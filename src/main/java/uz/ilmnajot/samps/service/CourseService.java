package uz.ilmnajot.samps.service;

import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.CourseDTO;
import uz.ilmnajot.samps.entity.Course;

import java.util.List;

public interface CourseService {
    ApiResponse saveCourse(CourseDTO courseDTO);

    Course getCourse(Long id);

    List<Course> getCourses();

    ApiResponse deleteCourse(Long courseId);
}
