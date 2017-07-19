package com.sywl.web.service;

import com.sywl.web.dao.ApplyCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pengxiao
 * @date 2017/7/19
 */
@Service
@Transactional
public class ApplyCardService {

    @Autowired
    private ApplyCardMapper applyCardMapper;


}
