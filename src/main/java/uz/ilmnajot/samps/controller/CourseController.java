package uz.ilmnajot.samps.controller;

import jakarta.persistence.Lob;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.CourseDTO;
import uz.ilmnajot.samps.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerCourse(@RequestBody CourseDTO courseDTO){
        ApiResponse apiResponse = courseService.saveCourse(courseDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getCourse(@RequestParam Long id){
        Object course = courseService.getCourse(id);
        return ResponseEntity.ok(course);
    }

}
