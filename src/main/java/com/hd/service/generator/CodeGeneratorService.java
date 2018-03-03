package com.hd.service.generator;

import com.hd.entity.CodeGenerator;
import com.hd.util.PageInfo;

public interface CodeGeneratorService {
    void save(CodeGenerator codeGenerator);
    void delete(Long id);
    void update(CodeGenerator codeGenerator);
    void dataGrid(PageInfo info);
    CodeGenerator getByTableName(String taleName);
}
