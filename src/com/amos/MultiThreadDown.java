package com.amos;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class MultiThreadDown
{
    public static void main(String[] args) throws Exception
    {
        // 初始化DownUtil对象
//        final DownUtil downUtil = new DownUtil("http://www.crazyit.org/"
//                + "attachment.php?aid=MTY0NXxjNjBiYzNjN3wxMzE1NTQ2MjU5fGNhO"
//                + "DlKVmpXVmhpNGlkWmVzR2JZbnluZWpqSllOd3JzckdodXJOMUpOWWt0aTJz,"
//                , "oracelsql.rar", 4);

//        final DownUtil downUtil = new DownUtil("http://mirrors.cnnic.cn/apache/tomcat/tomcat-7/v7.0.54/bin/apache-tomcat-7.0.54.zip", "tomcat-7.0.54.zip", 2);

        final DownUtil downUtil = new DownUtil("http://pic.cnitblog.com/avatar/534352/20131215160918.png", "amosli.png", 2);


        // 开始下载
        downUtil.download();
        new Thread()
        {
            public void run()
            {
                while(downUtil.getCompleteRate() < 1)
                {
                    // 每隔0.1秒查询一次任务的完成进度，
                    // GUI程序中可根据该进度来绘制进度条
                    System.out.println("已完成："
                            + downUtil.getCompleteRate());
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (Exception ex){}
                }
            }
        }.start();
    }
}