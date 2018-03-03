package com.hd.service.impl.generator;

import com.hd.dao.BaseDao;
import com.hd.entity.CodeBeanProperty;
import com.hd.service.generator.CodeBeanPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codeBeanPropertyService")
public class CodeBeanPropertyServiceImpl implements CodeBeanPropertyService {
    @Autowired
    private BaseDao<CodeBeanProperty,Long>codeBeanPropertyDao;
    @Override
    public void save(CodeBeanProperty codeBeanProperty) {
        codeBeanPropertyDao.save(codeBeanProperty);
    }

    @Override
    public void delete(String tableName) {
        String hql="delete from  CodeBeanProperty where tableName=?";
        codeBeanPropertyDao.execute(hql,tableName);
    }

    @Override
    public List<CodeBeanProperty> findByTableName(String tableName) {
        String hql="from CodeBeanProperty where tableName=?";
        return codeBeanPropertyDao.find(hql,tableName);
    }
}
