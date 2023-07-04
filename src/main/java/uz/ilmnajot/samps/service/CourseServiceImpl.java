package uz.ilmnajot.samps.service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.CourseDTO;
import uz.ilmnajot.samps.entity.Course;
import uz.ilmnajot.samps.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ApiResponse saveCourse(CourseDTO courseDTO) {
        Optional<Course> optionalCourse = courseRepository.findByName(courseDTO.getName());
        if (optionalCourse.isPresent()) {
            return new ApiResponse("you have already registered to this course", false);
        }
        Course course = new Course(
                courseDTO.getName(),
                courseDTO.getDescription(),
                courseDTO.getPrice(),
                courseDTO.getAttachment()
        );
        courseRepository.save(course);
        return new ApiResponse("you have registered to this course", true);
    }


    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public ApiResponse deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);

    }

    @Override
    public Course getCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        try {
            if (optionalCourse.isPresent()) {
                return optionalCourse.get();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
