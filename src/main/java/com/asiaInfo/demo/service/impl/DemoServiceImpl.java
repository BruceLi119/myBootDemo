package com.asiaInfo.demo.service.impl;

import com.asiaInfo.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: liShuGuang
 * @dateTime: 2021/04/09
 */
@Service("distinctionLogService")
public class DemoServiceImpl implements DemoService {

    private Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Value("${areaPreName}")
    private String areaPreName;

    //@Override
    //public String distinctionLogComplete(String data) {
    //    JSONObject jsonObject = JSONObject.parseObject(data);
    //    String oper_priacct = (String) jsonObject.get("OPER_PRIACCT");
    //    String PRACCT_NAME = (String) jsonObject.get("PRACCT_NAME");
    //
    //    //2. 然后判断待区分的字符串中是否包含”_“，如果有，再判断前三个字符有没有在集合  中，如果存在，则需要进行上传SFTP
    //    String[] areaPreNameArr = areaPreName.split(",");
    //    Set<String> areaPreNameArrSet = new HashSet<>(Arrays.asList(areaPreNameArr));
    //    boolean res = areaPreNameArrSet.contains(oper_priacct);
    //    Set<String> areaNameSet = new HashSet<>();
    //
    //    return null;
    //}
}
