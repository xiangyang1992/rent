package com.keith.rent.core.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.keith.rent.core.annotation.InsertLog;
import com.keith.rent.core.constants.SysConstants;
import com.keith.rent.core.entity.SysUser;
import com.keith.rent.core.service.SysUserService;
import com.keith.rent.core.utils.HttpResult;
import com.keith.rent.core.utils.PasswordEncoder;
import com.keith.rent.core.utils.PasswordUtil;
import com.keith.rent.core.vo.LoginBean;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {


    @Autowired
    Producer producer;

    @Autowired
    SysUserService sysUserService;

    /**
     * @desc: 谷歌验证码生成插件
     * @author: 向阳
     * @date: 2020/5/4
     * @version:
     */
    @InsertLog(value = "验证码", module = "登录")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }


    //登录接口(验证用户名，密码，验证码)
    @PostMapping("login")
    public HttpResult login(@RequestBody @Valid LoginBean loginBean,
                            HttpServletRequest request, HttpSession session) {
        String username = loginBean.getAccout();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        //从session 中获取之前保存的验证码跟前台传来的验证码匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(captcha)) {
            return HttpResult.error("验证码不正确");
        }
        //用户信息
        SysUser sysUser = sysUserService.queryByName(username);
        if (sysUser == null) {
            return HttpResult.error("用户不存在");
        }
        if (!PasswordUtil.matches(SysConstants.salt,password,sysUser.getPassword())) {
            return HttpResult.error("密码不正确");
        } else {
            session.setAttribute("user", loginBean.getAccout());
            return HttpResult.ok("登录成功");
        }
    }


    //退出登录
    @InsertLog(value = "退出登录", module = "登录")
    @PostMapping("/logOut")
    public HttpResult logOut(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, "");
        return HttpResult.ok("退出成功");
    }

    //注册
    @PostMapping("/register")
    public HttpResult register(@RequestBody LoginBean loginBean) {
        String userName = loginBean.getAccout();
        String password = loginBean.getPassword();
        String nickName = loginBean.getNickName();
        String phone = loginBean.getPhone();
        if (userName == null) {
            return HttpResult.error("账号不能为空！");
        }
        SysUser user = sysUserService.queryByName(userName);
        if (user != null) {
            return HttpResult.error("用户账号已存在！");
        }
        PasswordEncoder pe = new PasswordEncoder(SysConstants.salt, "MD5");
        String encPass = pe.encode(password);
        SysUser newUser = new SysUser();
        newUser.setUname(userName);
        newUser.setNickname(nickName);
        newUser.setPassword(encPass);
        newUser.setPhone(phone);
        int flag = sysUserService.insert(newUser);
        if (flag == 1) {
            return HttpResult.ok("新增成功");
        } else {
            return HttpResult.error("新增失败");
        }
    }
}
