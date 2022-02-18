package cn.pac.zeros.WebSocket;

import cn.pac.zeros.Config;
import cn.pac.zeros.Zeros;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWebSocket extends WebSocketServer {

    /**
     * 已验证的客户端
     * @key String 已验证的客户端的HostAddress
     * @key ArrayList<WebSocket> 已验证的客户端WebSocket对象列表
     */
    private static final HashMap<String, ArrayList<WebSocket>> VerifiedHosts = new HashMap<>();

    public MyWebSocket(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    /**
     * 当WebSocket启动所触发的事件
     */
    public void onStart() {
        System.out.println("WebSocket Server Start At Port : " + this.getPort());
    }

    /**
     * 当客户端与WebSocket连接所触发的事件
     * @param conn WebSocket客户端实例
     * @param clientHandshake WebSocket客户端牵手实例
     */
    public void onOpen(WebSocket conn, ClientHandshake clientHandshake) {
        System.out.println("New Connection === " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    /**
     * 当客户端与WebSocket断开连接所触发的事件
     * @param conn WebSocket客户端实例
     * @param code Code
     * @param reason 断开原因
     * @param remote 未知
     */
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("WebSocket Close ! Code: " + code + " Reason: " + reason);
    }

    /**
     * 当WebSocket接收到客户端消息
     * @param conn WebSocket客户端实例
     * @param message 接收到的消息
     */
    public void onMessage(WebSocket conn, String message) {
        if (JSONValidator.from(message).validate()) {
            WebSocketJsonObject JsonObject = JSONObject.parseObject(message, WebSocketJsonObject.class);
            // 验证类型
            if (JsonObject.type != null) {
                // 验证钥匙
                if (JsonObject.type == WebSocketJsonType.Verify) {
                    Config.ConfigObject ConfigObject = Config.GetConfig(); // 获取配置文件
                    String ConfigKey = ConfigObject.WebSocket.Key; // 获取配置文件钥匙
                    // 检测钥匙相同
                    if (JsonObject.data.equals(ConfigKey)) {
                        // 获取客户端地址
                        String HostAddress = conn.getRemoteSocketAddress().getAddress().getHostAddress();
                        boolean Success = false;
                        if (VerifiedHosts.containsKey(HostAddress)) {
                            Success = VerifiedHosts.get(HostAddress).add(conn);

                        } else {
                            ArrayList<WebSocket> Sockets = new ArrayList<>();
                            boolean SocketsAddSuccess = Sockets.add(conn);
                            ArrayList<WebSocket> PutToMapSuccess = VerifiedHosts.put(HostAddress, Sockets);
                            if (PutToMapSuccess == null) {
                                Success = SocketsAddSuccess;
                            }
                        }

                        if (Success) {
                            Zeros.INSTANCE.getLogger().info(String.format("客户端验证成功！客户端地址：%s", HostAddress));
                        }
                    }
                }
            }
        }
    }

    /**
     * 当客户端与WebSocket发生错误
     * @param conn WebSocket客户端实例
     * @param e 错误
     */
    public void onError(WebSocket conn, Exception e) {
        e.printStackTrace();
    }
}
