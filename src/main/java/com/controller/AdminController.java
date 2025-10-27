
package com.controller;


import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.AdminEntity;
import com.service.TokenService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 登录相关
 */
@RequestMapping("Admin")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@PostMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		AdminEntity user = adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(user.getId(),username, "Admin", user.getRole());
		R r = R.ok();
		r.put("token", token);
		r.put("role",user.getRole());
		r.put("userId",user.getId());
		return r;
	}
	
	/**
	 * 注册
	 */
	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody AdminEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("学生已存在");
    	}
        adminService.insert(user);
        return R.ok();
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
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	AdminEntity user = adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
    	user.setPassword("123456");
        adminService.update(user,null);
        return R.ok("密码已重置为：123456");
    }
	
	/**
     * 列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, AdminEntity user){
        EntityWrapper<AdminEntity> ew = new EntityWrapper<AdminEntity>();
    	PageUtils page = adminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/list")
    public R list( AdminEntity user){
       	EntityWrapper<AdminEntity> ew = new EntityWrapper<AdminEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user")); 
        return R.ok().put("data", adminService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        AdminEntity user = adminService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 获取学生的session学生信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Integer id = (Integer)request.getSession().getAttribute("userId");
        AdminEntity user = adminService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody AdminEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("学生已存在");
    	}
    	user.setPassword("123456");
        adminService.insert(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AdminEntity user){
//        ValidatorUtils.validateEntity(user);
        adminService.updateById(user);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        adminService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
