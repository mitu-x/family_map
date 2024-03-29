package org.dbWandy.controller;

import org.dbWandy.pojo.PersonalInformation;
import org.dbWandy.service.impl.PersonalInformationServiceImpl;
import org.dbWandy.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("person")
public class PersonalInformationController {

    @Autowired
    PersonalInformationServiceImpl personalInformationService;

    /**
     * 根据uuid查询一个人的所有信息
     * @param uuid uuid
     */
    @RequestMapping("getById")
    @ResponseBody
    public BaseResult<Map<String, Object>> getById(String uuid){
        Map<String, Object> map = personalInformationService.getByUuid(uuid);
        BaseResult<Map<String, Object>> result = new BaseResult<>();
        result.setResult(map);
        return result;
    }

    /**
     * 根据uuid查询一个人的所有信息
     * @param uuid uuid
     */
    @RequestMapping("getNameById")
    @ResponseBody
    public BaseResult<Map<String,String>> getNameById(String uuid){
        String name = personalInformationService.getNameByUuid(uuid);
        BaseResult<Map<String,String>> result = new BaseResult<>();
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        result.setResult(map);
        return result;
    }

    /**
     * 根据名字查询（模糊查询）一个人的所有信息
     * @param name 姓名
     */
    @RequestMapping("getByName")
    @ResponseBody
    public void getByName(String name){
        List<PersonalInformation> list = personalInformationService.getByName(name);
        System.out.println(list);
    }




    /**
     * 新增一个人员
     * @param p 人员信息
     */
    @RequestMapping("insertOne")
    @ResponseBody
    public void insertOne(@RequestBody PersonalInformation p) {
        boolean b = personalInformationService.insertOne(p);
        System.out.println(b);
    }
}
