package com.hd.controller.generator;

import com.hd.controller.base.BaseController;
import com.hd.jdbc.DAO;
import com.hd.jdbc.DBUtil;
import com.hd.entity.CodeBeanProperty;
import com.hd.entity.CodeGenerator;
import com.hd.generator.*;
import com.hd.service.generator.CodeBeanPropertyService;
import com.hd.service.generator.CodeGeneratorService;
import com.hd.util.PageInfo;
import com.hd.util.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("generator")
@Controller
public class GeneratorController extends BaseController {
    // 载入bean配置
    // ===================================
    public static final String BEAN_CFG_PROPERTIES = "bean.cfg.properties";
    public static LoadProperties beanLoadProperties = new LoadProperties(BEAN_CFG_PROPERTIES);
    // 代码生成的基目录
    public static String BEAN_PATH = beanLoadProperties.getValue("CODE_GENERATOR_BASE_PATH");

    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private CodeBeanPropertyService codeBeanPropertyService;

    /**
     * 列表
     *
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("dataGrid")
    public Object dataGrid(Integer page, Integer limit) {
        PageInfo info = new PageInfo(page, limit);
        codeGeneratorService.dataGrid(info);
        return info;
    }

    /**
     * 同步表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("syncTable")
    public Object syncTable(String tableName) throws Exception {
        CodeGenerator codeGenerator=codeGeneratorService.getByTableName(tableName);
        if(codeGenerator!=null){
            return renderError(tableName+"表已存在！");
        }
        DBUtil.syncTable(tableName);
        return renderSuccess("同步成功！");
    }

    /**
     * 显示数据库所有表
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("showTables")
    public Object showTables() throws Exception {
        List<String> tableNames = DBUtil.showTables();
        return renderSuccess(tableNames);
    }

    /**
     * 编辑建表的表和列属性
     *
     * @param tableName
     * @return
     */
    @RequestMapping("/editPage/{tableName}")
    public ModelAndView editPage(@PathVariable("tableName") String tableName) {
        ModelAndView mv = new ModelAndView();
        List<CodeBeanProperty> codeBeanProperties = codeBeanPropertyService.findByTableName(tableName);
        //List<CodeBeanProperty>codeBeanProperties=DBUtil.getColumnProperty(tableName);
        mv.addObject("codeBeanProperties", codeBeanProperties);
        mv.setViewName("generator/edit");
        return mv;
    }

    /**
     * 修改表
     * @param tableName
     * @param description
     * @param fields
     * @param lengths
     * @param types
     * @param defaultValues
     * @param keys
     * @param isNulls
     * @param extras
     * @param comments
     * @param isQuerys
     * @param isTransients
     * @param showTypes
     * @param valids
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "addOrEdit",method = RequestMethod.POST)
    public Object addOrEdit(String tableName,//表名
                      String description,//描述
                      String fields,//列名
                      String lengths,//长度
                      String types,//类型
                      String defaultValues,//默认值
                      String keys,//主键
                      String isNulls,//是否为空
                      String extras,//id主键策略
                      String comments,//注释
                      String isQuerys,//是否查询
                      String isTransients,//是否是临时对象
                      String showTypes,//前端页面显示类型
                      String valids)//校验
            throws Exception {
        String field[] = fields.split(",");
        String type[] = types.split(",");
        String length[] = lengths.split(",");
        String defaultValue[] = defaultValues.split(",");
        String key[] = keys.split(",");
        String isNull[] = isNulls.split(",");
        String extra[] = extras.split(",");
        String comment[] = comments.split(",");
        String isQuery[] = isQuerys.split(",");
        String isTransient[] = isTransients.split(",");
        String showType[] = showTypes.split(",");
        String valid[] = valids.split(",");
        List<CodeBeanProperty> codeBeanProperties = new ArrayList<CodeBeanProperty>();
        //刪除表原來的字段
        codeBeanPropertyService.delete(tableName);
        for (int i = 0; i < field.length; i++) {
            CodeBeanProperty codeBeanProperty = new CodeBeanProperty();
            codeBeanProperty.setField(field[i]);
            codeBeanProperty.setDescription(description);
            codeBeanProperty.setTableName(tableName);
            codeBeanProperty.setShowType(showType[i]);
            codeBeanProperty.setIsTransient(isTransient[i]);
            codeBeanProperty.setIsQuery(isQuery[i]);
            codeBeanProperty.setComment(comment[i]);
            codeBeanProperty.setExtra(extra[i].equals("1") ? "auto_increment" : "");
            codeBeanProperty.setIsNull(!isNull[i].equals("1") ? "YES" : "NO");
            codeBeanProperty.setPriKey(key[i].equals("1") ? "PRI" : "");
            codeBeanProperty.setDefaultValue(defaultValue[i].equals("0") ? "" : defaultValue[i]);
            codeBeanProperty.setLength(Integer.parseInt(length[i]));
            codeBeanProperty.setType(type[i]);
            codeBeanProperty.setJavaType(StringUtil.getType(type[i]));
            codeBeanProperty.setJavaField(StringUtil.handleColumnName(field[i]));
            codeBeanProperty.setValid(valid[i].equals("0")?"":valid[i]);
            codeBeanProperty.setMethodFieldName(StringUtil.handleTableName(field[i]));
            codeBeanPropertyService.save(codeBeanProperty);//保存字段
            codeBeanProperties.add(codeBeanProperty);
        }
        //删除表，创建表
        DAO dao = new DAO();
        String sql = ModyfyTableSql.createTableSql(tableName, codeBeanProperties);//创建表语句
        String sql2 = ModyfyTableSql.dropTable(tableName);//删除表语句
        dao.update(sql2);//刪除表
        dao.update(sql);//创建表

        //保存表名和描述
        CodeGenerator codeGenerator = codeGeneratorService.getByTableName(tableName);
        if (codeGenerator != null) {
            codeGenerator.setId(codeGenerator.getId());
            codeGenerator.setDescription(description);
            codeGenerator.setName(tableName);
            codeGenerator.setStatus(0);
            codeGeneratorService.update(codeGenerator);
        } else {
            codeGenerator=new CodeGenerator();
            codeGenerator.setDescription(description);
            codeGenerator.setName(tableName);
            codeGenerator.setStatus(0);
            codeGeneratorService.save(codeGenerator);
        }
        return renderSuccess();
    }

    /**
     * 删除
     * @param id
     * @param tableName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id, String tableName){
        codeBeanPropertyService.delete(tableName);
        codeGeneratorService.delete(id);
        return renderSuccess();
    }


    /**
     * 生成代码
     * @param tableName
     * @param entity
     * @param service
     * @param controller
     * @param add
     * @param edit
     * @param list
     * @param view
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "generator",method = RequestMethod.POST)
    public Object generator(String tableName,String entity, String service,
                            String controller, String add,String edit,
                            String list, String view) throws Exception {

        List<CodeBeanProperty> codeBeanProperties = DBUtil.getColumnProperty(tableName);
        if (entity.equals("1")) {
            GeneratorEntity generatorEntity = new GeneratorEntity();
            generatorEntity.generateBean(tableName, codeBeanProperties);
        }
        if (service.equals("1")) {
            GeneratorService generatorService = new GeneratorService();
            GeneratorServiceImpl generatorServiceImpl = new GeneratorServiceImpl();
            generatorService.generateBean(tableName, codeBeanProperties);
            generatorServiceImpl.generateBean(tableName, codeBeanProperties);
        }
        if (controller.equals("1")) {
            com.hd.generator.GeneratorController generatorController = new com.hd.generator.GeneratorController();
            generatorController.generateBean(tableName, codeBeanProperties);
        }
        if (edit.equals("1")) {
            GeneratorWebEdit generatorEdit = new GeneratorWebEdit();
            generatorEdit.generateBean(tableName, codeBeanProperties);
        }
        if (add.equals("1")) {
            GeneratorWebAdd generatorAdd = new GeneratorWebAdd();
            generatorAdd.generateBean(tableName, codeBeanProperties);
        }
        if (view.equals("1")) {
            GeneratorWebView generatorView = new GeneratorWebView();
            generatorView.generateBean(tableName, codeBeanProperties);
        }
        if (list.equals("1")) {
            GeneratorWebList generatorList = new GeneratorWebList();
            generatorList.generateBean(tableName, codeBeanProperties);
        }
        CodeGenerator codeGenerator=codeGeneratorService.getByTableName(tableName);
        codeGenerator.setStatus(1);
        codeGeneratorService.update(codeGenerator);
        return renderSuccess();
    }

    /**
     * 查看生成代码
     * @param tableName
     * @param type
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("readCodeFile")
    public String readCodeFile( String tableName,String type,Model model) throws IOException {
        File file=null;
        String path = "";
        File filePath = null;
        if(type.equals("entity")) {
            path = BEAN_PATH + "/src/main/java/" +GeneratorEntity.BEAN_PATH+ "/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleTableName(tableName) + ".java";
            file = new File(fileName);
        }
        if(type.equals("service")) {
            path = BEAN_PATH + "/src/main/java/" +GeneratorService.BEAN_PATH+ "/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleTableName(tableName) + "Service.java";
            file = new File(fileName);
        }
        if(type.equals("serviceImpl")) {
            path = BEAN_PATH + "/src/main/java/" +GeneratorServiceImpl.BEAN_PATH+ "/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleTableName(tableName) + "ServiceImpl.java";
            file = new File(fileName);
        }

        if(type.equals("controller")) {
            System.out.println(GeneratorController.BEAN_PATH);
            path = BEAN_PATH + "/src/main/java/" + com.hd.generator.GeneratorController.BEAN_PATH+ "/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleTableName(tableName) + "Controller.java";
            System.out.println(fileName);
            file = new File(fileName);
        }
        if(type.equals("add")) {
            path = BEAN_PATH + "/src/main/webapp/" + GeneratorWebAdd.BEAN_PATH + "/"+StringUtil.handleColumnName(tableName)+"/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleColumnName(tableName) + "Add.jsp";
            file = new File(fileName);
        }
        if(type.equals("edit")) {
            path = BEAN_PATH + "/src/main/webapp/" + GeneratorWebEdit.BEAN_PATH + "/"+StringUtil.handleColumnName(tableName)+"/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleColumnName(tableName) + "Edit.jsp";
            file = new File(fileName);
        }
        if(type.equals("view")) {
            path = BEAN_PATH + "/src/main/webapp/" + GeneratorWebView.BEAN_PATH + "/"+StringUtil.handleColumnName(tableName)+"/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleColumnName(tableName) + "View.jsp";
            file = new File(fileName);
        }
        if(type.equals("list")) {
            path = BEAN_PATH + "/src/main/webapp/" + GeneratorWebList.BEAN_PATH + "/"+StringUtil.handleColumnName(tableName)+"/";
            filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileName = path + StringUtil.handleColumnName(tableName) + "List.jsp";
            file = new File(fileName);
        }
        String targetPath=file.getAbsolutePath();
        String result= FileUtil.readFile(targetPath);
        model.addAttribute("code", result);
        return "generator/codeview";
    }

    @RequestMapping("queryCode")
    public String queryCode(String tableName, String status, Model model) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("status", status);
        return "generator/queryGeneratorStatus";
    }



    @RequestMapping("generatorCodeType")
    public String generatorCodeType(String name,Model model) {
        model.addAttribute("tableName", name);
        return "generator/generatorCodeType";
    }
    @RequestMapping("editPage")
    public String editPage(String tableName,Model model){
        List<CodeBeanProperty> codeBeanProperties=codeBeanPropertyService.findByTableName(tableName);
        model.addAttribute("codeBeanProperties", codeBeanProperties);
        return "generator/generatorEdit";
    }
    @ResponseBody
    @RequestMapping(value = "valid/{tableName}",method = RequestMethod.POST)
    public Object valid(@PathVariable("tableName")String tableName)throws Exception{
        List<String>tableNames=DBUtil.showTables();
        if(tableNames.contains(tableName)){
            return renderSuccess("表【"+tableName+"】已存在！");
        }
        return renderError();
    }
}
