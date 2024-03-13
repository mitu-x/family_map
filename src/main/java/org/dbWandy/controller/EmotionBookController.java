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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                @Override
                public void invoke(BookExcleData data, AnalysisContext context) {
                    cachedDataList.add(data);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    // 这里也要保存数据，确保最后遗留的数据也存储到数据库
                    Integer integer = saveData();
                    long l = System.currentTimeMillis() - date1;
                    String str = "成功导入" + integer + "条数据，耗时" + l + "毫秒";
                    log.info(str);
                    result.setResult(str);
                }

                private Integer saveData() {
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
                    Integer integer = emotionBookService.insertBookList(list);
                    // 存储完成清理 list
                    list.clear();
                    cachedDataList.clear();
                    return integer;
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

    /**
     * 根据book Name名查询
     *
     * @param bookName 文件名称
     * @return BaseResult
     */
    @RequestMapping("getByBookName")
    public BaseResult<List<EmotionBook>> getByBookName(String bookName) {
        BaseResult<List<EmotionBook>> result = new BaseResult<>();
        List<EmotionBook> list = emotionBookService.getAllByBookName(bookName);
        result.setResult(list);
        return result;
    }

    /**
     * 根据name名查询
     *
     * @param name 人名
     * @return BaseResult
     */
    @RequestMapping("getListByName")
    public BaseResult<Map<String, String>> getListByName(String name) {
        BaseResult<Map<String, String>> result = new BaseResult<>();
        EmotionBook emotionBook = emotionBookService.getListByName(name).get(0);
        Map<String, String> map = new HashMap<>();
        map.put("bookName", emotionBook.getBookName());
        map.put("name", emotionBook.getName());
        map.put("cash", emotionBook.getCash() + "");
        //map.put("createDate", emotionBook.getCreateDate() + "");
        result.setResult(map);
        return result;
    }

    /**
     * 根据book Name名删除
     *
     * @param bookName 文件名
     * @return BaseResult
     */
    @RequestMapping("deleteByBookName")
    public BaseResult<Integer> deleteByBookName(String bookName) {
        BaseResult<Integer> result = new BaseResult<>();
        Integer i = emotionBookService.deleteByBookName(bookName);
        result.setResult(i);
        result.setErrorMsg("删除" + i + "条数据成功");
        return result;
    }

}
