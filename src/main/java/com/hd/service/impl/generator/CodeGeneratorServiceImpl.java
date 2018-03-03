package com.hd.service.impl.generator;

import com.hd.dao.BaseDao;
import com.hd.entity.CodeGenerator;
import com.hd.service.generator.CodeGeneratorService;
import com.hd.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codeGeneratorService")
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    @Autowired
    private BaseDao<CodeGenerator,Long>codeGeneratorDao;
    @Override
    public void save(CodeGenerator codeGenerator) {
        codeGeneratorDao.save(codeGenerator);
    }

    @Override
    public void delete(Long id) {
        codeGeneratorDao.delete(CodeGenerator.class,id);
    }

    @Override
    public void update(CodeGenerator codeGenerator) {
        codeGeneratorDao.update(codeGenerator);
    }

    @Override
    public void dataGrid(PageInfo info) {
        String hql="from CodeGenerator";
        List<CodeGenerator> codeGeneratorList=codeGeneratorDao.findPage(hql,info.getPage(),info.getLimit());
        info.setData(codeGeneratorList);
        int total=codeGeneratorDao.find(hql).size();
        info.setCount(total);
    }

    @Override
    public CodeGenerator getByTableName(String taleName) {
        String hql="from CodeGenerator where name=?";
        List<CodeGenerator>codeGenerators=codeGeneratorDao.find(hql,taleName);
        if(codeGenerators.size()>0){
            return codeGenerators.get(0);
        }else{
            return null;
        }
    }
}
