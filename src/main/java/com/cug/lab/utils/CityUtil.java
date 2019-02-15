package com.cug.lab.utils;

import com.cug.lab.model.SysUser;

import java.util.ArrayList;
import java.util.List;

public class CityUtil {
    public static final String CitySTRING = "其他,北京市,天津市,河北省,山西省,内蒙古自治区,辽宁省,吉林省,黑龙江省,上海市," +
            "江苏省,浙江省,安徽省,福建省,江西省,山东省,河南省,湖北省,湖南省,广东省,海南省,广西壮族自治区,甘肃省,陕西省," +
            "新疆维吾尔自治区,青海省,宁夏回族自治区,重庆市,四川省,贵州省,云南省,西藏自治区,台湾省,澳门特别行政区," +
            "香港特别行政区,海外";

    public static final String[]  CityARRAY = CitySTRING.split(",");


    //将城市转化为list 每个元素都是city 用于前端选择
    public static List<City> getCityList(){
        List<City> list = new ArrayList<City>();
        for (int i = 0; i < CityARRAY.length; i++) {
            list.add(new City(i,CityARRAY[i]));
        }
        return list;
    }

    //将前端传回来的城市id转化为城市名称
    public static void SetCityIdToString(SysUser sysUser){
        sysUser.setUserAddress(CityARRAY[Integer.parseInt(sysUser.getUserAddress())]);
    }
}
