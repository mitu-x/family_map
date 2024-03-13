package org.dbWandy.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dbWandy.dao.BookExcleData;
import org.dbWandy.pojo.EmotionBook;
import org.dbWandy.service.EmotionBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class MyExcleListener implements ReadListener<BookExcleData> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private final List<BookExcleData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private int count;

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Autowired
    EmotionBookService emotionBookService;


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


    public int getSize() {
        return count;
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
}
