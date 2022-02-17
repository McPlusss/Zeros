package cn.pac.zeros;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

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

        //创建一个配置文件
        Config Config = new Config();
        if (!Config.CreateConfig()) {
            getLogger().info("配置文件创建失败");
        }
    }
}