package com.cug.lab.model;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysUser implements Serializable {

	private static final long serialVersionUuserId = 6085691637946845003L;

	private Long userId; //编号
	private String userName; //用户名
	private String userPsd; //密码
	private String userSalt; //加密密码的盐
	private String userRoleIds; //拥有的角色列表
	private Long userCode; //用户编码
	private String userCompany; //用户单位
	private String userAddress; //用户单位地址
	private Date userCreateTime; //创建时间
	private Boolean userLocked = Boolean.FALSE; //是否锁定

	/* 构造函数 */
	public SysUser() {
	}

	public SysUser(String userName, String userPsd) {
		this.userName = userName;
		this.userPsd = userPsd;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPsd() {
		return userPsd;
	}

	public void setUserPsd(String userPsd) {
		this.userPsd = userPsd;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public String getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(String userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public Boolean getUserLocked() {
		return userLocked;
	}

	public void setUserLocked(Boolean userLocked) {
		this.userLocked = userLocked;
	}

	/*get CredentialsSalt*/
	public String getCredentialsSalt() {
		return userName + userSalt;
	}

	/* 通过roleuserIds字符串获取到list */
	public List<Long> getRoleIdList() {
		List<Long> roleIdList = new ArrayList<Long>();
		if(userRoleIds == null) {

		}else {
			String[] arr = userRoleIds.split(",");

			if(arr != null || arr.length > 0){
				for (int i = 0 ; i < arr.length ; i++){
					if(StringUtils.isEmpty(arr[i])){

					}else{
						roleIdList.add(Long.parseLong(arr[i]));
					}
				}
			}
		}
		return roleIdList;
	}

	/* 通过list设置roleuserIds字符串 */
	public void setRoleIdList(List<Long> roleuserIdList) {
		StringBuilder s = new StringBuilder();
		for(Long roleuserId : roleuserIdList) {
			if(StringUtils.isEmpty(String.valueOf(roleuserId))) {
				continue;
			}
			s.append(roleuserId);
			s.append(",");
		}
		this.userRoleIds =  s.toString();
	}


	//重写equals
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysUser user = (SysUser) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null){
			return false;
		}

		return true;
	}

	//重写hashcode
	@Override
	public int hashCode() {
		return userId != null ? userId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userPsd='" + userPsd + '\'' +
				", userSalt='" + userSalt + '\'' +
				", userRoleIds='" + userRoleIds + '\'' +
				", userCode=" + userCode +
				", userCompany='" + userCompany + '\'' +
				", userAddress='" + userAddress + '\'' +
				", userCreateTime=" + userCreateTime +
				", userLocked=" + userLocked +
				'}';
	}
}

