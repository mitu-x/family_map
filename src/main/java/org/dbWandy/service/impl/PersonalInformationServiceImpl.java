package org.dbWandy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dbWandy.mapper.PersonalInformationMapper;
import org.dbWandy.pojo.PersonalInformation;
import org.dbWandy.service.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Autowired
    PersonalInformationMapper personalInformationMapper;
    /**
     * 新增一个人员
     * @param p 人员信息
     * @return Boolean
     */
    @Override
    public boolean insertOne(PersonalInformation p) {
        return personalInformationMapper.insert(p) == 1;
    }

    /**
     * 根据名字查询（模糊查询）一个人的所有信息
     * @param name 姓名
     * @return PersonalInformation
     */
    @Override
    public List<PersonalInformation> getByName(String name) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.like(!Objects.equals(name, ""), PersonalInformation::getName, name);
        return personalInformationMapper.selectList(wrapper);
    }

    /**
     * 根据uuid查询一个人的所有信息
     * @param uuid uuid
     * @return PersonalInformation
     */
    @Override
    public Map<String, Object> getByUuid(String uuid) {

        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getUuid, uuid);
        Map<String, Object> map = personalInformationMapper.selectMaps(wrapper).get(0);
        //拿到父母的uuid
        String uuidFather = (String) map.get("uuid_father");
        String uuidMother = (String) map.get("uuid_mother");
        map.remove("uuid_father");
        map.remove("uuid_mother");
        //拿到兄弟姐妹的信息
        List<Map<String, Object>> bro1 = getUuidAndNameListByFatherUuid(uuidFather);
        List<Map<String, Object>> bro2 = getUuidAndNameListByMotherUuid(uuidMother);
        bro1.addAll(bro2);
        Set<Map<String,Object>> set = new HashSet<>(bro1);
        map.put("siblings",set);
        //拿到父母的信息
        List<Map<String,Object>> parent = new ArrayList<>();
        String nameF = getNameByUuid(uuidFather);
        String nameM = getNameByUuid(uuidMother);
        parent.add(toMap(uuidFather,nameF));
        parent.add(toMap(uuidMother,nameM));
        map.put("parent",parent);
        return map;
    }

    /**
     * 将 uuid name 组合成map返回
     * @param uuid uuid
     * @param name 名字
     * @return map
     */
    private Map<String,Object> toMap(Object uuid,String name){
        Map<String,Object> map = new HashMap<>();
        map.put("uuid",uuid);
        map.put("name",name);
        return map;
    }

    /**
     * 根据uuid查询一个人的名字
     * @param uuid uuid
     * @return PersonalInformation
     */
    @Override
    public String getNameByUuid(String uuid) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getUuid, uuid).select(PersonalInformation::getName);
        List<Object> list = personalInformationMapper.selectObjs(wrapper);
        return list.get(0).toString();
    }

    /**
     * 根据父亲的uuid 查询同一个父亲的所有兄弟姊妹的uuid和名字
     * @param uuid uuid
     * @return List
     */
    @Override
    public List<Map<String, Object>> getUuidAndNameListByFatherUuid(String uuid) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getUuidFather, uuid).select(PersonalInformation::getName, PersonalInformation::getUuid );
        return personalInformationMapper.selectMaps(wrapper);
    }
    /**
     * 根据母亲的uuid 查询同一个父亲的所有兄弟姊妹的uuid和名字
     * @param uuid uuid
     * @return List
     */
    @Override
    public List<Map<String, Object>> getUuidAndNameListByMotherUuid(String uuid) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getUuidMother, uuid).select(PersonalInformation::getName, PersonalInformation::getUuid );
        return personalInformationMapper.selectMaps(wrapper);
    }


    /**
     * 根据代数查询 所有同代人员信息
     * @param generation 代数
     * @return PersonalInformation
     */
    @Override
    public List<Map<String, Object>> getByGeneration(int generation) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getGeneration, generation).select(PersonalInformation::getName, PersonalInformation::getUuid );
        return personalInformationMapper.selectMaps(wrapper);
    }

    /**
     * 根据uuid查询配偶信息
     * @param uuid uuid
     * @return list
     */
    @Override
    public List<Map<String, String>> getSpousesByUuid(String uuid) {
        //创建wrapper
        LambdaQueryWrapper<PersonalInformation> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq( PersonalInformation::getUuid, uuid).select(PersonalInformation::getUuidSpouse);
        String s = personalInformationMapper.selectMaps(wrapper).get(0).toString();

        String[] split = s.split(",");
        List<Map<String, String>> l = new ArrayList<>();
        for (int i = split.length - 1; i >= 0; i--) {
            Map<String,String> map = new HashMap<>();
            String name = getNameByUuid(split[i]);
            map.put("name",name);
            map.put("uuid",split[i]);
            l.add(map);
        }
        return l;
    }

}
