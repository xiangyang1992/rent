package com.keith.rent.core.vo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @desc: 登录接口封装对象
 * @author: 向阳
 * @date: 2019/12/22
 * @version:
 */
public class LoginBean {

    @NotNull(message = "账号不能为空")
    @Size(min = 1,max = 15,message = "账号长度必须是1-15个字符")
    private String accout;
    @NotNull(message = "密码不能为空")
    @Size(min = 5,max = 15,message = "密码长度必须是5-15个字符")
    private String password;
    private String nickName;
    private String phone;
//    @NotNull(message = "验证码已失效")
    private String captcha;

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
