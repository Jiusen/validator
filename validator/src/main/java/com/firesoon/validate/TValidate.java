package com.firesoon.validate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.firesoon.calibrator.pojo.Sign;
import com.firesoon.pojo.*;
import com.firesoon.reader.DiagExcelReader;
import com.firesoon.reader.SignExcelReader;
import com.firesoon.reader.SurgExcelReader;
import com.firesoon.writer.ExcelWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TValidate {

    private static final Logger logger = Logger.getLogger(TValidate.class);
    // 每个地区的错误项个数
    private static  Map<String,Map<String,Integer>> sourceErrFeeMap = new HashMap<>();
    // 每个地区的错误类型个数
    private static  Map<String,Map<String,Integer>> sourceMisTypeMap = new HashMap<>();
    // 每个地区的缺失项参数个数
    private static  Map<String,Map<String,Integer>> sourceMisFeeMap = new HashMap<>();
    // 每个地区的总病例数
    private static Map<String,Integer> sourceTotal = new HashMap<>();
    // 费用项缺失
    private static Map<String,Integer> misFeeItem;
    // 费用项遗漏或多余
    private static Map<String,Integer> errFeeItem;
    // 错误类型
    private static Map<String,Integer> misType;

    private static Map<String,String> signMap = new HashMap<>();


    public static Map<String, Map<String, Integer>> getSourceErrFeeMap() {
        return sourceErrFeeMap;
    }

    public static void setSourceErrFeeMap(Map<String, Map<String, Integer>> sourceErrFeeMap) {
        TValidate.sourceErrFeeMap = sourceErrFeeMap;
    }

    public static Map<String, Map<String, Integer>> getSourceMisTypeMap() {
        return sourceMisTypeMap;
    }

    public static void setSourceMisTypeMap(Map<String, Map<String, Integer>> sourceMisTypeMap) {
        TValidate.sourceMisTypeMap = sourceMisTypeMap;
    }

    public static Map<String, Map<String, Integer>> getSourceMisFeeMap() {
        return sourceMisFeeMap;
    }

    public static void setSourceMisFeeMap(Map<String, Map<String, Integer>> sourceMisFeeMap) {
        TValidate.sourceMisFeeMap = sourceMisFeeMap;
    }


    public static Map<String, Integer> getSourceTotal() {
        return sourceTotal;
    }

    public static void setSourceTotal(Map<String, Integer> sourceTotal) {
        TValidate.sourceTotal = sourceTotal;
    }


    // 初始化 MisTye
    public static Map<String,Integer> initMisType(Map<String,Integer> misType){
        misType.put("noneZd",0);// 缺失主诊断
        misType.put("noneSs",0);// 缺失主手术
        misType.put("noneSex",0);// 缺失性别
        misType.put("noneAge",0);// 缺失年龄
        misType.put("noneXzets",0);//缺失体重
        misType.put("errZd",0); // 主诊断取错
        misType.put("errSs",0); // 主手术取错
        misType.put("owenCode",0); // 院内自行维护编码
        misType.put("errWtoN",0); // 外科分入内科
        misType.put("errNtoW",0); // 内科分入外科
        misType.put("errNandunMdc",0); // 都是内科，不同MDC
        misType.put("errWandunMdc",0);
        //misType.put("errHbz",0); // 合并症分错
        misType.put("unSpecial",0); // 未分组 - 特殊情况
        misType.put("noneCzd",0); // 次诊断缺失
        misType.put("err1",0);
        misType.put("err2",0);
        misType.put("err3",0);
        misType.put("err4",0);
        misType.put("err5",0);
        misType.put("err6",0);
        misType.put("err7",0);
        return misType;
    }
    // 初始化 errFeeItem
    public static Map<String,Integer> initErrFeeMap(Map<String,Integer> errFeeItem){
        // 初始化 errFeetItem
        errFeeItem.put("us",0);
        errFeeItem.put("breakStone",0);
        errFeeItem.put("ercp",0);
        errFeeItem.put("singlerfqc",0);
        errFeeItem.put("doublerfqc",0);
        errFeeItem.put("csinglethyroi",0);
        errFeeItem.put("cdoublethyroid",0);
        errFeeItem.put("singlethyroi",0);
        errFeeItem.put("doublethyroid",0);
        errFeeItem.put("dialysis",0);
        errFeeItem.put("kffy",0);
        errFeeItem.put("kfsj",0);
        errFeeItem.put("icu",0);
        errFeeItem.put("vetime",0);
        return errFeeItem;
    }




    // 初始化费用项缺失
    public static Map<String,Integer> initMisFeeMap(List<String> expenseItem,Map<String,Integer> misFeeItem){
        // 初始化 费用项缺失
        for (String str:expenseItem){
            misFeeItem.put(str,0);
        }
        return misFeeItem;
    }
    public static void writerResult(List list, String resFilePath){
        if (resFilePath.equalsIgnoreCase("") || resFilePath == null || resFilePath.length() == 0){
            resFilePath = "D:\\result"; // 默认输出目录
        }
        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(list);
        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = resFilePath+"\\Tai-zhou_errorResult.xlsx";

            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }
            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            System.out.println("结果保存位置："+exportFilePath);
        } catch (Exception e) {
            //System.out.println("输出excel error:"+e.getMessage());
            logger.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                logger.warn("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
    }

    /*
           读取 结算单 csv 文件
     */
    public static List<Settlement> readSettlementCsvFile(String fileName) throws IOException {
        // 如果读入的结算但为空
        if (fileName == null || fileName.length()<=0){
            return null;
        }
        DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
        BufferedReader br= new BufferedReader(new InputStreamReader(in,"GBK"));
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(br);
        List<Settlement> list = new ArrayList<Settlement>();

        for (CSVRecord record : records) {
            Settlement s = new Settlement(record.get(0),record.get("PID"),record.get("JS_SURG_CODE"),record.get("JS_SURG_NAME"),record.get("JS_DIAG_CODE"),record.get("JS_DIAG_NAME"),
                    record.get("REASON"));
            list.add(s);
        }
        return list;
    }

    /*
        读取 分组参数 csv文件
     */
    public static List<GroupingParam> readGroupingParamCsvFile(String fileName) throws IOException {
        // 如果读入的结算但为空
        if (fileName == null || fileName.length()<=0){
            return null;
        }
        DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
        BufferedReader br= new BufferedReader(new InputStreamReader(in,"GBK"));
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(br);
        List<GroupingParam> list = new ArrayList<>();

        for (CSVRecord record : records) {
            GroupingParam s = new GroupingParam(record.get("MONTH"),record.get("PID"),record.get("BILL_DRGCODE"),record.get("SYS_DRGCODE"),record.get("REQUEST_JSON"),
                    record.get("SOURCE"));
            String source = record.get("SOURCE");
            if (TValidate.getSourceTotal().get(source)!=null){
                TValidate.getSourceTotal().put(source,TValidate.getSourceTotal().get(source)+1);
            }else{
                TValidate.getSourceTotal().put(source,1);
            }
            list.add(s);
        }
        return list;
    }

    // 转换pid编码
    public static String transforPid(String pid,String hospital){
        String tempPid = "";
        hospital = hospital.split("\\_")[0] + "_" + hospital.split("\\_")[1];
        if ("tz_ezyyezyq".equals(hospital)) {
            tempPid = pid + "EZYY";
        } else if ("tz_lhseyy".equals(hospital)) {
            tempPid = pid + "lhseyy";
        } else if ("tz_lhsyyy".equals(hospital)) {
            tempPid = pid + "LHSYYY";
        } else if ("tz_lhzyy".equals(hospital)) {
            tempPid = pid + "lhsz";
        } else if ("tz_lqderyy".equals(hospital)) {
            tempPid = pid + "LQEY";
        } else if ("tz_slyy".equals(hospital)) {
            tempPid = pid + "TZSL";
        } else if ("tz_tzyy".equals(hospital)) {
            tempPid = pid + "TZYY";
        } else if ("tz_wlrmyy".equals(hospital)) {
            tempPid = pid + "wlyy";
        } else if ("tz_zlyy".equals(hospital)) {
            tempPid = pid + "TZZLYY";
        } else if ("tz_zxyyy".equals(hospital)) {
            tempPid = pid + "zxy";
        } else if ("tz_zyyy".equals(hospital)) {
            tempPid = pid + "01";
        } else {
            tempPid = "";
        }
        return tempPid;
    }
    // 获取Request_json中所有的key
    public static ArrayList<String> getAllKey(String jsonStr) {
        ArrayList<String> keySet = new ArrayList<String>();

        jsonStr = "["+jsonStr+"]";
        JSONArray excelArray = JSON.parseArray(jsonStr);
        for(int i=0;i<excelArray.size();i++) {
            JSONObject jsonObject = excelArray.getJSONObject(i);
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                keySet.add(key);
            }
        }
        return keySet;
    }

    // 检查基本信息
    public static void checkBaseInfo(GroupingParam group){
        if (group.getItem().getSex() == null || group.getItem().getSex().equals("") || group.getItem().getSex().length()==0){
            group.getErrorReason().append("3.1 性别为空\n");
            sourceMisTypeMap.get(group.getSource()).put("noneSex",
                    sourceMisTypeMap.get(group.getSource()).get("noneSex")+1);
           // misType.put("noneSex",misType.get("noneSex")+1);
        }
        if (group.getItem().getAge().compareTo(0.0) == 0 || group.getItem().getAge().equals("")){
            group.getErrorReason().append("3.2 年龄为空\n");
            sourceMisTypeMap.get(group.getSource()).put("noneAge",
                    sourceMisTypeMap.get(group.getSource()).get("noneAge")+1);
            // misType.put("noneAge",misType.get("noneAge")+1);
        }
        if (((group.getItem().getXsetz()==null || group.getItem().getXsetz().compareTo(0.0) == 0) && group.getItem().getAge().compareTo(1.0)<0)){
            group.getErrorReason().append("3.3 新生儿年龄<=1,且体重为空\n");
            sourceMisTypeMap.get(group.getSource()).put("noneXzets",
                    sourceMisTypeMap.get(group.getSource()).get("noneXzets")+1);
            // misType.put("noneXzets",misType.get("noneXzets")+1);
        }
    }

    public static long readFileAndCheck(String groupFilePath, String settlementFilePath, String resFilePath){
        System.out.println("台州 - 校验开始......");
        long timebegin = System.currentTimeMillis();
        // 分组参数表
        List<GroupingParam> groupResult = null;
        // 结算表
        List<Settlement> settlementResult = null;
        try {
            groupResult = readGroupingParamCsvFile(groupFilePath);
            settlementResult = readSettlementCsvFile(settlementFilePath);
        } catch (IOException e) {
            logger.error("读取csv文件出错："+e.getMessage());
      }

        // diag表
        List<Diag> diagResult = DiagExcelReader.readExcel("diag_version_20200506.xlsx");
        // surg表
        List<Surg> surgResult = SurgExcelReader.readExcel("surg_version_20200506.xlsx");
        // 台州标注表
        List<Sign> signResult = SignExcelReader.readExcel("台州标记表.xlsx");

        // 初始化标注表Map
        for (Sign sign:signResult){
            signMap.put(sign.getDrgCode(),sign.getCc());
        }

        // 进行校验
        check(groupResult,settlementResult,diagResult,surgResult);
        // 写入文件
        writerResult(groupResult,resFilePath);
        long timeAfter = System.currentTimeMillis();
        System.out.println("台州 - 校验结束，总耗时："+(timeAfter-timebegin));
        return (timeAfter-timebegin);
    }

    // 校验
    private static void check(List<GroupingParam> groupResult, List<Settlement> settlementResult, List<Diag> diagResult, List<Surg> surgResult) {
//        Map<String,Map<String,Integer>> sourceExpenseMap = new HashMap<>();
//        Map<String,Map<String,Integer>> sourceMisTypeMap = new HashMap<>();
//        Map<String,Map<String,Integer>> sourceMisFeeMap = new HashMap<>();
        Map<String,Settlement> settlementMap = new HashMap<String, Settlement>();
        ArrayList<String> expenseItem = new ArrayList<String>();
        Map<String,Diag> diagMap = new HashMap<String, Diag>();
        Map<String,Surg> surgMap = new HashMap<String, Surg>();
        // 结算但为空的情况
        if (settlementResult != null) {
            for (Settlement settlement : settlementResult) {
//                if (settlement.getHosId().contains("IP"))
//                    settlement.setHosId("IP" + settlement.getHosId().split("IP")[1]);
                // 转换 pid
                settlement.setHosId(settlement.getHosId().toLowerCase());
                settlementMap.put(settlement.getHosId(), settlement);
            }
        }
        for (Diag diag:diagResult){
            if (diag.getVersion().equals("《国家临床2.0版本》")) {
                diagMap.put(diag.getMainDiagCode().split("\\+")[0].toLowerCase(), diag);
            }
        }
        for (Surg surg:surgResult){
            if (surg.getVersion().equals("《国家临床2.0版本》")){
                surgMap.put(surg.getSurgCode().split("\\+")[0].toLowerCase(),surg);
            }
        }
        expenseItem.add("us");
        expenseItem.add("breakstone");
        expenseItem.add("ercp");
         //  expenseItem.add("rfqc"); // 备注 作废
        expenseItem.add("singlerfqc");
        expenseItem.add("doublerfqc");
        expenseItem.add("csinglethyroi");
        expenseItem.add("cdoublethyroid");
        expenseItem.add("singlethyroi");
        expenseItem.add("doublethyroid");

        expenseItem.add("dialysis");
        // expenseItem.add("rfxq");// 备注：作废
        expenseItem.add("kffy");
        expenseItem.add("kfsj");
        expenseItem.add("icu");
        expenseItem.add("vetime");

        // 按照区域划分
        for (GroupingParam g: groupResult){
            if (sourceErrFeeMap.get(g.getSource())==null) {
                // 费用项取错
                errFeeItem = new HashMap<>();

                sourceErrFeeMap.put(g.getSource(),initErrFeeMap(errFeeItem));
                // 错误类型
                misType = new HashMap<>();
                sourceMisTypeMap.put(g.getSource(), initMisType(misType));
                // 费用项缺失
                misFeeItem = new HashMap<>();
                sourceMisFeeMap.put(g.getSource(), initMisFeeMap(expenseItem,misFeeItem));
                // 医院总病例数
            }
        }

        for (GroupingParam group:groupResult) {
            if (group.getBillDra().equalsIgnoreCase(group.getSysDrg())){
                continue;
            }
            // 一、缺失
            checkDefect(group, settlementMap, expenseItem);
            // 二、取错
            checkMistake(group, settlementMap,diagMap,surgMap);
            // 三、医院自行维护编码
            checkCode(group, diagMap, surgMap);
            // 四、分组错误
            checkGroup(group);
            // 五、 未分组
            checkUnGroup(group);
        }
    }

    // 错误类型五、未分组的情况 - 只考虑病组特殊条件没有满足
    private static void checkUnGroup(GroupingParam group) {
        // 未分组
            if (group.getBillDra().equalsIgnoreCase("ZR19") && (!((group.getItem().getDaynum()<5 && group.getItem().getLyfs()==5) || group.getItem().getLyfs() == 2))) {
                group.getErrorReason().append("1.1 病组特殊条件没满足\n");
                sourceMisTypeMap.get(group.getSource()).put("unSpecial",
                        sourceMisTypeMap.get(group.getSource()).get("unSpecial")+1);
               // misType.put("unSpecial",misType.get("unSpecial")+1);
            }
    }

    // 错误类型四、 检查分组是否错误
    private static void checkGroup(GroupingParam group) {

        String billCode = group.getBillDra();
        String sysCode = group.getSysDrg();
        if (sysCode == null || sysCode.equalsIgnoreCase("") || sysCode.length()<4){
            group.getErrorReason().append("0.0 系统分组为空\n");
            return;
        }
        int billGro = testGroup(billCode.toUpperCase().charAt(1));
        int sysGro = testGroup(sysCode.toUpperCase().charAt(1));
        int bilMdc = billCode.toUpperCase().charAt(0);
        int sysMdc = sysCode.toUpperCase().charAt(0);
        if (billGro==0 || sysGro == 0){
            logger.error("DRG编码有误");
        }
        // ADRG分错
        if (billGro == 1 && sysGro == 3){
            group.getErrorReason().append("1.1 ADRG分组错误，外科分入内科,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
           // misType.put("errWtoN",misType.get("errWtoN")+1);
            sourceMisTypeMap.get(group.getSource()).put("errWtoN",
                    sourceMisTypeMap.get(group.getSource()).get("errWtoN")+1);
        }
        if (billGro == 3 && sysGro == 1){
            group.getErrorReason().append("1.2 ADRG分组错误，内科分外科,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
            //misType.put("errNtoW",misType.get("errNtoW")+1);
            sourceMisTypeMap.get(group.getSource()).put("errNtoW",
                    sourceMisTypeMap.get(group.getSource()).get("errNtoW")+1);
        }
        if(billGro == 3 && sysGro == 3 && bilMdc!=sysMdc){
            group.getErrorReason().append("1.3 ADRG分组错误，都是内科，不同MDC,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
            //misType.put("errNandunMdc",misType.get("errNandunMdc")+1);
            sourceMisTypeMap.get(group.getSource()).put("errNandunMdc",
                    sourceMisTypeMap.get(group.getSource()).get("errNandunMdc")+1);
        }
        if (billGro == 1 && sysGro == 1 && bilMdc!=sysMdc){
            group.getErrorReason().append("1.4 ADRG分组错误,都是外科，不同MDC,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
            //misType.put("errWandunMdc",misType.get("errWandunMdc")+1);
            sourceMisTypeMap.get(group.getSource()).put("errWandunMdc",
                    sourceMisTypeMap.get(group.getSource()).get("errWandunMdc")+1);
        }
        // 合并症分错 - 前三位一样，最后一位不一样

        //合并症分错
        if(group.getBillDra().length()>0 && group.getSysDrg().length() > 0)
        {
            String billCc = signMap.get(group.getBillDra());
            String sysCc = signMap.get(group.getSysDrg());
            if (billCc == null) billCc = "";
            if (sysCc == null)  sysCc = "";
            if( (billCc.length()<=0 && sysCc.length()>0) || (billCc.length()>0 && sysCc.length()<=0))
            {
                group.getErrorReason().append("2.1 合并症分错,2.7其他,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err7",
                        sourceMisTypeMap.get(group.getSource()).get("err7")+1);
                return;
            }

            if(billCc.equals("2") && sysCc.equals("1"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.1应该是重要结果分到一般,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err1",
                        sourceMisTypeMap.get(group.getSource()).get("err1")+1);
                return;
            }
            if(billCc.equals("2") && sysCc.equals("0"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.2应该是重要结果分到不伴,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err2",
                        sourceMisTypeMap.get(group.getSource()).get("err2")+1);
                return;
            }
            if(billCc.equals("1") && sysCc.equals("2"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.3应该是一般结果是重要,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err3",
                        sourceMisTypeMap.get(group.getSource()).get("err3")+1);
                return;
            }
            if(billCc.equals("0") && sysCc.equals("2"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.4应该是不伴结果是重要,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err4",
                        sourceMisTypeMap.get(group.getSource()).get("err4")+1);
                return;
            }
            if(billCc.equals("1") && sysCc.equals("0"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.5应该是一般结果是不伴,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err5",
                        sourceMisTypeMap.get(group.getSource()).get("err5")+1);
                return;
            }

            if(billCc.equals("0") && sysCc.equals("1"))
            {
                group.getErrorReason().append("2.1 合并症分错,2.6应该是不伴结果是一般"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
                //misType.put("errHbz",misType.get("errHbz")+1);
                sourceMisTypeMap.get(group.getSource()).put("err6",
                        sourceMisTypeMap.get(group.getSource()).get("err6")+1);
                return;
            }

        }

//        if (billCode.substring(0,3).equalsIgnoreCase(sysCode.substring(0,3)) &&
//                !billCode.substring(3).equalsIgnoreCase(sysCode.substring(3))){
//            group.getErrorReason().append("2.1 合并症分组分错,"+"BILL_DrgCode:"+billCode+" , "+" SYS_DrgCode:"+sysCode+"\n");
//            //misType.put("errHbz",misType.get("errHbz")+1);
//            sourceMisTypeMap.get(group.getSource()).put("errHbz",
//                    sourceMisTypeMap.get(group.getSource()).get("errHbz")+1);
//        }
    }
    // 判断 内科 外科
    private static int testGroup(char c){
        if (c >= 'A' && c <= 'J'){
            return 1; // 外科
        }else if (c >= 'K' && c <= 'Q'){
            return 2; // 非手术室
        }else if (c >= 'R' && c <= 'Z'){
            return 3; // 内科
        }
        return 0;
    }
    // 错误类型三、医院自行维护编码
    private static void checkCode(GroupingParam group, Map<String, Diag> diagMap, Map<String, Surg> surgMap) {
        Item item = group.getItem();
        boolean bzd=false;
        // - 主诊断
        if ( !(item.getZdcode().equals("") || item.getZdcode().length()<=0 || item.getZdcode() == null)){
            if (diagMap.get(item.getZdcode().split("\\+")[0].toLowerCase()) == null){
                group.getErrorReason().append("1.1 院内自行维护编码，主诊断编码："+ item.getZdcode()+"\n");
                bzd=true;
            }
        }
        // - 次诊断
        ArrayList<String> czdcode = item.getCzdcode();
        ArrayList<String> unCzdCode = new ArrayList<>();
        boolean bczd = false;
        if (czdcode != null && czdcode.size()>0) {
            for (String czd : czdcode) {
                if (diagMap.get(czd.split("\\+")[0].toLowerCase()) == null) {
                    bczd = true;
                    unCzdCode.add(czd);
                }
            }
            if (bczd == true){
                group.getErrorReason().append("1.1 院内自行维护编码，次诊断编码:"+unCzdCode.toString()+"\n");
            }
        }

        // 国家临床2.0 版本 -
        // - 主手术
        boolean bss = false;
        if (item.getSscode() != null && !item.getSscode().equals("") && item.getSscode().length()>0) {
            if (surgMap.get(item.getSscode().split("\\+")[0].toLowerCase().toLowerCase()) == null) {
                group.getErrorReason().append("1.1 院内自行维护编码，主手术编码：" +  item.getSscode() + "\n");
                bss = true;
            }
        }
        // - 次手术
        ArrayList<String> csscode = item.getCsscode();
        ArrayList<String> uncssCode = new ArrayList<>();
        boolean bcss = false;
        if (csscode != null && csscode.size()>0) {
            for (String css : csscode) {
                if (surgMap.get(css.split("\\+")[0].toLowerCase()) == null) {
                    bcss = true;
                    uncssCode.add(css);
                }
            }
            if (bcss == true){
                group.getErrorReason().append("1.1 院内自行维护编码，次手术编码：" + uncssCode.toString() + "\n");
            }
        }
        if (bzd || bczd || bss || bcss){
            //misType.put("owenCode",misType.get("owenCode")+1);
            sourceMisTypeMap.get(group.getSource()).put("owenCode",
                    sourceMisTypeMap.get(group.getSource()).get("owenCode")+1);
        }
    }



    // 错误类型二、取错
    private static void checkMistake(GroupingParam group,Map settlementMap,Map diagMap,Map surgMap) {
        // 1 病案取错
        // 主诊断取错
        Settlement settlement = (Settlement) settlementMap.get(group.getPid());

        if (settlement != null) {
//            group.getErrorReason().append("0.0 结算单内无法找到对应记录,PID:"+group.getPid()+"\n");

            if (!(group.getItem().getZdcode()==null || group.getItem().getZdcode().equalsIgnoreCase("") || group.getItem().getZdcode().length()<=0)) {
                Diag diag = (Diag) diagMap.get(group.getItem().getZdcode().toLowerCase().split("\\+")[0]);
                if (!((settlement.getZdCode().length()<=0 && group.getItem().getZdcode().length()<=0) ||
                        settlement.getZdCode().toLowerCase().split("\\+")[0].equalsIgnoreCase(group.getItem().getZdcode().toLowerCase().split("\\+")[0]) ||
                        (diag!=null && diag.getDiagName().equals(settlement.getZdName()))) ) {


                    group.getErrorReason().append("1.1 主诊断取错,分组参数-主诊断：" + group.getItem().getZdcode() + " , 结算单-主诊断: " + settlement.getZdCode() + "\n");
                    //misType.put("errZd",misType.get("errZd")+1);
                    sourceMisTypeMap.get(group.getSource()).put("errZd",
                            sourceMisTypeMap.get(group.getSource()).get("errZd")+1);
                }

            }
            // 主手术取错
            if (!(group.getItem().getSscode()==null || group.getItem().getSscode().length()<=0 )) {
                Surg surg = (Surg) surgMap.get(group.getItem().getSscode().toLowerCase().split("\\+")[0]);
                //System.out.println(surg.getSurgName());
                if (!((settlement.getSsCode().length()<=0) ||
                        settlement.getSsCode().toLowerCase().split("\\+")[0].equalsIgnoreCase(group.getItem().getSscode().toLowerCase().split("\\+")[0]) ||
                        (surg!=null && surg.getSurgName().equals(settlement.getSsName()))) )
                    group.getErrorReason().append("1.1 主手术取错,分组参数-主手术：" + group.getItem().getSscode() + " , 结算单-主手术: " + settlement.getSsCode() + "\n");
                    //misType.put("errSs",misType.get("errSs")+1);
                    sourceMisTypeMap.get(group.getSource()).put("errSs",
                            sourceMisTypeMap.get(group.getSource()).get("errSs")+1);

            }
        }
        // 2.2 费用项取错 -- 使用结算单的比较
        String billDraCode = group.getBillDra();
        Item item = group.getItem();
        String errorStr = "2.1 费用序号遗漏或多余,相关校验条件：";
        if ("LE19".equalsIgnoreCase(billDraCode)||
                "LE23".equalsIgnoreCase(billDraCode)||
                "LE25".equalsIgnoreCase(billDraCode)){
            if (!(group.getItem().getUs()>0)){
                sourceErrFeeMap.get(group.getSource()).put("us",
                        sourceErrFeeMap.get(group.getSource()).get("us")+1);
                //errFeeItem.put("us",errFeeItem.get("us")+1);
                group.getErrorReason().append(errorStr+"billDragCode:"+billDraCode+" , us:"+group.getItem().getUs()+"\n");
            }
        }

        if ("LE49".equalsIgnoreCase(billDraCode)){
            if (!(item.getBreakstone()>1)){
                sourceErrFeeMap.get(group.getSource()).put("breakStone",
                        sourceErrFeeMap.get(group.getSource()).get("breakStone")+1);
                //errFeeItem.put("breakStone",errFeeItem.get("breakStone")+1);
                group.getErrorReason().append(errorStr+"billDrgCode:"+billDraCode+" , breakStone:"+item.getBreakstone()+"\n");
            }
        }

        if ("HK59".equalsIgnoreCase(billDraCode)){
            if (!(item.getErcp()>1)){
                sourceErrFeeMap.get(group.getSource()).put("ercp",
                        sourceErrFeeMap.get(group.getSource()).get("ercp")+1);
                //errFeeItem.put("ercp",errFeeItem.get("ercp")+1);
                group.getErrorReason().append(errorStr+"billDrgCode:"+billDraCode+" , erpc:"+item.getErcp()+"\n");
            }
        }

        if ("JB39".equalsIgnoreCase(billDraCode)){
            if (!(item.getSinglerfqc()>0)){
                sourceErrFeeMap.get(group.getSource()).put("singlerfqc",
                        sourceErrFeeMap.get(group.getSource()).get("singlerfqc")+1);
                //errFeeItem.put("singlerfqc",errFeeItem.get("singlerfqc")+1);
                group.getErrorReason().append(errorStr+"billDegCode:"+billDraCode+" , singlerfqc:"+item.getSinglerfqc()+"\n");
            }
        }

        if ("JB29".equalsIgnoreCase(billDraCode)){
            if (!(item.getDoublerfqc()>0)){
                sourceErrFeeMap.get(group.getSource()).put("doublerfqc",
                        sourceErrFeeMap.get(group.getSource()).get("doublerfqc")+1);
                //errFeeItem.put("doublerfqc",errFeeItem.get("doublerfqc")+1);
                group.getErrorReason().append(errorStr+"billDrgCode:"+billDraCode+" , doublerfqc:"+item.getDoublerfqc()+"\n");
            }
        }

        if ("LM11".equalsIgnoreCase(billDraCode) || "LM13".equalsIgnoreCase(billDraCode)){
            if (!(item.getDialysis()>0 && item.getDialysis()<=10)){
                sourceErrFeeMap.get(group.getSource()).put("dialysis",
                        sourceErrFeeMap.get(group.getSource()).get("dialysis")+1);
               //errFeeItem.put("dialysis",errFeeItem.get("dialysis")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , dialysis:"+item.getDialysis()+"\n");
            }
        }

        if ("LM21".equalsIgnoreCase(billDraCode) || "LM23".equalsIgnoreCase(billDraCode)){
            if (!(item.getDialysis()>10)){
                sourceErrFeeMap.get(group.getSource()).put("dialysis",
                        sourceErrFeeMap.get(group.getSource()).get("dialysis")+1);
                //errFeeItem.put("dialysis",errFeeItem.get("dialysis")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , dialysis:"+item.getDialysis()+"\n");
            }
        }

        if ("XR15".equalsIgnoreCase(billDraCode)){
            if (!(item.getKffy()>0)){
                sourceErrFeeMap.get(group.getSource()).put("kffy",
                        sourceErrFeeMap.get(group.getSource()).get("kffy")+1);
                //errFeeItem.put("kffy",errFeeItem.get("kffy")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , kffy:"+item.getKffy()+"\n");
            }
            if (!(item.getKfsj()>0)){
                sourceErrFeeMap.get(group.getSource()).put("kfsj",
                        sourceErrFeeMap.get(group.getSource()).get("kfsj")+1);
                //errFeeItem.put("kfsj",errFeeItem.get("kfsj")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" ,  kfsj:"+item.getKfsj()+"\n");
            }
        }

        if ("BU99".equalsIgnoreCase(billDraCode)||
                "EQ10".equalsIgnoreCase(billDraCode)||
                "EQ19".equalsIgnoreCase(billDraCode)||
                "PU19".equalsIgnoreCase(billDraCode)||
                "PU59".equalsIgnoreCase(billDraCode)){
            if (!(item.getIcu()>0)){
                sourceErrFeeMap.get(group.getSource()).put("icu",
                        sourceErrFeeMap.get(group.getSource()).get("icu")+1);
                //errFeeItem.put("icu",errFeeItem.get("icu")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , icu:"+item.getIcu()+"\n");
            }
        }

        if ("SU99".equalsIgnoreCase(billDraCode)){
            if (!(item.getVetime()>0)){
                sourceErrFeeMap.get(group.getSource()).put("vetime",
                        sourceErrFeeMap.get(group.getSource()).get("vetime")+1);
                //errFeeItem.put("vetime",errFeeItem.get("vetime")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , vetime:"+item.getVetime()+"\n");
            }
        }

        if ("KA29".equalsIgnoreCase(billDraCode)){
            if (!(item.getCsinglethyroi()>0)){
                sourceErrFeeMap.get(group.getSource()).put("csinglethyroi",
                        sourceErrFeeMap.get(group.getSource()).get("csinglethyroi")+1);
                //errFeeItem.put("csinglethyroi",errFeeItem.get("csinglethyroi")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , csinglethyroi:"+item.getCsinglethyroi()+"\n");
            }
        }

        if ("KA19".equalsIgnoreCase(billDraCode)){
            if (!(item.getCdoublethyroid()>0)){
                sourceErrFeeMap.get(group.getSource()).put("cdoublethyroid",
                        sourceErrFeeMap.get(group.getSource()).get("cdoublethyroid")+1);
                //errFeeItem.put("cdoublethyroid",errFeeItem.get("cdoublethyroid")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , cdoublethyroid:"+item.getCdoublethyroid()+"\n");
            }
        }

        if ("KB29".equalsIgnoreCase(billDraCode)){
            if (!(item.getSinglethyroi()>0)){
                sourceErrFeeMap.get(group.getSource()).put("singlethyroi",
                        sourceErrFeeMap.get(group.getSource()).get("singlethyroi")+1);
                //errFeeItem.put("singlethyroi",errFeeItem.get("singlethyroi")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , singlethyroi:"+item.getSinglethyroi()+"\n");
            }
        }

        if ("KB19".equalsIgnoreCase(billDraCode)){
            if (!(item.getDoublethyroid()>0)){
                sourceErrFeeMap.get(group.getSource()).put("doublethyroid",
                        sourceErrFeeMap.get(group.getSource()).get("doublethyroid")+1);
                //errFeeItem.put("doublethyroid",errFeeItem.get("doublethyroid")+1);
                group.getErrorReason().append(errorStr+" billDrgCode:"+billDraCode+" , doublethyroid:"+item.getDoublethyroid()+"\n");
            }
        }
    }


    // 错误类型一、缺失
    private static void checkDefect(GroupingParam group, Map<String,Settlement> settlementMap, ArrayList<String> expenseItem){

        // 1.1 判断主诊断是否缺失
        if (group.getItem().getZdcode() == null || group.getItem().getZdcode().equals("")){
            sourceMisTypeMap.get(group.getSource()).put("noneZd",
                    sourceMisTypeMap.get(group.getSource()).get("noneZd")+1);
            //misType.put("noneZd",misType.get("noneZd")+1);
            group.getErrorReason().append("1.1 主诊断缺失\n");
        }
        // 1.2 判断主手术是否缺失
        Settlement s = settlementMap.get(group.getPid());
        if (s==null){
            group.getErrorReason().append("0.0 结算单内无该条数据的对应记录，PID："+group.getPid()+"\n");
        }else {
            if (group.getItem().getSscode() == null || group.getItem().getSscode().equals("") || group.getItem().getSscode().length()<=0) {
                if (!(s.getSsCode().length()<=0 || s.getSsCode() == null || s.getSsCode().equals(""))) {
                    group.getErrorReason().append("1.2 主手术缺失\n");
                    sourceMisTypeMap.get(group.getSource()).put("noneSs",
                            sourceMisTypeMap.get(group.getSource()).get("noneSs") + 1);
                }
             //  misType.put("noneSs",misType.get("noneSs")+1);
            }
        }
            // 1.3 判断次诊断是否为空
            if (group.getItem().getCzdcode() == null || group.getItem().getCzdcode().equals("") || group.getItem().getCzdcode().size() <= 0){
                sourceMisTypeMap.get(group.getSource()).put("noneCzd",
                        sourceMisTypeMap.get(group.getSource()).get("noneCzd")+1);
            }
        // 2.1 费用项参数不一致
        ArrayList<String> allKey = getAllKey(group.getItemStr());
        Map<String,Integer> paramsMap = new HashMap<String, Integer>();
        for (String str:allKey){
            paramsMap.put(str,1);
        }
        List<String> unParams = new ArrayList<>();
        boolean b =  false;
        for (String param:expenseItem){
            if (paramsMap.get(param) == null){
                b = true;
                unParams.add(param);
                sourceMisFeeMap.get(group.getSource()).put(param,sourceMisFeeMap.get(group.getSource()).get(param)+1);
               // misFeeItem.put(param,misFeeItem.get(param)+1);
            }
        }
        if (true == b){
            group.getErrorReason().append("2.1 费用项缺失,缺失参数："+unParams.toString()+"\n");
        }
        // 3.基本信息缺失
        checkBaseInfo(group);
    }
}
