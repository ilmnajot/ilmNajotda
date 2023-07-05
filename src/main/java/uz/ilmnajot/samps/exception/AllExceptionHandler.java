package uz.ilmnajot.samps.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler {


    /*
    here what we are doing;
    1. this class called "AllExceptionHandler" is MAIN class
    2. WEBREQIEST is that when we request by client or by postman, this can let us know who is requesting in the system
     */
    @ExceptionHandler(CourseNotFoundException.class)
    public HttpEntity<?> handleCourseNotFoundException(
            CourseNotFoundException courseNotFoundException,
            WebRequest webRequest
    ){
            ErrorDetails errorDetails =new ErrorDetails(
                    new Date(),
                    courseNotFoundException.getMessage(),
                    webRequest.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    //bu yerda Webrequest nima uchun?

    //bu kirib kelayotgan request yani postmandan so'rov berasiku shu aynan kim bergan bo'lsa shunga xatolik qaytarish uchun bu narsa
    // tushunarli!

    // ustoz boya biz username orqali kimligini bildik, shu ko'p hollada shunaqa qilinadimi yoki id yaxshiroqmi?
    // holatga qarab ko'pincha username unique bo'ladi va bir marta beriladi sistemaga kirganda shu uchun username olgan yaxshi
    // logikani qanqa qilish o'zingizga bog'liq
    // username email bo'lgandami? yani agar username ni name qilsak bo'lmasdan qolarkanda shunaqami ha shunday

    //KATTA RAHMAT HAMMASI TUSHUNARLI.okkkkk bosing to'xtamang
}
