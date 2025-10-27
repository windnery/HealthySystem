
package com.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;

import com.service.TokenService;
import com.utils.*;

import com.service.DictionaryService;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 学生
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/Student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private TeacherService teacherService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("StudentId",request.getSession().getAttribute("userId"));
        else if("心理老师".equals(role))
            params.put("TeacherId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = studentService.queryPage(params);

        //字典表数据转换
        List<StudentView> list =(List<StudentView>)page.getList();
        for(StudentView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        StudentEntity Student = studentService.selectById(id);
        if(Student !=null){
            //entity转view
            StudentView view = new StudentView();
            BeanUtils.copyProperties( Student , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody StudentEntity Student, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,Student:{}",this.getClass().getName(),Student.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<StudentEntity> queryWrapper = new EntityWrapper<StudentEntity>()
            .eq("username", Student.getUsername())
            .or()
            .eq("Student_phone", Student.getStudentPhone())
            .or()
            .eq("Student_id_number", Student.getStudentIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        StudentEntity studentEntity = studentService.selectOne(queryWrapper);
        if(studentEntity ==null){
            Student.setCreateTime(new Date());
            Student.setPassword("123456");
            studentService.insert(Student);
            return R.ok();
        }else {
            return R.error(511,"账户或者学生手机号或者学生身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody StudentEntity Student, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,Student:{}",this.getClass().getName(),Student.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<StudentEntity> queryWrapper = new EntityWrapper<StudentEntity>()
            .notIn("id",Student.getId())
            .andNew()
            .eq("username", Student.getUsername())
            .or()
            .eq("Student_phone", Student.getStudentPhone())
            .or()
            .eq("Student_id_number", Student.getStudentIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        StudentEntity studentEntity = studentService.selectOne(queryWrapper);
        if("".equals(Student.getStudentPhoto()) || "null".equals(Student.getStudentPhoto())){
                Student.setStudentPhoto(null);
        }
        if(studentEntity ==null){
            studentService.updateById(Student);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者学生手机号或者学生身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        studentService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer StudentId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<StudentEntity> StudentList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            StudentEntity studentEntity = new StudentEntity();
                            StudentList.add(studentEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //学生手机号
                                if(seachFields.containsKey("StudentPhone")){
                                    List<String> StudentPhone = seachFields.get("StudentPhone");
                                    StudentPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> StudentPhone = new ArrayList<>();
                                    StudentPhone.add(data.get(0));//要改的
                                    seachFields.put("StudentPhone",StudentPhone);
                                }
                                //学生身份证号
                                if(seachFields.containsKey("StudentIdNumber")){
                                    List<String> StudentIdNumber = seachFields.get("StudentIdNumber");
                                    StudentIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> StudentIdNumber = new ArrayList<>();
                                    StudentIdNumber.add(data.get(0));//要改的
                                    seachFields.put("StudentIdNumber",StudentIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<StudentEntity> StudentEntities_username = studentService.selectList(new EntityWrapper<StudentEntity>().in("username", seachFields.get("username")));
                        if(StudentEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(StudentEntity s:StudentEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学生手机号
                        List<StudentEntity> StudentEntities_StudentPhone = studentService.selectList(new EntityWrapper<StudentEntity>().in("Student_phone", seachFields.get("StudentPhone")));
                        if(StudentEntities_StudentPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(StudentEntity s:StudentEntities_StudentPhone){
                                repeatFields.add(s.getStudentPhone());
                            }
                            return R.error(511,"数据库的该表中的 [学生手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学生身份证号
                        List<StudentEntity> StudentEntities_StudentIdNumber = studentService.selectList(new EntityWrapper<StudentEntity>().in("Student_id_number", seachFields.get("StudentIdNumber")));
                        if(StudentEntities_StudentIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(StudentEntity s:StudentEntities_StudentIdNumber){
                                repeatFields.add(s.getStudentIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [学生身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        studentService.insertBatch(StudentList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        StudentEntity Student = studentService.selectOne(new EntityWrapper<StudentEntity>().eq("username", username));
        if(Student==null || !Student.getPassword().equals(password))
            return R.error("账号或密码不正确");

        String token = tokenService.generateToken(Student.getId(),username, "Student", "学生");
        R r = R.ok();
        r.put("token", token);
        r.put("role","学生");
        r.put("username",Student.getStudentName());
        r.put("tableName","Student");
        r.put("userId",Student.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody StudentEntity Student){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<StudentEntity> queryWrapper = new EntityWrapper<StudentEntity>()
            .eq("username", Student.getUsername())
            .or()
            .eq("Student_phone", Student.getStudentPhone())
            .or()
            .eq("Student_id_number", Student.getStudentIdNumber())
            ;
        StudentEntity studentEntity = studentService.selectOne(queryWrapper);
        if(studentEntity != null)
            return R.error("账户或者学生手机号或者学生身份证号已经被使用");
        Student.setCreateTime(new Date());
        studentService.insert(Student);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        StudentEntity Student = new StudentEntity();
        Student.setPassword("123456");
        Student.setId(id);
        studentService.updateById(Student);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        StudentEntity Student = studentService.selectOne(new EntityWrapper<StudentEntity>().eq("username", username));
        if(Student!=null){
            Student.setPassword("123456");
            boolean b = studentService.updateById(Student);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取学生的session学生信息
    */
    @RequestMapping("/session")
    public R getCurrStudent(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        StudentEntity Student = studentService.selectById(id);
        if(Student !=null){
            //entity转view
            StudentView view = new StudentView();
            BeanUtils.copyProperties( Student , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = studentService.queryPage(params);

        //字典表数据转换
        List<StudentView> list =(List<StudentView>)page.getList();
        for(StudentView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        StudentEntity Student = studentService.selectById(id);
            if(Student !=null){


                //entity转view
                StudentView view = new StudentView();
                BeanUtils.copyProperties( Student , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody StudentEntity Student, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,Student:{}",this.getClass().getName(),Student.toString());
        Wrapper<StudentEntity> queryWrapper = new EntityWrapper<StudentEntity>()
            .eq("username", Student.getUsername())
            .or()
            .eq("Student_phone", Student.getStudentPhone())
            .or()
            .eq("Student_id_number", Student.getStudentIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        StudentEntity studentEntity = studentService.selectOne(queryWrapper);
        if(studentEntity ==null){
            Student.setCreateTime(new Date());
        Student.setPassword("123456");
        studentService.insert(Student);
            return R.ok();
        }else {
            return R.error(511,"账户或者学生手机号或者学生身份证号已经被使用");
        }
    }


}
