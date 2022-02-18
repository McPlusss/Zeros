package cn.pac.zeros;

import cn.pac.zeros.File.FileReader;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 配置文件
 *
 * @author NyanCatda
 * @date 2022/02/17
 */
public class Config {
    public static File ConfigFileObject = new File("./config/cn.pac.zeros/Config.yml");

    /**
     * 配置对象
     *
     * @author NyanCatda
     * @date 2022/02/17
     */
    public static class ConfigObject {
        public GroupObject Group;
        public static class GroupObject {
            public ArrayList<Integer> Admin;// 管理员群组
            public ArrayList<Integer> Common;// 交流群组

            public GroupObject() {
                this.Admin = new ArrayList<>();
                this.Common = new ArrayList<>();
            }
        }

        public ServerObject Server;
        public static class ServerObject {
            public String ServerPath; // 服务器路径

            public ServerObject() {
                this.ServerPath = "";
            }
        }

        public WebSocketObject WebSocket;
        public static class WebSocketObject {
            public int Port; // 端口
            public String Key; // Key

            public WebSocketObject() {
                this.Port = 8888;
                this.Key = "";
            }
        }

        public ConfigObject() {
            this.Group = new GroupObject();
            this.Server = new ServerObject();
            this.WebSocket = new WebSocketObject();
        }
    }


    /**
     * 读取配置文件
     *
     * @return {@link ConfigObject}
     */
    public static ConfigObject GetConfig() {
        String ConfigYml = FileReader.ReadFile(ConfigFileObject);
        Yaml Yaml = new Yaml();
        return Yaml.loadAs(ConfigYml, ConfigObject.class);
    }

    /**
     * 创建配置文件
     *
     * @return boolean
     */
    public static boolean CreateConfig() {
        // 判断配置文件是否存在
        if (!ConfigFileObject.exists()) {
            File Folder = new File("./config/cn.pac.zeros");
            if (!Folder.exists()) Folder.mkdirs();
            try {
                //创建配置文件
                ConfigObject Config = new ConfigObject();

                Yaml yaml = new Yaml();

                // 将Java对象转换为Yaml字符串
                String StringYaml = yaml.dumpAs(Config, Tag.MAP, DumperOptions.FlowStyle.BLOCK);

                // 写入文件
                FileWriter ConfigWriter = new FileWriter(ConfigFileObject, StandardCharsets.UTF_8);
                BufferedWriter ConfigBufferedWriter = new BufferedWriter(ConfigWriter);
                ConfigBufferedWriter.write(StringYaml);
                ConfigBufferedWriter.close();

                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return true;
        }
    }
}
