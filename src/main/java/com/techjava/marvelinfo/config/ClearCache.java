package com.techjava.marvelinfo.config;

import com.techjava.marvelinfo.constant.GlobalConstants;
import com.techjava.marvelinfo.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClearCache {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(ClearCache.class);

    /** Cache clear time maintained in milli seconds. */

    @CacheEvict(value ="characterdetails",allEntries = true)
    @Scheduled(fixedDelayString = "${cache-clear-interval}")
    public void cacheEvict() {
        logger.info("Cache Cleared at {} " , DateUtils.convertDateToString(new Date(), GlobalConstants.DD_MM_YYYY_HH_MM_SS));
    }



}
