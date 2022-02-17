package cn.pac.zeros;

import cn.pac.zeros.File.FileReader;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 配置文件
 *
 * @author NyanCatda
 * @date 2022/02/17
 */
public class Config {
    public static File ConfigFileObject = new File("./config/cn.pac.zeros/config.yml");

    /**
     * 配置对象
     *
     * @author NyanCatda
     * @date 2022/02/17
     */
    public class ConfigObject {
        public Group Group;
        public class Group {
            public int[] Admin;// 管理员群组
            public int[] Common;// 交流群组
        }
        public Server Server;
        public class Server {
            public String ServerPath; // BDS地址
        }
        public WebSocket WebSocket;
        public class WebSocket {
            public String Host; // 地址
            public int Port; // 端口
            public String Key; // Key
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
    public boolean CreateConfig() {
        // 判断配置文件是否存在
        if (!ConfigFileObject.exists()) {
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
