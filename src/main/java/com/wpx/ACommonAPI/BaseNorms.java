package com.wpx.ACommonAPI;

import com.wpx.ACommonAPI.Constants.JQGRID.CommonPageConstants;
import com.wpx.ACommonAPI.Constants.KindEditor.CommonKindEditorImg;
import com.wpx.ACommonAPI.Constants.KindEditor.CommonKindEditorImgProperty;
import com.wpx.ACommonAPI.Constants.KindEditor.CommonKindEditorImgUpload;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wpx
 * @Date: 2019/10/25 20:46
 * @Version: V_1.0.0
 */
public class BaseNorms {



    public Map<String, List<Integer>> setEchartsResult(List<Integer> men, List<Integer> woMen) {
        Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
        result.put("men",men);
        result.put("woMen",woMen);
        return result;
    }

    /**
     * 方法描述: (自定义返回 根据分页查询结果)
     *
     * param code    状态码
     * param msg     携带信息
     * @param data    数据
     * @param page    当前页码
     * @param total   总页数
     * @param records 总条数
     * @return Map<String,Object>
     */
    public Map<String, Object> setResult(Object data, Integer page, Integer total, Integer records) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonPageConstants.PAGE_PAGE_PAGE,page);
        result.put(CommonPageConstants.PAGE_PAGE_TOTAL,total);
        result.put(CommonPageConstants.PAGE_PAGE_RECORDS,records);
        result.put(CommonPageConstants.PAGE_PAGE_ROWS,data);
        return result;
    }


    /**
     *
     * KindEditor富文本编辑器图片空间常量：
     *
     * @Author: wpx
     * @Description:
     * @Date: 15:37 2019/10/30
     * @param file_list
     * @param moveup_dir_path
     * @param current_dir_path
     * @param current_url
     * @param total_count
     */
    public Map<String , Object> setResult(Object file_list, String moveup_dir_path,String current_dir_path,String current_url,Integer total_count ){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonKindEditorImg.IMG_IMG_MOVEUP_DIR_PATH,moveup_dir_path);
        result.put(CommonKindEditorImg.IMG_IMG_CURRENT_DIR_PATH,current_dir_path);
        result.put(CommonKindEditorImg.IMG_IMG_CURRENT_URL,current_url);
        result.put(CommonKindEditorImg.IMG_IMG_TOTAL_COUNT,total_count);
        result.put(CommonKindEditorImg.IMG_IMG_FILE_LIST,file_list);
        return result;
    }

    /**
     * KindEditor获取当前项目中的图片库的8种属性：
     *
     * @Author: wpx
     * @Description:
     * @Date: 16:29 2019/10/30
     * @param fileSize  文件大小
     * @param fileType  文件类型
     * @param fileName  文件名
     */
    public Map<String , Object> setResult(long fileSize , String fileType , String fileName){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonKindEditorImgProperty.IMG_IMG_IS_DIR,false);
        result.put(CommonKindEditorImgProperty.IMG_IMG_HAS_FILE,false);
        result.put(CommonKindEditorImgProperty.IMG_IMG_FILESIZE,fileSize);
        //result.put(CommonKindEditorImgProperty.IMG_IMG_DIR_PATH,"");
        result.put(CommonKindEditorImgProperty.IMG_IMG_IS_PHOTo,true);
        result.put(CommonKindEditorImgProperty.IMG_IMG_FILETYPE,fileType);
        result.put(CommonKindEditorImgProperty.IMG_IMG_FILENAME,fileName);
        result.put(CommonKindEditorImgProperty.IMG_IMG_DATETIME,new Date());
        return result;
    }

    /**
     * 文件上传：
     *
     * @Author: wpx
     * @Description:
     * @Date: 18:40 2019/10/30
     * @param url     //url
     * @param error  //返回响应信息   0 提交成功
     */
    public Map<String , Object> setResult(String url,Integer error){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonKindEditorImgUpload.IMG_IMG_URL,url);
        result.put(CommonKindEditorImgUpload.IMG_IMG_ERROR,error);
        return result;
    }
    /**
     * 文件上传：
     *
     * @Author: wpx
     * @Description:
     * @Date: 18:40 2019/10/30
     * @param error  //返回响应信息   0 提交成功
     */
    public Map<String , Object> setResult(Integer error){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonKindEditorImgUpload.IMG_IMG_ERROR,error);
        return result;
    }
}
