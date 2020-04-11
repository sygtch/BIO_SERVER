package com.guo.sky;

/**
 * @author GuoTianchi
 * @version 1.0
 * @date 2020/4/8 13:07
 */
public class App {

    public static void main(String[] args) {
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        ServerService serverService = new ServerServiceImpl();
        rpcProxyServer.publisher(serverService,8080);
    }

}
