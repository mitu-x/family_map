package org.dbWandy.controller;

import lombok.extern.slf4j.Slf4j;
import org.dbWandy.service.ExcleService;
import org.dbWandy.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 人情簿子相关接口
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class EmotionBookController {

    @Autowired
    ExcleService excleService;


    /**
     * 保存一个新的人情簿子
     * @param file 人情簿子excle表
     * @return BaseResult
     */
    @PostMapping("/importExcel")
    public BaseResult<String> importExcel(MultipartFile file) throws IOException {
        BaseResult<String> result = new BaseResult<>();
        int i = excleService.readExcle(file);
        result.setResult("成功导入" + i + "条数据");
        return result;
    }
}
