package com.huatek.core.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.core.contants.SessionConstant;
import com.huatek.core.entity.SysDept;
import com.huatek.core.entity.SysUser;			
import com.huatek.core.security.SessionThreadLocal;
import com.huatek.core.service.SysDeptService;
import com.huatek.core.service.SysUserService;
import com.huatek.core.vo.R;



@RestController
@RequestMapping(value = "/sys")
public class LoginController {
   
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysDeptService sysDeptService;
	
	
	
	@RequestMapping(value = "/login")
	public R login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		SysUser sysUser = sysUserService.findUserByNameAndPwd(username, password);
		if(null == sysUser){
			return R.error("用户名或者密码错误.");
		}
		if(null != sysUser.getDeptId()){
			SysDept findByPk = sysDeptService.findByPk(sysUser.getDeptId());
			if(null != findByPk){
				sysUser.setSysDept(findByPk);
				sysUser.setDeptName(findByPk.getName());
			}
		}
		if(null != sysUser.getCompany()){
			SysDept findByPk = sysDeptService.findByPk(sysUser.getCompany());
			if(null != findByPk){
				sysUser.setCompanyName(findByPk.getName());
			}
		}
		session.setAttribute(SessionConstant.USER_SESSION, sysUser); //存入session
		SessionThreadLocal.setUser(sysUser);
		String token = UUID.randomUUID().toString().replace("-", "");
		return R.success("登录成功").put("token", token).put("user", sysUser);
	}
						
	@RequestMapping(value = "/logout")
	public R logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.removeAttribute(SessionConstant.USER_SESSION); //删除session
		SessionThreadLocal.remove();
		return R.success();
	}
	
}
