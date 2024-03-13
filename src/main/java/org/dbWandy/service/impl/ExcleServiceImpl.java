package org.dbWandy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.dbWandy.dao.BookExcleData;
import org.dbWandy.pojo.EmotionBook;
import org.dbWandy.service.EmotionBookService;
import org.dbWandy.service.ExcleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExcleServiceImpl implements ExcleService {

    @Autowired
    EmotionBookService emotionBookService;

    /**
     * 读取excle文件
     * @param file 传入文件
     * @return EmotionBook 返回一个对象，包含excle中每一行数据
     * @author mitu
     */
    @Override
    public int readExcle(MultipartFile file) throws IOException {
        int count = 0;
        int BATCH_COUNT = 100;
        String filename = file.getOriginalFilename();
        ReadListener<BookExcleData> listener = new ReadListener<BookExcleData>() {

            private final List<BookExcleData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


            /**
             * 这个每一条数据解析都会来调用
             * @param data 一行数据
             */
            @Override
            public void invoke(BookExcleData data, AnalysisContext context) {
                log.info("解析到一条数据:{}", JSON.toJSONString(data));
                cachedDataList.add(data);
                count += 1;
                // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList.clear();
                }

            }

            /**
             * 所有数据解析完成了 都会来调用
             */
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
                // 这里也要保存数据，确保最后遗留的数据也存储到数据库
                log.info("所有数据解析完成！");
            }

            private void saveData() {
                //将数据存到数据库中
                log.info("{}条数据开始存储数据库！", cachedDataList.size());
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
                int i = emotionBookService.insertBookList(list);
                System.out.println(i);
                // 存储完成清理 list
                list.clear();
                cachedDataList.clear();
                log.info("存储数据库成功！");
            }
        };
        EasyExcel.read(file.getInputStream(), BookExcleData.class, listener).sheet().doRead();
        return count;
    }
}
