package com.zazbug.blog.entity;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

public class IpUtils {
	private static final String LOCAL_IP = "127.0.0.1";

	public static String getMyIp(){
		String localIp = null; // 本地IP,如果没有配置外网IP则返回它
		String netIp = null; // 外网Ip

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false; // 是否找到外网Ip
			while (interfaces.hasMoreElements() && !finded){
				NetworkInterface networkInterface = interfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()){
					ip = inetAddresses.nextElement();
					if(!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1){ // 外网ip
						netIp = ip.getHostAddress();
						finded = true;
						break;
					}else if(ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1){ // 内网ip
						localIp = ip.getHostAddress();
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		if(netIp != null && !"".equals(netIp)){
			return netIp;
		}else{
			return localIp;
		}

	}

	public static String getIpAddr(HttpServletRequest request) {

		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				}catch (Exception e){
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}

		// 通过多个代理的情况,第一个ip为客户端真实IP
		if(ip != null && ip.length() > 15){
			if(ip.indexOf(",") > 0){
				ip = ip.substring(0,ip.indexOf(","));
			}
		}

		return ip;
	}

	public static boolean internalIp(String ip) {
		boolean res = false;
		byte[] addr = textToNumericFormatV4(ip);
		if (addr != null && ip != null) {
			res = internalIp(addr) || LOCAL_IP.equals(ip);
		}
		return res;
	}

	private static boolean internalIp(byte[] addr) {
		final byte b0 = addr[0];
		final byte b1 = addr[1];
		// 10.x.x.x/8
		final byte SECTION_1 = 0x0A;
		// 172.16.x.x/12
		final byte SECTION_2 = (byte) 0xAC;
		final byte SECTION_3 = (byte) 0x10;
		final byte SECTION_4 = (byte) 0x1F;
		// 192.168.x.x/16
		final byte SECTION_5 = (byte) 0xC0;
		final byte SECTION_6 = (byte) 0xA8;
		boolean flag = false;
		switch (b0) {
			case SECTION_1:
				flag = true;
				break;
			case SECTION_2:
				if (b1 >= SECTION_3 && b1 <= SECTION_4) {
					flag = true;
				}
				break;
			case SECTION_5:
				if (b1 == SECTION_6) {
					flag = true;
				}
				break;
			default:
				break;
		}
		return flag;
	}

	/**
	 * 将IPv4地址转换成字节
	 *IPv4地址
	 * @param text
	 * @return byte 字节
	 */
	public static byte[] textToNumericFormatV4(String text) {
		if (text.length() == 0) {
			return null;
		}

		byte[] bytes = new byte[4];
		String[] elements = text.split("\\.", -1);
		try {
			long l;
			int i;
			switch (elements.length) {
				case 1:
					l = Long.parseLong(elements[0]);
					if ((l < 0L) || (l > 4294967295L))
						return null;
					bytes[0] = (byte) (int) (l >> 24 & 0xFF);
					bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
					bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
					bytes[3] = (byte) (int) (l & 0xFF);
					break;
				case 2:
					l = Integer.parseInt(elements[0]);
					if ((l < 0L) || (l > 255L))
						return null;
					bytes[0] = (byte) (int) (l & 0xFF);
					l = Integer.parseInt(elements[1]);
					if ((l < 0L) || (l > 16777215L))
						return null;
					bytes[1] = (byte) (int) (l >> 16 & 0xFF);
					bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
					bytes[3] = (byte) (int) (l & 0xFF);
					break;
				case 3:
					for (i = 0; i < 2; ++i) {
						l = Integer.parseInt(elements[i]);
						if ((l < 0L) || (l > 255L))
							return null;
						bytes[i] = (byte) (int) (l & 0xFF);
					}
					l = Integer.parseInt(elements[2]);
					if ((l < 0L) || (l > 65535L))
						return null;
					bytes[2] = (byte) (int) (l >> 8 & 0xFF);
					bytes[3] = (byte) (int) (l & 0xFF);
					break;
				case 4:
					for (i = 0; i < 4; ++i) {
						l = Integer.parseInt(elements[i]);
						if ((l < 0L) || (l > 255L))
							return null;
						bytes[i] = (byte) (int) (l & 0xFF);
					}
					break;
				default:
					return null;
			}
		} catch (NumberFormatException e) {
			return null;
		}
		return bytes;
	}

	public static String getLocalIP() {
		String ip = "";
		if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
			InetAddress addr;
			try {
				addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress();
			} catch (UnknownHostException e) {
			}
			return ip;
		} else {
			try {
				Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
						.getNetworkInterfaces();
				while (e1.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) e1.nextElement();
					if (!ni.getName().equals("eth0")) {
						continue;
					} else {
						Enumeration<?> e2 = ni.getInetAddresses();
						while (e2.hasMoreElements()) {
							InetAddress ia = (InetAddress) e2.nextElement();
							if (ia instanceof Inet6Address)
								continue;
							ip = ia.getHostAddress();
							return ip;
						}
						break;
					}
				}
			} catch (SocketException e) {
			}
		}
		return "";
	}
}
