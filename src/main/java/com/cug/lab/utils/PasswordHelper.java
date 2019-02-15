package com.cug.lab.utils;

import com.cug.lab.model.SysUser;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordHelper {

	public static SysUser getMD5String(SysUser sysUser) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(sysUser.getUserPsd().getBytes());
			// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			// 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
			sysUser.setUserPsd(new BigInteger(1, md.digest()).toString(16));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysUser;
	}

}
