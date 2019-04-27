package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.result.SocketResult;
import com.example.phoebe.youtiao.commmon.enums.SocketEnum;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/socket/{uid}")
@Slf4j
@Component
public class WebSocketServer {
    Gson gson = new Gson();
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String uid="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("uid") String uid) {
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线数加1
        this.uid = uid;

        try {
            SocketResult result = new SocketResult();
            result.setMessage("连接成功");
            result.setType(SocketEnum.SUCCESS_CONNECT_CONTENT.getType());
            sendMessage(result);
        } catch (IOException e) {
            log.error("websocket IO exception");
        } catch (EncodeException e) {
            log.error("socketResult encode fail");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+uid+"的信息:"+message);
//群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                SocketResult result = new SocketResult();
                result.setType(SocketEnum.CLIENT_MESSAGE.getType());
                result.setMessage(message);
                item.sendMessage(result);
            } catch (IOException | EncodeException e) {
                log.warn("WebSocketServer.onMessage error");
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */

    public void sendMessage(SocketResult message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendText(gson.toJson(message, SocketResult.class));
    }


    /**
     * 群发自定义消息
     * */
    public static boolean sendInfo(SocketResult message, String uid) throws IOException {
        log.info("推送消息到窗口" + uid + "，推送内容:" + message);
        boolean flag = false;

        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个uid的，为null则全部推送
                if (uid == null) {
                    item.sendMessage(message);
                    flag = true;
                } else if (item.uid.equals(uid)) {
                    item.sendMessage(message);
                    flag = true;
                }
            } catch (IOException | EncodeException e) {
                log.warn("sendInfo error e:{}", e);
                log.warn("WebSocketServer.sendInfo error");
            }
        }
        return flag;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

