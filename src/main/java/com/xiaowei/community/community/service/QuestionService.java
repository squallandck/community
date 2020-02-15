package com.xiaowei.community.community.service;

import com.xiaowei.community.community.dto.PaginationDto;
import com.xiaowei.community.community.dto.QuestionDto;
import com.xiaowei.community.community.exception.CustomizeErrorCode;
import com.xiaowei.community.community.exception.CustomizeException;
import com.xiaowei.community.community.mapper.QuestionExtMapper;
import com.xiaowei.community.community.mapper.QuestionMapper;
import com.xiaowei.community.community.mapper.UserMapper;
import com.xiaowei.community.community.model.Question;
import com.xiaowei.community.community.model.QuestionExample;
import com.xiaowei.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxw on 2020/2/12
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCnt = (int) questionMapper.countByExample(new QuestionExample());
        paginationDto.setPagination(totalCnt, page, size);

        if (page < 1) {
            page = 1;
        } else if (page > paginationDto.getTotalPage()) {
            page = paginationDto.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCnt = (int) questionMapper.countByExample(questionExample);
        paginationDto.setPagination(totalCnt, page, size);

        if (page < 1) {
            page = 1;
        } else if (page > paginationDto.getTotalPage()) {
            page = paginationDto.getTotalPage();
        }
        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            question.setGmtModify(System.currentTimeMillis());
            int updated = questionMapper.updateByPrimaryKeySelective(question);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
