package com.hd.service.generator;

import com.hd.entity.CodeBeanProperty;

import java.util.List;

public interface CodeBeanPropertyService {
    void save(CodeBeanProperty code_bean_property);
    void delete(String tableName);
    List<CodeBeanProperty> findByTableName(String tableName);
}
