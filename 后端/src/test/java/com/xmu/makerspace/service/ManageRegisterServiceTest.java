package com.xmu.makerspace.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NonNls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

/**
 * Created by status200 on 2017/8/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageRegisterServiceTest {

    @NonNls
    private Log log = LogFactory.getLog(ManageRegisterServiceTest.class);

    @Autowired
    @NonNls
    private ManageRegisterService manageRegisterService;

    @Test
    public void testGetAllRegistration() {
        log.info(manageRegisterService.getAllRegistration());
    }

    @Test
    public void testAgreeRegistration() throws MessagingException {
        manageRegisterService.agreeRegistration("8cf9af68-c25e-4624-833b-31088e6a35ce");
    }

    @Test
    public void testRefuseRegistration() throws MessagingException {
        manageRegisterService.refuseRegistration("f94f7fab-7d51-4d0d-996b-7259c720bd67");
    }
}
