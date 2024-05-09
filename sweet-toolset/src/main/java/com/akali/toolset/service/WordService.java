package com.akali.toolset.service;

import com.akali.toolset.model.RestResponseObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author Jian-Jun Duan
 * @date 2024/5/8 15:54
 */
public interface WordService {
    RestResponseObject uploadWord(MultipartFile file);
}
