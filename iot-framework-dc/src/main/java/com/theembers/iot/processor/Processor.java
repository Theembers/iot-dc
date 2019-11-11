package com.theembers.iot.processor;

import java.util.List;

/**
 * 数据处理器
 * I 源数据对象实体
 * O 业务对象实体
 * <p>
 * inputDeserialization 方法 处理 thing data 接入后的协议转换
 * transform 方法 处理基于数据内容的转换、过滤、填充等工作
 * outputSerialization 方法 处理下游应用数据对象的转换 默认实现是java对象 O 序列化为json
 * except 方法 进行异常处理，比如打印错误数据日志 或者 降级存储
 * execute 方法 限定了以上方法的处理流程，作为 DataProcessor 的 root 方法
 * <p>
 * 通过限定这样一套处理逻辑，统一入参、出参数据类型并让业务逻辑集中在 transform 方法中实现，以此为以后的处理模块插件化提供拓展性
 * <p>
 * 定义为这样三个处理
 *
 * @author TheEmbers Guo
 * createTime 2019-11-07 16:29
 */
public interface Processor<I extends Input, O extends Output> {
    /**
     * 数据接口: 接入数据并把数据转换为实体
     *
     * @param tData
     * @return
     */
    List<I> inputDeserialization(ThingData tData);


    /**
     * 路由转发: 基于 thingShadow 选择 handler
     */
    void router();


    /**
     * 数据转换: 基于数据的业务内容进行加工，构建为具体的业务数据
     *
     * @param in
     */
    List<O> transform(ThingData tData, List<I> in);

    /**
     * 数据出口: 构建为统一格式的下游数据
     *
     * @param out
     * @return
     */
    AppData outputSerialization(List<O> out);

    /**
     * 异常
     */
    void except(Exception e, String... msg);

    /**
     * 执行
     *
     * @param tData
     * @return
     */
    AppData execute(ThingData tData);

}
