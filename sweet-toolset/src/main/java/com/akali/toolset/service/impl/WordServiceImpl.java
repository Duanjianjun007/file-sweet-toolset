package com.akali.toolset.service.impl;

import com.akali.toolset.model.RestResponseObject;
import com.akali.toolset.service.WordService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: Word文档相关服务
 *
 * @author Jian-Jun Duan
 * @date 2024/5/8 15:59
 */
@Service
public class WordServiceImpl implements WordService {
    @Override
    public RestResponseObject uploadWord(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return RestResponseObject.error("上传的文件不能为空哦宝贝儿");
        }

        return RestResponseObject.ok();
    }
}
