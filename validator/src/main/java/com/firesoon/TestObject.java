package com.firesoon;

import com.firesoon.util.MapSortUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestObject {
    public static void main(String[] args) {
//        Map<String,String> errString = new HashMap<>();
//        errString.put("noneZd","111+【一、缺失；1.病案缺失；1.1主诊断缺失】");
//        errString.put("noneSs","112+【一、缺失；1.病案缺失；1.2主手术缺失】");
//        errString.put("noneCzd","113+【一、缺失；1.病案缺失；1.3次诊断缺失】");
//        errString.put("noneSex","131+【一、缺失；3.基本信息缺失；1.1性别】");
//        errString.put("noneAge","132+【一、缺失；3.基本信息缺失；1.2年龄】");
//        errString.put("noneXzets","133+【一、缺失；3.基本信息缺失；1.3新生儿体重】");
//        errString.put("errZd","211+【二、取错；1.病案取错；1.1主诊断取错】");
//        errString.put("errSs","212+【二、取错；1.病案取错；1.2主手术取错】");
//        errString.put("owenCode","321+【三、编码；2.院内自行维护的编码；2.1医院自行维护的编码】");
//        errString.put("errWtoN","511+【五、分组错误；1.ADRG分错；1.1应该是外科操作结果入内科】");
//        errString.put("errNtoW","512+【五、分组错误；1.ADRG分错；1.2应该是内科操作结果入外科】");
//        errString.put("errNandunMdc","513+【五、分组错误；1.ADRG分错；1.3都是内科，不同MDC】");
//        errString.put("errWandunMdc","514+【五、分组错误；1.ADRG分错；1.4都是外科操作，不同MDC】");
//        errString.put("errHbz","521+【五、分组错误；2.合并症分错；1.1合并症分错】");
//        errString.put("unSpecial","621+【六、未分组；2.病组特殊条件没满足；1.1病组特殊条件没满足】");
//
//        System.out.println(errString);
//        for (String key:errString.keySet()){
//            System.out.println(errString.get(key));
//        }
//        System.out.println("--------------------------------");
//        System.out.println(MapSortUtil.sortByValueAsc(errString));
//        Map<String, String> stringStringMap = MapSortUtil.sortByValueAsc(errString);
////        Set<String> strings = stringStringMap.keySet();
////        Iterator<String> iterator = strings.iterator();
////        while (iterator.hasNext()){
////            String key = iterator.next();
////            System.out.println(errString.get(key));
////        }
//        for (String key:stringStringMap.keySet()){
//            System.out.println(stringStringMap.get(key));
//        }

        String s = "ty_wlryy_202002";
        s = s.split("\\_")[0]+"_"+s.split("\\_")[1];
        System.out.println(s);
    }
}
