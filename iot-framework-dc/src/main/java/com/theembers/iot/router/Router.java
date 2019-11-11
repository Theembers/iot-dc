package com.theembers.iot.router;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:11
 */
public interface Router {
    /**
     * 获取 路由器
     *
     * @return
     */
    Router initRouter(Selector selector);

    /**
     * 扫描并构建 map
     *
     * @return
     */
    void scanAndBuildMap();

    /**
     * 设置 选择器
     *
     * @param selector
     */
    void setSelector(Selector selector);

    Selector getSelector();

    Map getMap();
}
