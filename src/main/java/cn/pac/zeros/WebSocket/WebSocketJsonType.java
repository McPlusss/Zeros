package cn.pac.zeros.WebSocket;

import cn.pac.zeros.Config;
import cn.pac.zeros.Zeros;
import org.java_websocket.WebSocket;

public enum WebSocketJsonType { // WebSocket消息类型
    Verify { // 钥匙验证
        @Override
        public void handle(String Data, WebSocket Socket) {
            // 验证钥匙
            Config.ConfigObject ConfigObject = Config.GetConfig(); // 获取配置文件
            String ConfigKey = ConfigObject.WebSocket.Key; // 获取配置文件钥匙
            // 检测钥匙相同
            if (Data.equals(ConfigKey)) {
                // 获取客户端地址
                String HostAddress = Socket.getRemoteSocketAddress().getAddress().getHostAddress();
                boolean Success = MyWebSocket.AddHostAndWebSocketToList(HostAddress, Socket);

                if (Success) {
                    Zeros.INSTANCE.getLogger().info(String.format("客户端验证成功！客户端地址：%s", HostAddress));
                }
            }
        }
    },
    MessageOnLocalServer { // 通过机器人所启动的服务器消息
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    StartLocalServer { // 启动机器人配置文件所制定的服务端
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    StopLocalServer { // 关闭机器人配置文件所制定的服务端
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    // 通过WebSocket连接服务器所触发的事件
    // 玩家事件
    onPlayerJoin { // 当玩家加入世界
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    onPlayerLeft { // 当玩家离开游戏
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    // 其他事件
    onConsoleOutput { // 当控制台产生命令输出
        @Override
        public void handle(String Data, WebSocket Socket) {

        }
    },
    onServerStart { // 当服务器启动
        @Override
        public void handle(String Data, WebSocket Socket) {
        }
    };

    public abstract void handle(String Data, WebSocket Socket);//定义抽象方法
}
