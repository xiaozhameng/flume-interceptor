package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志ETL拦截器
 *
 */
public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 获取数据
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);

        // 2 判断数据类型并向Header中赋值
        if (log.contains("start")) {
            if (LogUtils.validateStart(log)){
                return event;
            }
        }else {
            if (LogUtils.validateEvent(log)){
                return event;
            }
        }

        // 返回数据
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        List<Event> events = new ArrayList<>();
        for (Event event : list) {
            Event intercept = this.intercept(event);
            if (intercept != null){
                events.add(intercept);
            }
        }
        return events;
    }

    @Override
    public void close() {

    }

    public class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
