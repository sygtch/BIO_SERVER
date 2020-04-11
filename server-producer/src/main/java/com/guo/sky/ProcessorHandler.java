package com.guo.sky;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author GuoTianchi
 * @version 1.0
 * @date 2020/4/8 13:48
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object instance;

    public ProcessorHandler(Socket socket, Object instance) {
        this.socket = socket;
        this.instance = instance;
    }

    public void run() {
        InputStream socketInputStream = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            socketInputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(socketInputStream);
            Request request = (Request)objectInputStream.readObject();
            Object obj = invoke(request);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object invoke(Request request){
        String clazzName = request.getClazzName();
        String methodName = request.getMethod();
        Object[] params = request.getParams();
        Class<?>[] types = null;
        if (null != request.getParams()){
            types = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                types[i] = params[i].getClass();
            }
        }
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(clazzName);
            Method method = clazz.getMethod(methodName,types);
            obj = method.invoke(instance, params);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
