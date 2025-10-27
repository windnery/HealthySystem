
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
 * 心理老师收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/TeacherCollection")
public class TeacherCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherCollectionController.class);

    @Autowired
    private TeacherCollectionService TeacherCollectionService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
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
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("心理老师".equals(role))
            params.put("TeacherId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = TeacherCollectionService.queryPage(params);

        //字典表数据转换
        List<TeacherCollectionView> list =(List<TeacherCollectionView>)page.getList();
        for(TeacherCollectionView c:list){
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
        TeacherCollectionEntity TeacherCollection = TeacherCollectionService.selectById(id);
        if(TeacherCollection !=null){
            //entity转view
            TeacherCollectionView view = new TeacherCollectionView();
            BeanUtils.copyProperties( TeacherCollection , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(TeacherCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                TeacherEntity Teacher = TeacherService.selectById(TeacherCollection.getTeacherId());
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
    public R save(@RequestBody TeacherCollectionEntity TeacherCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,TeacherCollection:{}",this.getClass().getName(),TeacherCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("心理老师".equals(role))
            TeacherCollection.setTeacherId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("学生".equals(role))
            TeacherCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<TeacherCollectionEntity> queryWrapper = new EntityWrapper<TeacherCollectionEntity>()
            .eq("Teacher_id", TeacherCollection.getTeacherId())
            .eq("yonghu_id", TeacherCollection.getYonghuId())
            .eq("Teacher_collection_types", TeacherCollection.getTeacherCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherCollectionEntity TeacherCollectionEntity = TeacherCollectionService.selectOne(queryWrapper);
        if(TeacherCollectionEntity==null){
            TeacherCollection.setInsertTime(new Date());
            TeacherCollection.setCreateTime(new Date());
            TeacherCollectionService.insert(TeacherCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TeacherCollectionEntity TeacherCollection, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,TeacherCollection:{}",this.getClass().getName(),TeacherCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("心理老师".equals(role))
//            TeacherCollection.setTeacherId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("学生".equals(role))
//            TeacherCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<TeacherCollectionEntity> queryWrapper = new EntityWrapper<TeacherCollectionEntity>()
            .notIn("id",TeacherCollection.getId())
            .andNew()
            .eq("Teacher_id", TeacherCollection.getTeacherId())
            .eq("yonghu_id", TeacherCollection.getYonghuId())
            .eq("Teacher_collection_types", TeacherCollection.getTeacherCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherCollectionEntity TeacherCollectionEntity = TeacherCollectionService.selectOne(queryWrapper);
        if(TeacherCollectionEntity==null){
            TeacherCollectionService.updateById(TeacherCollection);//根据id更新
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
        TeacherCollectionService.deleteBatchIds(Arrays.asList(ids));
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
            List<TeacherCollectionEntity> TeacherCollectionList = new ArrayList<>();//上传的东西
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
                            TeacherCollectionEntity TeacherCollectionEntity = new TeacherCollectionEntity();
//                            TeacherCollectionEntity.setTeacherId(Integer.valueOf(data.get(0)));   //心理老师 要改的
//                            TeacherCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            TeacherCollectionEntity.setTeacherCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            TeacherCollectionEntity.setInsertTime(date);//时间
//                            TeacherCollectionEntity.setCreateTime(date);//时间
                            TeacherCollectionList.add(TeacherCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        TeacherCollectionService.insertBatch(TeacherCollectionList);
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
        PageUtils page = TeacherCollectionService.queryPage(params);

        //字典表数据转换
        List<TeacherCollectionView> list =(List<TeacherCollectionView>)page.getList();
        for(TeacherCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TeacherCollectionEntity TeacherCollection = TeacherCollectionService.selectById(id);
            if(TeacherCollection !=null){


                //entity转view
                TeacherCollectionView view = new TeacherCollectionView();
                BeanUtils.copyProperties( TeacherCollection , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(TeacherCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    TeacherEntity Teacher = TeacherService.selectById(TeacherCollection.getTeacherId());
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
    public R add(@RequestBody TeacherCollectionEntity TeacherCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,TeacherCollection:{}",this.getClass().getName(),TeacherCollection.toString());
        Wrapper<TeacherCollectionEntity> queryWrapper = new EntityWrapper<TeacherCollectionEntity>()
            .eq("Teacher_id", TeacherCollection.getTeacherId())
            .eq("yonghu_id", TeacherCollection.getYonghuId())
            .eq("Teacher_collection_types", TeacherCollection.getTeacherCollectionTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TeacherCollectionEntity TeacherCollectionEntity = TeacherCollectionService.selectOne(queryWrapper);
        if(TeacherCollectionEntity==null){
            TeacherCollection.setInsertTime(new Date());
            TeacherCollection.setCreateTime(new Date());
        TeacherCollectionService.insert(TeacherCollection);
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }


}
