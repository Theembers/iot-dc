## IoT-DC framework

> a IoT data collector framework and power by springboot+netty+rabbitmq/kafka
>
> 一个基于 springboot+netty+rabbitmq 实现的 物联网设备(IoT) 数据接入的项目

> **src**: https://github.com/Theembers/com.theembers.iot-dc
>
> **thanks for star! :) ⭐⭐⭐⭐⭐**

**the obsolete version (branch:s-0.1) will be not update yet (except bug fix), the master will be rebuild as new one. thanks follow!**

**旧版本以封版（分支s-0.1）不再更新（除了bug修改），master 分支将启用新的设计，敬请期待！**

[branch:s-0.1](https://github.com/Theembers/iot-dc/tree/s-0.1)  



## IoT platform framework (IoT平台总体架构设计)

![设备总体接入架构](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20190926173357.jpg)

## about the new IoT-DC Framework (关于新版 IoT-DC Framework)

抽离了netty的实现逻辑，保留了基础框架

- iot-framework-dc 抽象了基础的接入逻辑模型。如下图：

![iot-framework-dc 模型](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20191111134357.jpg)

- router 模型 & device shadow 模型

  路由器 (router)，把接入的数据分发到符合规则 (rule) 的处理器 (processor) 中进行处理。
![router 模型](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/20191111131757.jpg)

- processor-link 模型

  router 通过选择器 (Selector) 选择出的导航 (Route) 维护了一个调度者 (Dispatcher) 调度者负责管理被使用的 processor链 以及 processor 的调用 规则:

![processor-link 模型](https://image-1257148187.cos.ap-chengdu.myqcloud.com/picgo_img/processor-link-1.jpg)

```java
/**
 * 执行
 * 如果 当前processor是头节点，调用 headIn （ 调用 beforeTransform & transForm）
 * 否则 （中间节点 或者 尾节点） 调用 receive（接收）
 * 最终 如果 是尾结点 则 调用 tailOut （调用 afterTransform） 并 退出循环
 * 到 //1 则 调用 buildSlotData （构建插槽）
 *
 * @param shadow
 * @param sourceData
 */
void run(Shadow shadow, SourceData sourceData) {
    Output output = null;
    SlotData slotData = null;
    Iterator<Processor> processors = this.link.iterator();
    while (processors.hasNext()) {
        Processor p = processors.next();
        if (p == getFirst()) {
            output = p.headIn(shadow, sourceData);
        } else {
            output = p.receive(shadow, slotData);
        }
        if (p == getLast()) {
            output = p.tailOut(shadow, output);
            return;
        }
        slotData = p.passOn(shadow, output); // 1
    }
}
```

- iot-dc-netty-server 是之前的netty实现，目前（2019-11-07）只是迁移了老模块的代码，后期会修改结构，所以暂不做架构说明。新分支侧着架构设计，所以具体实现可能要延后实现。

