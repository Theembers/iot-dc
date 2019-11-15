package me.theembers.iot;

import com.theembers.iot.processor.Processor;
import com.theembers.iot.router.AbstractRouter;
import com.theembers.iot.router.AutoSelector;
import me.theembers.iot.bizprocessor.Test2Processor;
import me.theembers.iot.bizprocessor.TestProcessor;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-15 14:40
 */
public class TestRouter extends AbstractRouter<Processor> {
    public TestRouter() {
        initRouter(new AutoSelector(this));
    }

    @Override
    public void scanAndBuildMap() {
        PROCESSOR_MAP.put("TestProcessor", new TestProcessor());
        PROCESSOR_MAP.put("Test2Processor", new Test2Processor());
    }
}
