package cn.pac.zeros.WebSocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MyWebSocket extends WebSocketServer {
    public MyWebSocket() throws UnknownHostException {
    }

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
