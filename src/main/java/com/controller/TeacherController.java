
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
 * 心理老师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/Teacher")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService TeacherService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


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
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("心理老师".equals(role))
            params.put("TeacherId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = TeacherService.queryPage(params);

        //字典表数据转换
        List<TeacherView> list =(List<TeacherView>)page.getList();
        for(TeacherView c:list){
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
        TeacherEntity Teacher = TeacherService.selectById(id);
        if(Teacher !=null){
            //entity转view
            TeacherView view = new TeacherView();
            BeanUtils.copyProperties( Teacher , view );//把实体数据重构到view中

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
    public R save(@RequestBody TeacherEntity Teacher, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,Teacher:{}",this.getClass().getName(),Teacher.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TeacherEntity> queryWrapper = new EntityWrapper<TeacherEntity>()
            .eq("username", Teacher.getUsername())
            .or()
            .eq("Teacher_phone", Teacher.getTeacherPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherEntity TeacherEntity = TeacherService.selectOne(queryWrapper);
        if(TeacherEntity==null){
            Teacher.setCreateTime(new Date());
            Teacher.setPassword("123456");
            TeacherService.insert(Teacher);
            return R.ok();
        }else {
            return R.error(511,"账户或者心理老师手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TeacherEntity Teacher, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,Teacher:{}",this.getClass().getName(),Teacher.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<TeacherEntity> queryWrapper = new EntityWrapper<TeacherEntity>()
            .notIn("id",Teacher.getId())
            .andNew()
            .eq("username", Teacher.getUsername())
            .or()
            .eq("Teacher_phone", Teacher.getTeacherPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherEntity TeacherEntity = TeacherService.selectOne(queryWrapper);
        if("".equals(Teacher.getTeacherPhoto()) || "null".equals(Teacher.getTeacherPhoto())){
                Teacher.setTeacherPhoto(null);
        }
        if(TeacherEntity==null){
            TeacherService.updateById(Teacher);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者心理老师手机号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        TeacherService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<TeacherEntity> TeacherList = new ArrayList<>();//上传的东西
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
                            TeacherEntity TeacherEntity = new TeacherEntity();
//                            TeacherEntity.setUsername(data.get(0));                    //账户 要改的
//                            //TeacherEntity.setPassword("123456");//密码
//                            TeacherEntity.setTeacherName(data.get(0));                    //心理老师姓名 要改的
//                            TeacherEntity.setTeacherPhone(data.get(0));                    //心理老师手机号 要改的
//                            TeacherEntity.setTeacherPhoto("");//详情和图片
//                            TeacherEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            TeacherEntity.setTeacherEmail(data.get(0));                    //电子邮箱 要改的
//                            TeacherEntity.setTeacherShanchang(data.get(0));                    //擅长 要改的
//                            TeacherEntity.setTeacherLvli(data.get(0));                    //履历 要改的
//                            TeacherEntity.setTeacherContent("");//详情和图片
//                            TeacherEntity.setCreateTime(date);//时间
                            TeacherList.add(TeacherEntity);


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
                                //心理老师手机号
                                if(seachFields.containsKey("TeacherPhone")){
                                    List<String> TeacherPhone = seachFields.get("TeacherPhone");
                                    TeacherPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> TeacherPhone = new ArrayList<>();
                                    TeacherPhone.add(data.get(0));//要改的
                                    seachFields.put("TeacherPhone",TeacherPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<TeacherEntity> TeacherEntities_username = TeacherService.selectList(new EntityWrapper<TeacherEntity>().in("username", seachFields.get("username")));
                        if(TeacherEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TeacherEntity s:TeacherEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //心理老师手机号
                        List<TeacherEntity> TeacherEntities_TeacherPhone = TeacherService.selectList(new EntityWrapper<TeacherEntity>().in("Teacher_phone", seachFields.get("TeacherPhone")));
                        if(TeacherEntities_TeacherPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TeacherEntity s:TeacherEntities_TeacherPhone){
                                repeatFields.add(s.getTeacherPhone());
                            }
                            return R.error(511,"数据库的该表中的 [心理老师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        TeacherService.insertBatch(TeacherList);
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
        TeacherEntity Teacher = TeacherService.selectOne(new EntityWrapper<TeacherEntity>().eq("username", username));
        if(Teacher==null || !Teacher.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(Teacher.getId(),username, "Teacher", "心理老师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","心理老师");
        r.put("username",Teacher.getTeacherName());
        r.put("tableName","Teacher");
        r.put("userId",Teacher.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody TeacherEntity Teacher){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<TeacherEntity> queryWrapper = new EntityWrapper<TeacherEntity>()
            .eq("username", Teacher.getUsername())
            .or()
            .eq("Teacher_phone", Teacher.getTeacherPhone())
            ;
        TeacherEntity TeacherEntity = TeacherService.selectOne(queryWrapper);
        if(TeacherEntity != null)
            return R.error("账户或者心理老师手机号已经被使用");
        Teacher.setCreateTime(new Date());
        TeacherService.insert(Teacher);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        TeacherEntity Teacher = new TeacherEntity();
        Teacher.setPassword("123456");
        Teacher.setId(id);
        TeacherService.updateById(Teacher);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        TeacherEntity Teacher = TeacherService.selectOne(new EntityWrapper<TeacherEntity>().eq("username", username));
        if(Teacher!=null){
            Teacher.setPassword("123456");
            boolean b = TeacherService.updateById(Teacher);
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
    public R getCurrTeacher(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        TeacherEntity Teacher = TeacherService.selectById(id);
        if(Teacher !=null){
            //entity转view
            TeacherView view = new TeacherView();
            BeanUtils.copyProperties( Teacher , view );//把实体数据重构到view中

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
        PageUtils page = TeacherService.queryPage(params);

        //字典表数据转换
        List<TeacherView> list =(List<TeacherView>)page.getList();
        for(TeacherView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TeacherEntity Teacher = TeacherService.selectById(id);
            if(Teacher !=null){


                //entity转view
                TeacherView view = new TeacherView();
                BeanUtils.copyProperties( Teacher , view );//把实体数据重构到view中

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
    public R add(@RequestBody TeacherEntity Teacher, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,Teacher:{}",this.getClass().getName(),Teacher.toString());
        Wrapper<TeacherEntity> queryWrapper = new EntityWrapper<TeacherEntity>()
            .eq("username", Teacher.getUsername())
            .or()
            .eq("Teacher_phone", Teacher.getTeacherPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherEntity TeacherEntity = TeacherService.selectOne(queryWrapper);
        if(TeacherEntity==null){
            Teacher.setCreateTime(new Date());
        Teacher.setPassword("123456");
        TeacherService.insert(Teacher);
            return R.ok();
        }else {
            return R.error(511,"账户或者心理老师手机号已经被使用");
        }
    }


}
