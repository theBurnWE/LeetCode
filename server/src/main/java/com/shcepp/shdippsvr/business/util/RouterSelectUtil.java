package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.business.exception.BizCheckException;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Burn~E
 * @date: 20190520
 * @description: 对站点的的选择和转换
 * @version:V1.0 *
 */
public class RouterSelectUtil {

    private static final Logger logger = LoggerFactory.getLogger(RouterSelectUtil.class);
    /*默认的*/
    private static final String DEFAULT_DECLARETYPE = "P";
    /*转换类型为由分站点向主站点转换*/
    private static final String STM_DECLARETYPE = "STM";
    /*转换类型为由主站点向分站点转换*/
    private static final String MTS_DECLARETYPE = "MTS";

    /**
     * 根据平台站点号,支付公司站点号,以及业务模式获取目标站点号
     *
     * @param platCode    传入的站点号
     * @param payerCode   传入的支付公司站点号
     * @param declareType 传入的业务类型，默认为对私
     * @return platCode
     */
    public static String getSubMappedPlatCode(String platCode, String payerCode, String declareType) {

        if (StringUtils.isEmpty(declareType)) {
            declareType = DEFAULT_DECLARETYPE;
        }
        //给支持
        if (StringUtils.isEmpty(payerCode)) {
            payerCode = "";
        }

        String agentProp = platCode + "." + payerCode + "." + declareType;
        String payCode = PropertiesUtil.get(agentProp);
        if (StringUtils.isEmpty(payCode)) {
            logger.warn("未配置商户[{}]的子站点编号，使用传入编号", platCode);
            payCode = platCode;
        }
        return payCode;
    }

    /**
     * 根据子站点号换出主站点号
     *
     * @param platCode    传入的站点号
     * @param declareType 传入的业务类型，默认为对私
     * @return platCode
     */
    public static String getMainPlatCode(String platCode, String declareType) {

        if (StringUtils.isEmpty(declareType)) {
            declareType = STM_DECLARETYPE;
        }

        String agentProp = platCode + "." + declareType;
        String mainPlatCode = PropertiesUtil.get(agentProp);
        if (StringUtils.isEmpty(mainPlatCode)) {
            logger.warn("未配置主站点[{}]的子站点号", platCode);
            throw new BizCheckException(BaseService.BR_OTHER_ERROR,
                                        "未配置主站点[" + platCode + "]的子站点号");
        }
        return mainPlatCode;
    }

    /*根据规范解析传入的platCode*/
    public static String[] parsePlatCode(String platCode) {

        if (StringUtils.isEmpty(platCode)) {
            return null;
        }
        String[] platCodeStrArray = new String[4];

        platCodeStrArray[0] = platCode.substring(0, 3);
        platCodeStrArray[1] = platCode.substring(3, 4);
        platCodeStrArray[2] = platCode.substring(4, 6);
        platCodeStrArray[3] = platCode.substring(6, 7);

        return platCodeStrArray;

    }

}
