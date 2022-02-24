package cn.pac.zeros.Regular;

import cn.pac.zeros.File.FileReader;
import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取正则文件
 *
 * @author NyanCatda
 * @date 2022/02/24
 */
public class RegularFile {
    /**
     * 正则文件对象
     */
    public static File RegularFileObject = new File("./data/cn.pac.zeros/Regular.json");

    /**
     * 正则文件结构体
     *
     * @author NyanCatda
     * @date 2022/02/24
     */
    public static class RegularFileStruct {
        public Boolean Admin; // 是否需要群管理权限
        public String ReceiveMessageSource; // 接受消息的来源，可选Server，Group
        public String ReceiveMessageRegular; // 接受消息的匹配正则
        public ArrayList<SendMessageGroupObject> SendMessageGroup; // 发送消息组
        /**
         * 发送消息组
         *
         * @author NyanCatda
         * @date 2022/02/24
         */
        public static class SendMessageGroupObject {
            public String Message; // 消息
            public String SendTo; // 发送至，可选Server，Group
        }
    }

    /**
     * 获取正则文件信息
     *
     * @return {@link List}<{@link RegularFileStruct}>
     */
    public static List<RegularFileStruct> GetRegularFile() {
        String RegularJson = FileReader.ReadFile(RegularFileObject);
        List<RegularFileStruct> RegularData = JSONArray.parseArray(RegularJson, RegularFileStruct.class);
        return RegularData;
    }
}
