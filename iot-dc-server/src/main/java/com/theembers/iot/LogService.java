package com.theembers.iot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-16 14:04
 */
@Component
public class LogService implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            StringBuilder sb = new StringBuilder("GlobalInfo.SN_CHANNEL_INFO_MAP.size(): " + GlobalInfo.SN_CHANNEL_INFO_MAP.size() + "\n");
            if (!CollectionUtils.isEmpty(GlobalInfo.SN_CHANNEL_INFO_MAP)) {
                GlobalInfo.SN_CHANNEL_INFO_MAP.forEach((k, v) -> sb.append("sn: ").append(k).append(" , info: ").append(v).append("\n"));
            }
            LOGGER.info(sb.toString());
            Thread.sleep(10000);
        }
    }
}
