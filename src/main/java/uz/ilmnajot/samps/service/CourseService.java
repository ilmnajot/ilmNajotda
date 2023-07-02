package uz.ilmnajot.samps.service;

import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.CourseDTO;

public interface CourseService {
    ApiResponse saveCourse(CourseDTO courseDTO);

    Object getCourse(Long id);
}
