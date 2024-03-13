package org.dbWandy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ExcleService {
    /**
     * 读取excle文件
     * @param file 传入文件
     * @return int 返回读取的行数
     * @throws IOException 文件读取错误
     * @author mitu
     */
    int readExcle(MultipartFile file) throws IOException;


}
