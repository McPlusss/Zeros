package cn.pac.zeros;

import cn.pac.zeros.WebSocket.MyWebSocket;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import java.net.UnknownHostException;

public final class Zeros extends JavaPlugin {
    public static final Zeros INSTANCE = new Zeros();

    private Zeros() {
        super(new JvmPluginDescriptionBuilder("cn.pac.zeros", "1.0.0")
                .name("Zeros")
                .author("McPlus, NyanCatda")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");

        if (!Config.CreateConfig()) {
            getLogger().info("配置文件创建失败");
        } else {
            Config.ConfigObject ConfigObject = Config.GetConfig();
            if (ConfigObject.WebSocket.Key.equals("")) {
                getLogger().info("检测到配置文件中WebSocket服务器的验证钥匙为空，请编辑后重启机器人");
            } else {
                try {
                    int Port = ConfigObject.WebSocket.Port;
                    MyWebSocket WebSocket = new MyWebSocket(Port);
                    WebSocket.start();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}