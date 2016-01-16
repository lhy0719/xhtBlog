package com.lhy.commons.jmx;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class JMXUtils {
	public static Map<String,Object> getManagementInfo(){
		ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
		ClassLoadingMXBean classLoadingMXBean=ManagementFactory.getClassLoadingMXBean();
		OperatingSystemMXBean operatingSystemMXBean=ManagementFactory.getOperatingSystemMXBean();
		RuntimeMXBean runtimeMXBean=ManagementFactory.getRuntimeMXBean();
		MemoryMXBean memoryMXBean=ManagementFactory.getMemoryMXBean();
		MemoryUsage heapMemoryUsage=memoryMXBean.getHeapMemoryUsage();
		MemoryUsage honHeapMemoryUsage=memoryMXBean.getNonHeapMemoryUsage();		
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("HEAPMEMORY_INIT", Long.valueOf(heapMemoryUsage.getInit()));
		model.put("HEAPMEMORY_MAX", Long.valueOf(heapMemoryUsage.getMax()));
		model.put("HEAPMEMORY_USED", Long.valueOf(heapMemoryUsage.getUsed()));
		model.put("HEAPMEMORY_COMMITTED", Long.valueOf(heapMemoryUsage.getCommitted()));
		model.put("NONHEAPMEMORY_INIT", Long.valueOf(honHeapMemoryUsage.getInit()));
		model.put("NONHEAPMEMORY_MAX", Long.valueOf(honHeapMemoryUsage.getMax()));
		model.put("NONHEAPMEMORY_USED", Long.valueOf(honHeapMemoryUsage.getUsed()));
		model.put("NONHEAPMEMORY_COMMITTED", Long.valueOf(honHeapMemoryUsage.getCommitted()));
		model.put("VM_STARTTIME", new Date(runtimeMXBean.getStartTime()));
		model.put("VM_UPTIME",Long.valueOf(runtimeMXBean.getUptime()));
		model.put("VM_MANAGEMENTSPECVERSION", runtimeMXBean.getManagementSpecVersion());
		model.put("VM_NAME", runtimeMXBean.getVmName());
		model.put("VM_VENDOR", runtimeMXBean.getVmVendor());
		model.put("VM_VERSION", runtimeMXBean.getVmVersion());		
		model.put("VM_THREADCOUNT",Long.valueOf(threadMXBean.getThreadCount()));
		model.put("VM_TOTALSTARTEDTHREADCOUNT",Long.valueOf(threadMXBean.getTotalStartedThreadCount()));
		
		model.put("OS_ARCH", operatingSystemMXBean.getArch());
		model.put("OS_AVAILABLE_PROCESSORS", Integer.valueOf(operatingSystemMXBean.getAvailableProcessors()));
		model.put("OS_NAME", operatingSystemMXBean.getName());
		model.put("OS_VERSION", operatingSystemMXBean.getVersion());
		
		model.put("CLASSLOADING_LOADEDCLASSCOUNT",classLoadingMXBean.getLoadedClassCount());
		model.put("CLASSLOADING_TOTALLOADEDCLASSCOUNT",classLoadingMXBean.getTotalLoadedClassCount());
		return model;
	}
	public static String formatDuring(long mss,String pattern) {  
	    long days = mss / (1000 * 60 * 60 * 24);  
	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
	    long seconds = (mss % (1000 * 60)) / 1000;	    
	    return MessageFormat.format(pattern,days,hours,minutes,seconds);
	} 
}
