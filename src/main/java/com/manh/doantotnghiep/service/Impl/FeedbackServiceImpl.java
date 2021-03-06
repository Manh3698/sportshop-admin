package com.manh.doantotnghiep.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.FeedbackEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.dao.FeedbackDao;
import com.manh.doantotnghiep.service.FeedbackService;
import com.manh.doantotnghiep.utils.Constants;

@Service
@LogExecutionTime
public class FeedbackServiceImpl implements FeedbackService {
    
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<FeedbackEntity> responses = feedbackDao.findAll();
        return new ResultBean(responses, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        Optional<FeedbackEntity> feedbackOp = feedbackDao.findById(id);
        if(!feedbackOp.isPresent()) {
            throw new Exception("Feedback by id " + id + " does not exist!");
        }
        return new ResultBean(feedbackOp.get(), Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addFeedback(FeedbackEntity entity) throws Exception {
        FeedbackEntity feedback = feedbackDao.save(entity);
        return new ResultBean(feedback, Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateFeedback(FeedbackEntity entity) throws Exception {
        Optional<FeedbackEntity> feedbackOp = feedbackDao.findById(entity.getId());
        if(!feedbackOp.isPresent()) {
            throw new Exception("Feedback by id " + entity.getId() + " does not exist!");
        }
        FeedbackEntity feedback = feedbackDao.save(entity);
        return new ResultBean(feedback, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        Optional<FeedbackEntity> feedbackOp = feedbackDao.findById(id);
        if(!feedbackOp.isPresent()) {
            throw new Exception("Feedback by id " + id + " does not exist!");
        }
        feedbackDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }

}
