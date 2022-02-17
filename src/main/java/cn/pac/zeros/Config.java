package cn.pac.zeros;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

/**
 * 配置文件
 *
 * @author NyanCatda
 * @date 2022/02/17
 */
public class Config {
    public static String ConfigPath = "./config/cn.pac.zeros/config.yml";

    /**
     * 配置对象
     *
     * @author NyanCatda
     * @date 2022/02/17
     */
    public class ConfigObject {
        public Group Group;
        public class Group {

        }
    }


    /**
     * 读取配置文件
     *
     * @return {@link ConfigObject}
     */
    public static ConfigObject GetConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = Config.class
                .getClassLoader()
                .getResourceAsStream(ConfigPath);
        ConfigObject Config = yaml.load(inputStream);
        return Config;
    }

    /**
     * 创建配置文件
     *
     * @return boolean
     */
    public boolean CreateConfig() {
        File file = new File(ConfigPath);
        // 判断配置文件是否存在
        if (!file.exists()) {
            try {
                //创建配置文件
                ConfigObject Config = new ConfigObject();
                Yaml yaml = new Yaml();
                yaml.dumpAs(Config,Tag.MAP, DumperOptions.FlowStyle.BLOCK);
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return true;
        }
    }
}
