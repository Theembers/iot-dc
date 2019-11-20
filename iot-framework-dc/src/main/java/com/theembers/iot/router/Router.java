package com.theembers.iot.router;

import com.theembers.iot.router.selector.Selector;

import java.util.Map;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-11 10:11
 */
public interface Router<P> {
    /**
     * 初始化 路由器
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

    Map<String, P> getMap();
}
