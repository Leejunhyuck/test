package org.motivators.wavy_project.user.controller.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/exception")
public class ExceptionController {

    // @GetMapping(value = "/entrypoint")
    // public CommonResult entrypointException() {
    //     throw new CAuthenticationEntryPointException();
    // }

    // @GetMapping(value = "/accessdenied")
    // public CommonResult accessdeniedException() {
    //     throw new AccessDeniedException("");
    // }
    

}