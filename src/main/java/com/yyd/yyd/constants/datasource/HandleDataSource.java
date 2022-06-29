package com.yyd.yyd.constants.datasource;


public class HandleDataSource {
    public static final ThreadLocal<ReadSlave> holder = new ThreadLocal<ReadSlave>();

    /**
     * 绑定当前线程数据源
     *
     */
    public static void putDataSource(ReadSlave readSlave)
    {
        holder.set(readSlave);
    }

    /**
     * 获取当前线程的数据源
     *
     * @return
     */
    public static ReadSlave getDataSource()
    {
        return holder.get();
    }
}
