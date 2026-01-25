package com.example.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/09/28 22:21
 **/
@RestController
@RequestMapping("wx")
public class Controller {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String APP_ID = "wxdcf3f40876db553f";

	private static final String SECRET = "f5a7a659446992f54ba86c2ffcd33838";

	private static final ConcurrentHashMap<String, String> M = new ConcurrentHashMap<>(16);

	@Resource
	private WxSupport wxSupport;

	@GetMapping("code")
	public WxAuthCode2SessionVO code(String code) {
		WxAuthCode2SessionVO wxAuthCode2SessionVO = wxSupport.wxAuthCodeToSession(APP_ID, SECRET, code);
		if (null != wxAuthCode2SessionVO) {

			M.put(wxAuthCode2SessionVO.getOpenid(), wxAuthCode2SessionVO.getSession_key());
		}
		wxAuthCode2SessionVO.setSession_key("******");
		return wxAuthCode2SessionVO;
	}

	@GetMapping("phone")
	public void phone(String openId, String iv, String encryptedData) {
		String decrypt = wxSupport.decrypt(M.get(openId), encryptedData, iv);
		logger.info(decrypt);
	}




}
