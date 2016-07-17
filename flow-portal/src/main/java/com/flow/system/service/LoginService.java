package com.flow.system.service;

import java.util.Map;

public interface LoginService {
	Map<String,String> checkUser(String userCode,String rawPwd);
}
