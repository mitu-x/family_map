package org.dbWandy.service;

import org.dbWandy.pojo.PersonalInformation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PersonalInformationService {

    /**
     * 新增一个人员
     * @return Boolean
     */
     boolean insertOne(PersonalInformation p);

    /**
     * 根据名字查询（模糊查询）一个人的所有信息
     * @param name 姓名
     * @return PersonalInformation
     */
    List<PersonalInformation> getByName(String name);

    /**
     * 根据uuid查询一个人的所有信息
     * @param uuid uuid
     * @return Map
     */
    Map<String, Object> getByUuid(String uuid);

     /**
     * 根据uuid查询一个人的所有信息
     * @param uuid uuid
     * @return PersonalInformation
     */
     String getNameByUuid(String uuid);

    /**
     * 查询father_uuid为uuid的所有人员的uuid和名字
     * @param uuid uuid
     * @return List
     */
    List<Map<String, Object>> getUuidAndNameListByFatherUuid(String uuid);
    /**
     * 查询mother_uuid为uuid的所有人员的uuid和名字
     * @param uuid uuid
     * @return List
     */
    List<Map<String, Object>> getUuidAndNameListByMotherUuid(String uuid);

    /**
     * 根据代数查询 所有同代人员信息
     * @param generation 代数
     * @return PersonalInformation
     */
    List<Map<String, Object>> getByGeneration(int generation);

    /**
     * 根据uuid查询配偶信息
     * @param uuid uuid
     * @return list
     */
    List<Map<String, String>> getSpousesByUuid(String uuid);
}
