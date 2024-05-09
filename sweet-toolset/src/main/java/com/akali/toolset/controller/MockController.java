package com.akali.toolset.controller;

import com.akali.toolset.model.RestResponseObject;
import com.akali.toolset.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author Jian-Jun Duan
 * @date 2024/5/8 15:29
 */
@Slf4j
@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private WordService wordService;

    @PostMapping("/uploadWord")
    public RestResponseObject uploadWord(@RequestParam("file") MultipartFile file) {
        if (log.isInfoEnabled()) {
            log.info("receive word file upload request, filename: {}", file.getOriginalFilename());
        }

        return wordService.uploadWord(file);
    }
}
