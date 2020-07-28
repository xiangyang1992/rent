package com.keith.rent.web.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keith.rent.web.weixinConfig.WeixinUtil;
import com.keith.rent.web.wx.WxDeptTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * description:
 * author:
 */
public class LinkedList_demo {

    private static final Logger logger = LoggerFactory.getLogger(LinkedList_demo.class);

    public static String WEIXIN_QY_API_PORT = "https://qyapi.weixin.qq.com/cgi-bin";


    public static final String department_list = "/department/list?access_token=%s&id=%s";

    public static String deptId = "1";
    public static String  test() {



        try {
            return String.format(WEIXIN_QY_API_PORT + department_list, WeixinUtil.getAccessToken(), deptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static void main(String[] args) {
//        System.out.println(test());

        LinkedList_demo.tes2t();

//        try {
//            URL url = new URL(test());
//            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//            String aReturn = WeixinUtil.getReturn(connection);
//            JSONObject object = JSONObject.parseObject(aReturn);
//            if (object != null && object.containsKey("errcode")) {
//                int errcode = object.getIntValue("errcode");
//                if (0 != errcode) {
//                    if (60003 == errcode || 60123 == errcode) {
//                        logger.info("获取部门失败!，errcode:{},errmsg:{}", errcode, object.get("errmsg"));
//                    }
//                }
//            } else {
//                logger.error("获取所有部门失败,返回信息为空");
//            }
//            System.out.println(getTree(object, deptId));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static WxDeptTree getTree(JSONObject object, String deptId) {
        JSONArray array = JSONArray.parseArray(object.getString("department"));
        if (array == null || array.size() == 0) {
            return null;
        }
        List<WxDeptTree> deptTrees = array.toJavaList(WxDeptTree.class);
        Map<String, WxDeptTree> tempTreeMap = new HashMap<>(array.size());
        WxDeptTree wxDeptTreeRoot = null;
        WxDeptTree node;
        List<WxDeptTree> list = new ArrayList<WxDeptTree>();//用于存在暂时未找到父节点的部门，用于统一放入
        for (int i = 0; i < deptTrees.size(); i++) {
            WxDeptTree wxDeptTree1 = deptTrees.get(i);
            if (wxDeptTree1 != null) {
                tempTreeMap.put(wxDeptTree1.getId(), wxDeptTree1);
                if (wxDeptTree1.getId().equals(deptId)) {
                    wxDeptTreeRoot = wxDeptTree1;
                    continue;
                }
                node = tempTreeMap.get(wxDeptTree1.getParentid());
                if (node != null) {
                    node.addChildrenDept(wxDeptTree1);
                } else {
                    list.add(wxDeptTree1);
                }
            }
        }
        if (wxDeptTreeRoot == null) {
            wxDeptTreeRoot = deptTrees.get(0);
        }
        return wxDeptTreeRoot;
    }


    public static void tes2t() {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
    }



}
