package org.dbWandy.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;
import org.dbWandy.dao.BookExcleData;
import org.dbWandy.pojo.EmotionBook;
import org.dbWandy.service.impl.EmotionBookServiceImpl;
import org.dbWandy.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 人情簿子相关接口
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class EmotionBookController {

    @Autowired
    private EmotionBookServiceImpl emotionBookService;

    /**
     * 保存一个新的人情簿子
     *
     * @param file 人情簿子excle表
     * @return BaseResult
     */
    @PostMapping("/importExcel")
    public BaseResult<String> importExcel(MultipartFile file) {
        long date1 = System.currentTimeMillis();
        BaseResult<String> result = new BaseResult<>();
        String filename = file.getOriginalFilename();
        try {
            ReadListener<BookExcleData> listener = new ReadListener<BookExcleData>() {
                private final List<BookExcleData> cachedDataList = new ArrayList<>();
                int count;

                @Override
                public void invoke(BookExcleData data, AnalysisContext context) {
                    cachedDataList.add(data);
                    count += 1;
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    // 这里也要保存数据，确保最后遗留的数据也存储到数据库
                    saveData();
                    long l = System.currentTimeMillis() - date1;
                    String str = "成功导入" + count + "条数据，耗时" + l + "毫秒";
                    log.info(str);
                    result.setResult(str);
                }

                private void saveData() {
                    //将数据存到数据库中
                    List<EmotionBook> list = new ArrayList<>();
                    for (BookExcleData data : cachedDataList) {
                        EmotionBook emotionBook = new EmotionBook();
                        emotionBook.setBookName(filename);
                        emotionBook.setName(data.getName());
                        emotionBook.setCash(data.getCash());
                        emotionBook.setRemark(data.getRemark());
                        emotionBook.setCreateDate(new Timestamp(System.currentTimeMillis()));
                        list.add(emotionBook);
                    }
                    emotionBookService.insertBookList(list);
                    // 存储完成清理 list
                    list.clear();
                    cachedDataList.clear();
                }
            };
            EasyExcel.read(file.getInputStream(), BookExcleData.class, listener).sheet().doRead();
        } catch (Exception e) {
            result.setResult(null);
            result.setErrorCode("300");
            result.setErrorMsg("导入失败，请检查文件格式是否正确！");
            result.setSuccess(false);
        }
        return result;
    }
}
