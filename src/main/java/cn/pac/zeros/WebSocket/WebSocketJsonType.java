package cn.pac.zeros.WebSocket;

public enum WebSocketJsonType { // WebSocket消息类型
    Verify, // 钥匙验证
    MessageOnLocalServer, // 通过机器人所启动的服务器消息
    StartLocalServer, // 启动机器人配置文件所制定的服务端
    StopLocalServer, // 关闭机器人配置文件所制定的服务端
    // 通过WebSocket连接服务器所触发的事件
    // 玩家事件
    onPlayerJoin, // 当玩家加入世界
    onPlayerLeft, // 当玩家离开游戏
    // 其他事件
    onConsoleOutput, // 当控制台产生命令输出
    onServerStart // 当服务器启动
}
