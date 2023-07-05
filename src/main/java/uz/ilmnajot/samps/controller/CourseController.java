package uz.ilmnajot.samps.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.CourseDTO;
import uz.ilmnajot.samps.entity.Course;
import uz.ilmnajot.samps.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerCourse(@RequestBody CourseDTO courseDTO) {
        ApiResponse apiResponse = courseService.saveCourse(courseDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCourse(@RequestParam Long id) {
        Course course = courseService.getCourse(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAllCourses() {
        List<Course> courseList = courseService.getCourses();
        return ResponseEntity.status(courseList != null ? 200 : 409).body(courseList);
    }
    @DeleteMapping("/{courseId}")
    public HttpEntity<?> deleteCourse(@PathVariable Long courseId){
        ApiResponse apiResponse = courseService.deleteCourse(courseId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
