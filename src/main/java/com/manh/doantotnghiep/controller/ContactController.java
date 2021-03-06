package com.manh.doantotnghiep.controller;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ContactEntity;
import com.manh.doantotnghiep.bean.entity.EmailEntity;
import com.manh.doantotnghiep.bean.entity.FeedbackEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.service.ContactService;
import com.manh.doantotnghiep.service.FeedbackService;
import com.manh.doantotnghiep.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
@LogExecutionTime
@RequestMapping("api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    public JavaMailSender emailSender;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllcontacts() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getContactById(@RequestParam Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.getById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addContact(@RequestBody String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.addContact(json);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteContact(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.deleteById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/sendHtmlEmail", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity sendEmail(@RequestBody EmailEntity emailModel) {
        LOGGER.info("Sending email");

        MimeMessage mail = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(emailModel.getEmail());
            helper.setFrom("manh3698@gmail.com");
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getMessage(), true);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email: " + emailModel.toString(), e);
        } finally {}
        emailSender.send(mail);
        return ResponseEntity.accepted().build();
    }

}
