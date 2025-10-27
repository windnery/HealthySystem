
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
 * 心理咨询预约申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/TeacherOrder")
public class TeacherOrderController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherOrderController.class);

    @Autowired
    private TeacherOrderService TeacherOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService TeacherService;



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
        PageUtils page = TeacherOrderService.queryPage(params);

        //字典表数据转换
        List<TeacherOrderView> list =(List<TeacherOrderView>)page.getList();
        for(TeacherOrderView c:list){
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
        TeacherOrderEntity TeacherOrder = TeacherOrderService.selectById(id);
        if(TeacherOrder !=null){
            //entity转view
            TeacherOrderView view = new TeacherOrderView();
            BeanUtils.copyProperties( TeacherOrder , view );//把实体数据重构到view中

                //级联表
                StudentEntity Student = studentService.selectById(TeacherOrder.getStudentId());
                if(Student != null){
                    BeanUtils.copyProperties( Student , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setStudentId(Student.getId());
                }
                //级联表
                TeacherEntity Teacher = TeacherService.selectById(TeacherOrder.getTeacherId());
                if(Teacher != null){
                    BeanUtils.copyProperties( Teacher , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTeacherId(Teacher.getId());
                }
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
    public R save(@RequestBody TeacherOrderEntity TeacherOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,TeacherOrder:{}",this.getClass().getName(),TeacherOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("心理老师".equals(role))
            TeacherOrder.setTeacherId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("学生".equals(role))
            TeacherOrder.setStudentId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        TeacherOrder.setInsertTime(new Date());
        TeacherOrder.setCreateTime(new Date());
        TeacherOrderService.insert(TeacherOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TeacherOrderEntity TeacherOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,TeacherOrder:{}",this.getClass().getName(),TeacherOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("心理老师".equals(role))
//            TeacherOrder.setTeacherId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("学生".equals(role))
//            TeacherOrder.setStudentId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<TeacherOrderEntity> queryWrapper = new EntityWrapper<TeacherOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherOrderEntity TeacherOrderEntity = TeacherOrderService.selectOne(queryWrapper);
        if(TeacherOrderEntity==null){
            TeacherOrderService.updateById(TeacherOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        TeacherOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<TeacherOrderEntity> TeacherOrderList = new ArrayList<>();//上传的东西
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
                            TeacherOrderEntity TeacherOrderEntity = new TeacherOrderEntity();
//                            TeacherOrderEntity.setTeacherOrderUuidNumber(data.get(0));                    //预约流水号 要改的
//                            TeacherOrderEntity.setTeacherId(Integer.valueOf(data.get(0)));   //心理老师 要改的
//                            TeacherOrderEntity.setStudentId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            TeacherOrderEntity.setYuyueTime(sdf.parse(data.get(0)));          //预约日期 要改的
//                            TeacherOrderEntity.setShijianduanTypes(Integer.valueOf(data.get(0)));   //预约时间段 要改的
//                            TeacherOrderEntity.setTeacherOrderYesnoTypes(Integer.valueOf(data.get(0)));   //预约状态 要改的
//                            TeacherOrderEntity.setTeacherOrderYesnoText(data.get(0));                    //审核意见 要改的
//                            TeacherOrderEntity.setInsertTime(date);//时间
//                            TeacherOrderEntity.setCreateTime(date);//时间
                            TeacherOrderList.add(TeacherOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //预约流水号
                                if(seachFields.containsKey("TeacherOrderUuidNumber")){
                                    List<String> TeacherOrderUuidNumber = seachFields.get("TeacherOrderUuidNumber");
                                    TeacherOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> TeacherOrderUuidNumber = new ArrayList<>();
                                    TeacherOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("TeacherOrderUuidNumber",TeacherOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约流水号
                        List<TeacherOrderEntity> TeacherOrderEntities_TeacherOrderUuidNumber = TeacherOrderService.selectList(new EntityWrapper<TeacherOrderEntity>().in("Teacher_order_uuid_number", seachFields.get("TeacherOrderUuidNumber")));
                        if(TeacherOrderEntities_TeacherOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TeacherOrderEntity s:TeacherOrderEntities_TeacherOrderUuidNumber){
                                repeatFields.add(s.getTeacherOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        TeacherOrderService.insertBatch(TeacherOrderList);
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
        PageUtils page = TeacherOrderService.queryPage(params);

        //字典表数据转换
        List<TeacherOrderView> list =(List<TeacherOrderView>)page.getList();
        for(TeacherOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TeacherOrderEntity TeacherOrder = TeacherOrderService.selectById(id);
            if(TeacherOrder !=null){


                //entity转view
                TeacherOrderView view = new TeacherOrderView();
                BeanUtils.copyProperties( TeacherOrder , view );//把实体数据重构到view中

                //级联表
                    StudentEntity Student = studentService.selectById(TeacherOrder.getStudentId());
                if(Student != null){
                    BeanUtils.copyProperties( Student , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setStudentId(Student.getId());
                }
                //级联表
                    TeacherEntity Teacher = TeacherService.selectById(TeacherOrder.getTeacherId());
                if(Teacher != null){
                    BeanUtils.copyProperties( Teacher , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTeacherId(Teacher.getId());
                }
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
    public R add(@RequestBody TeacherOrderEntity TeacherOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,TeacherOrder:{}",this.getClass().getName(),TeacherOrder.toString());
            TeacherEntity TeacherEntity = TeacherService.selectById(TeacherOrder.getTeacherId());
            if(TeacherEntity == null){
                return R.error(511,"查不到该心理老师");
            }
            // Double TeacherNewMoney = TeacherEntity.getTeacherNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            TeacherOrder.setStudentId(userId); //设置订单支付人id
            TeacherOrder.setTeacherOrderUuidNumber(String.valueOf(new Date().getTime()));
            TeacherOrder.setInsertTime(new Date());
            TeacherOrder.setCreateTime(new Date());
                TeacherOrderService.insert(TeacherOrder);//新增订单
            return R.ok();
    }



}
