import json
import re
import time
import secrets
import hashlib
import base64
import random
import string
import urllib.parse
import urllib.request
import urllib.error
from typing import Any, Dict, Optional, Sequence
from dataclasses import dataclass
from datetime import datetime, timedelta, timezone
from curl_cffi import requests


# ═══════════════════════════════════════════════════════
# Sentinel Proof-of-Work 求解器
# ═══════════════════════════════════════════════════════
DEFAULT_SENTINEL_DIFF = "0fffff"
DEFAULT_MAX_ITERATIONS = 500_000
_SCREEN_SIGNATURES = (3000, 3120, 4000, 4160)
_LANGUAGE_SIGNATURE = "en-US,es-US,en,es"
_NAVIGATOR_KEYS = ("location", "onprogress", "ontransitionend")
_WINDOW_KEYS = ("window", "document", "navigator")


class SentinelPOWError(RuntimeError):
    pass


def _format_browser_time() -> str:
    browser_now = datetime.now(timezone(timedelta(hours=-5)))
    return browser_now.strftime("%a %b %d %Y %H:%M:%S") + " GMT-0500 (Eastern Standard Time)"


def build_sentinel_config(user_agent: str) -> list:
    perf_ms = time.perf_counter() * 1000
    epoch_ms = (time.time() * 1000) - perf_ms
    return [
        random.choice(_SCREEN_SIGNATURES),
        _format_browser_time(),
        4294705152,
        0,
        user_agent,
        "", "", "en-US",
        _LANGUAGE_SIGNATURE,
        0,
        random.choice(_NAVIGATOR_KEYS),
        "location",
        random.choice(_WINDOW_KEYS),
        perf_ms,
        str(__import__('uuid').uuid4()),
        "", 8, epoch_ms,
    ]


def _encode_pow_payload(config: Sequence[object], nonce: int) -> bytes:
    prefix = (json.dumps(config[:3], separators=(",", ":"), ensure_ascii=False)[:-1] + ",").encode("utf-8")
    middle = ("," + json.dumps(config[4:9], separators=(",", ":"), ensure_ascii=False)[1:-1] + ",").encode("utf-8")
    suffix = ("," + json.dumps(config[10:], separators=(",", ":"), ensure_ascii=False)[1:]).encode("utf-8")
    body = prefix + str(nonce).encode("ascii") + middle + str(nonce >> 1).encode("ascii") + suffix
    return base64.b64encode(body)


def solve_sentinel_pow(
    seed: str,
    difficulty: str,
    config: Sequence[object],
    max_iterations: int = DEFAULT_MAX_ITERATIONS,
) -> str:
    seed_bytes = seed.encode("utf-8")
    target = bytes.fromhex(difficulty)
    prefix_length = len(target)

    for nonce in range(max_iterations):
        encoded = _encode_pow_payload(config, nonce)
        digest = hashlib.sha3_512(seed_bytes + encoded).digest()
        if digest[:prefix_length] <= target:
            return encoded.decode("ascii")

    raise SentinelPOWError(f"failed to solve sentinel pow after {max_iterations} attempts")


def build_sentinel_pow_token(
    user_agent: str,
    difficulty: str = DEFAULT_SENTINEL_DIFF,
    max_iterations: int = DEFAULT_MAX_ITERATIONS,
) -> str:
    config = build_sentinel_config(user_agent)
    seed = format(random.random())
    solution = solve_sentinel_pow(seed, difficulty, config, max_iterations=max_iterations)
    return f"gAAAAAC{solution}"


# ═══════════════════════════════════════════════════════
# 配置常量
# ═══════════════════════════════════════════════════════

# Tempmail.lol API v2
TEMPMAIL_BASE = "https://api.tempmail.lol/v2"

# OAuth 配置
AUTH_URL = "https://auth.openai.com/oauth/authorize"
TOKEN_URL = "https://auth.openai.com/oauth/token"
CLIENT_ID = "app_EMoamEEZ73f0CkXaXp7hrann"
DEFAULT_REDIRECT_URI = "http://localhost:1455/auth/callback"
DEFAULT_SCOPE = "openid email profile offline_access"

# Sentinel 配置
SENTINEL_URL = "https://sentinel.openai.com/backend-api/sentinel/req"


def _log_step(name, info=""):
    print(f"[{time.strftime('%H:%M:%S')}] [*] {name}: {info}")


# ═══════════════════════════════════════════════════════
# 浏览器指纹伪装
# ═══════════════════════════════════════════════════════
_BROWSER_PROFILES = [
    'edge99', 'edge101',
    'chrome99', 'chrome100', 'chrome101', 'chrome104', 'chrome107', 'chrome110',
    'chrome116', 'chrome119', 'chrome120', 'chrome123', 'chrome124', 'chrome131', 
    'chrome133a', 'chrome136',
    'safari153', 'safari155', 'safari170', 'safari180', 'safari184', 'safari260',
    'safari172_ios', 'safari180_ios', 'safari184_ios', 'safari260_ios',
    'firefox133', 'firefox135',
]

_ACCEPT_LANGUAGES = [
    "en-US,en;q=0.9",
    "en-GB,en;q=0.9,en-US;q=0.8",
    "en,en-US;q=0.9,en-GB;q=0.8",
    "zh-CN,zh;q=0.9",
    "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
    "zh-TW,zh-HK;q=0.9,zh;q=0.8,en-US;q=0.7,en;q=0.6",
    "es-ES,es;q=0.9,en;q=0.8",
    "es-MX,es;q=0.9,en-US;q=0.8,en;q=0.7",
    "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7",
    "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7",
    "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
    "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7",
    "ja-JP,ja;q=0.9,en-US;q=0.8,en;q=0.7",
    "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
    "vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5",
    "en-US",
    "en-GB",
]


def _pick_fingerprint() -> tuple[str, dict]:
    """随机选择浏览器身份和对应请求头"""
    profile = random.choice(_BROWSER_PROFILES)
    lang = random.choice(_ACCEPT_LANGUAGES)
    headers = {
        "Accept-Language": lang,
        "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Accept-Encoding": "gzip, deflate, br",
        "DNT": "1",
        "Sec-Fetch-Dest": "document",
        "Sec-Fetch-Mode": "navigate",
        "Sec-Fetch-Site": "none",
        "Sec-Fetch-User": "?1",
        "Upgrade-Insecure-Requests": "1",
    }
    return profile, headers


# ═══════════════════════════════════════════════════════
# 姓名 / 生日 / 密码生成
# ═══════════════════════════════════════════════════════
_GIVEN_NAMES = [
    "Liam", "Noah", "Oliver", "James", "Elijah", "William", "Henry", "Lucas",
    "Benjamin", "Theodore", "Jack", "Levi", "Alexander", "Mason", "Ethan",
    "Daniel", "Jacob", "Michael", "Logan", "Jackson", "Sebastian", "Aiden",
    "Owen", "Samuel", "Ryan", "Nathan", "Carter", "Luke", "Jayden", "Dylan",
    "Caleb", "Isaac", "Connor", "Adrian", "Hunter", "Eli", "Thomas", "Aaron",
    "Olivia", "Emma", "Charlotte", "Amelia", "Sophia", "Isabella", "Mia",
    "Evelyn", "Harper", "Luna", "Camila", "Sofia", "Scarlett", "Elizabeth",
    "Eleanor", "Emily", "Chloe", "Mila", "Avery", "Riley", "Aria", "Layla",
    "Nora", "Lily", "Hannah", "Hazel", "Zoey", "Stella", "Aurora", "Natalie",
    "Emilia", "Zoe", "Lucy", "Lillian", "Addison", "Willow", "Ivy", "Violet",
]

_FAMILY_NAMES = [
    "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis",
    "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
    "Lee", "Thompson", "White", "Harris", "Clark", "Lewis", "Robinson",
    "Walker", "Young", "Allen", "King", "Wright", "Hill", "Scott", "Green",
    "Adams", "Baker", "Nelson", "Carter", "Mitchell", "Roberts", "Turner",
    "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart",
    "Morris", "Murphy", "Cook", "Rogers", "Morgan", "Cooper", "Peterson",
    "Reed", "Bailey", "Kelly", "Howard", "Ward", "Watson", "Brooks", "Bennett",
    "Gray", "Price", "Hughes", "Sanders", "Long", "Foster", "Powell", "Perry",
    "Russell", "Sullivan", "Bell", "Coleman", "Butler", "Henderson", "Barnes",
]


def random_name() -> str:
    """生成随机英文姓名"""
    return f"{random.choice(_GIVEN_NAMES)} {random.choice(_FAMILY_NAMES)}"


def random_birthday() -> str:
    """生成随机生日（18~40岁之间）"""
    y = random.randint(1986, 2006)
    m = random.randint(1, 12)
    d = random.randint(1, 28)
    return f"{y}-{m:02d}-{d:02d}"


def generate_password() -> str:
    """生成随机密码"""
    letters = string.ascii_letters
    digits = string.digits
    symbols = "!@#()_+-=[]{}"

    pwd_letters = [secrets.choice(letters) for _ in range(5)]
    pwd_digits = [secrets.choice(digits) for _ in range(6)]
    pwd_symbols = [secrets.choice(symbols) for _ in range(1)]

    password_list = pwd_letters + pwd_digits + pwd_symbols
    secrets.SystemRandom().shuffle(password_list)
    return "".join(password_list)


def _b64url_no_pad(raw: bytes) -> str:
    return base64.urlsafe_b64encode(raw).decode("ascii").rstrip("=")


def _sha256_b64url_no_pad(s: str) -> str:
    return _b64url_no_pad(hashlib.sha256(s.encode("ascii")).digest())


def _random_state(nbytes: int = 16) -> str:
    return secrets.token_urlsafe(nbytes)


def _pkce_verifier() -> str:
    return secrets.token_urlsafe(64)


def _to_int(v: Any) -> int:
    try:
        return int(v)
    except (TypeError, ValueError):
        return 0


def _decode_jwt_segment(seg: str) -> Dict[str, Any]:
    """解码 JWT segment"""
    raw = (seg or "").strip()
    if not raw:
        return {}
    pad = "=" * ((4 - (len(raw) % 4)) % 4)
    try:
        decoded = base64.urlsafe_b64decode((raw + pad).encode("ascii"))
        return json.loads(decoded.decode("utf-8"))
    except Exception:
        return {}


def _jwt_claims_no_verify(id_token: str) -> Dict[str, Any]:
    """从 ID token 中提取 claims"""
    if not id_token or id_token.count(".") < 2:
        return {}
    payload_b64 = id_token.split(".")[1]
    pad = "=" * ((4 - (len(payload_b64) % 4)) % 4)
    try:
        payload = base64.urlsafe_b64decode((payload_b64 + pad).encode("ascii"))
        return json.loads(payload.decode("utf-8"))
    except Exception:
        return {}


def _parse_callback_url(callback_url: str) -> Dict[str, str]:
    """解析 OAuth callback URL"""
    candidate = callback_url.strip()
    if not candidate:
        return {"code": "", "state": "", "error": "", "error_description": ""}

    if "://" not in candidate:
        if candidate.startswith("?"):
            candidate = f"http://localhost{candidate}"
        elif any(ch in candidate for ch in "/?#") or ":" in candidate:
            candidate = f"http://{candidate}"
        elif "=" in candidate:
            candidate = f"http://localhost/?{candidate}"

    parsed = urllib.parse.urlparse(candidate)
    query = urllib.parse.parse_qs(parsed.query, keep_blank_values=True)
    fragment = urllib.parse.parse_qs(parsed.fragment, keep_blank_values=True)

    for key, values in fragment.items():
        if key not in query or not query[key] or not (query[key][0] or "").strip():
            query[key] = values

    def get1(k: str) -> str:
        v = query.get(k, [""])
        return (v[0] or "").strip()

    code = get1("code")
    state = get1("state")
    error = get1("error")
    error_description = get1("error_description")

    if code and not state and "#" in code:
        code, state = code.split("#", 1)

    if not error and error_description:
        error, error_description = error_description, ""

    return {
        "code": code,
        "state": state,
        "error": error,
        "error_description": error_description,
    }


def _post_form(url: str, data: Dict[str, str], timeout: int = 30) -> Dict[str, Any]:
    """POST 表单数据"""
    body = urllib.parse.urlencode(data).encode("utf-8")
    req = urllib.request.Request(
        url,
        data=body,
        method="POST",
        headers={
            "Content-Type": "application/x-www-form-urlencoded",
            "Accept": "application/json",
        },
    )
    try:
        with urllib.request.urlopen(req, timeout=timeout) as resp:
            raw = resp.read()
            if resp.status != 200:
                raise RuntimeError(
                    f"token exchange failed: {resp.status}: {raw.decode('utf-8', 'replace')}"
                )
            return json.loads(raw.decode("utf-8"))
    except urllib.error.HTTPError as exc:
        raw = exc.read()
        raise RuntimeError(
            f"token exchange failed: {exc.code}: {raw.decode('utf-8', 'replace')}"
        ) from exc


def submit_callback_url(
    *,
    callback_url: str,
    expected_state: str,
    code_verifier: str,
    redirect_uri: str = DEFAULT_REDIRECT_URI,
) -> str:
    """提交 callback URL 并交换 token"""
    cb = _parse_callback_url(callback_url)
    if cb["error"]:
        desc = cb["error_description"]
        raise RuntimeError(f"oauth error: {cb['error']}: {desc}".strip())

    if not cb["code"]:
        raise ValueError("callback url missing ?code=")
    if not cb["state"]:
        raise ValueError("callback url missing ?state=")
    if cb["state"] != expected_state:
        raise ValueError("state mismatch")

    token_resp = _post_form(
        TOKEN_URL,
        {
            "grant_type": "authorization_code",
            "client_id": CLIENT_ID,
            "code": cb["code"],
            "redirect_uri": redirect_uri,
            "code_verifier": code_verifier,
        },
    )

    access_token = (token_resp.get("access_token") or "").strip()
    refresh_token = (token_resp.get("refresh_token") or "").strip()
    id_token = (token_resp.get("id_token") or "").strip()
    expires_in = _to_int(token_resp.get("expires_in"))

    claims = _jwt_claims_no_verify(id_token)
    email = str(claims.get("email") or "").strip()
    auth_claims = claims.get("https://api.openai.com/auth") or {}
    account_id = str(auth_claims.get("chatgpt_account_id") or "").strip()

    now = int(time.time())
    expired_rfc3339 = time.strftime(
        "%Y-%m-%dT%H:%M:%SZ", time.gmtime(now + max(expires_in, 0))
    )
    now_rfc3339 = time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime(now))

    config = {
        "id_token": id_token,
        "access_token": access_token,
        "refresh_token": refresh_token,
        "account_id": account_id,
        "last_refresh": now_rfc3339,
        "email": email,
        "type": "codex",
        "expired": expired_rfc3339,
    }

    return json.dumps(config, ensure_ascii=False, separators=(",", ":"))


@dataclass(frozen=True)
class OAuthStart:
    auth_url: str
    state: str
    code_verifier: str
    redirect_uri: str


def generate_oauth_url() -> OAuthStart:
    """生成 OAuth 授权 URL"""
    state = _random_state()
    code_verifier = _pkce_verifier()
    code_challenge = _sha256_b64url_no_pad(code_verifier)

    params = {
        "client_id": CLIENT_ID,
        "response_type": "code",
        "redirect_uri": DEFAULT_REDIRECT_URI,
        "scope": DEFAULT_SCOPE,
        "state": state,
        "code_challenge": code_challenge,
        "code_challenge_method": "S256",
        "prompt": "login",
        "id_token_add_organizations": "true",
        "codex_cli_simplified_flow": "true",
    }
    auth_url = f"{AUTH_URL}?{urllib.parse.urlencode(params)}"
    return OAuthStart(
        auth_url=auth_url,
        state=state,
        code_verifier=code_verifier,
        redirect_uri=DEFAULT_REDIRECT_URI,
    )


def get_email_and_token(proxies: Any = None) -> tuple[str, str]:
    """创建 Tempmail.lol 邮箱"""
    try:
        resp = requests.post(
            f"{TEMPMAIL_BASE}/inbox/create",
            headers={
                "Accept": "application/json",
                "Content-Type": "application/json",
            },
            json={},
            proxies=proxies,
            impersonate="chrome",
            timeout=15,
        )

        if resp.status_code not in (200, 201):
            print(f"[-] Tempmail.lol 请求失败，状态码: {resp.status_code}")
            return "", ""

        data = resp.json()
        email = str(data.get("address", "")).strip()
        token = str(data.get("token", "")).strip()

        if not email or not token:
            print("[-] Tempmail.lol 返回数据不完整")
            return "", ""

        return email, token

    except Exception as e:
        print(f"[-] 创建 Tempmail.lol 邮箱出错: {e}")
        return "", ""


def get_oai_code(token: str, email: str, proxies: Any = None) -> str:
    """轮询获取验证码"""
    regex = r"(?<!\d)(\d{6})(?!\d)"
    seen_ids: set[int] = set()

    _log_step("开始监听邮件", f"邮箱: {email}")

    for i in range(40):
        print(".", end="", flush=True)
        try:
            resp = requests.get(
                f"{TEMPMAIL_BASE}/inbox",
                params={"token": token},
                headers={"Accept": "application/json"},
                proxies=proxies,
                impersonate="chrome",
                timeout=15,
            )

            if resp.status_code != 200:
                time.sleep(3)
                continue

            data = resp.json()

            if data is None or (isinstance(data, dict) and not data):
                print("\n[-] 邮箱已过期")
                return ""

            email_list = data.get("emails", []) if isinstance(data, dict) else []

            if not isinstance(email_list, list):
                time.sleep(3)
                continue

            for msg in email_list:
                if not isinstance(msg, dict):
                    continue

                msg_date = msg.get("date", 0)
                if not msg_date or msg_date in seen_ids:
                    continue
                seen_ids.add(msg_date)

                sender = str(msg.get("from", "")).lower()
                subject = str(msg.get("subject", ""))
                body = str(msg.get("body", ""))
                html = str(msg.get("html") or "")

                content = "\n".join([sender, subject, body, html])

                if "openai" not in sender and "openai" not in content.lower():
                    continue

                m = re.search(regex, content)
                if m:
                    print(f"\n[+] 验证码: {m.group(1)}")
                    return m.group(1)

        except Exception as e:
            pass

        time.sleep(3)

    print("\n[-] 超时，未收到验证码")
    return ""


def run_register(proxy: Optional[str], password: str) -> Optional[str]:
    """执行注册流程并返回 token JSON"""
    proxies: Any = None
    if proxy:
        proxies = {"http": proxy, "https": proxy}

    profile, fp_headers = _pick_fingerprint()
    s = requests.Session(proxies=proxies, impersonate=profile)
    s.headers.update(fp_headers)
    _log_step("浏览器指纹", profile)

    try:
        _log_step("检查网络连接")
        trace = s.get("https://cloudflare.com/cdn-cgi/trace", timeout=10)
        loc_match = re.search(r"^loc=(.+)$", trace.text, re.MULTILINE)
        loc = loc_match.group(1) if loc_match else "未知"
        _log_step("当前 IP 所在地", loc)

        email, dev_token = get_email_and_token(proxies)
        if not email or not dev_token:
            return None
        _log_step("临时邮箱创建成功", email)

        oauth = generate_oauth_url()

        _log_step("访问授权页面")
        resp = s.get(oauth.auth_url, timeout=15)
        did = s.cookies.get("oai-did")
        _log_step("Device ID", did)

        _log_step("求解 Sentinel PoW...")
        ua = s.headers.get("User-Agent", "Mozilla/5.0")
        try:
            pow_token = build_sentinel_pow_token(ua)
        except SentinelPOWError as e:
            print(f"[-] Sentinel PoW 求解失败: {e}")
            return None
        _log_step("PoW token 已生成")

        sentinel_body = json.dumps({
            "p": pow_token,
            "id": did,
            "flow": "authorize_continue",
        }, separators=(",", ":"))
        sen_resp = s.post(
            SENTINEL_URL,
            data=sentinel_body,
            headers={
                "Origin": "https://sentinel.openai.com",
                "Referer": "https://sentinel.openai.com/backend-api/sentinel/frame.html?sv=20260219f9f6",
                "Content-Type": "text/plain;charset=UTF-8",
            },
            timeout=15,
        )

        if sen_resp.status_code < 200 or sen_resp.status_code >= 300:
            print(f"[-] Sentinel 请求失败: {sen_resp.status_code}")
            return None

        sen_token = sen_resp.json()["token"]
        sentinel = json.dumps({
            "p": "", "t": "", "c": sen_token,
            "id": did, "flow": "authorize_continue",
        })
        _log_step("Sentinel token OK")

        _log_step("提交注册表单", email)
        signup_body = json.dumps({"username": {"value": email, "kind": "email"}, "screen_hint": "signup"})
        signup_resp = s.post(
            "https://auth.openai.com/api/accounts/authorize/continue",
            headers={
                "referer": "https://auth.openai.com/create-account",
                "accept": "application/json",
                "content-type": "application/json",
                "openai-sentinel-token": sentinel,
            },
            data=signup_body,
        )
        _log_step("注册表单状态", signup_resp.status_code)

        _log_step("提交密码", password)
        register_body = json.dumps({"password": password, "username": email})
        pwd_resp = s.post(
            "https://auth.openai.com/api/accounts/user/register",
            headers={
                "referer": "https://auth.openai.com/create-account/password",
                "accept": "application/json",
                "content-type": "application/json",
            },
            data=register_body,
        )
        _log_step("密码提交状态", pwd_resp.status_code)

        _log_step("请求发送验证码")
        otp_resp = s.get(
            "https://auth.openai.com/api/accounts/email-otp/send",
            headers={
                "referer": "https://auth.openai.com/create-account/password",
                "accept": "application/json",
            },
        )
        _log_step("验证码发送状态", otp_resp.status_code)

        code = get_oai_code(dev_token, email, proxies)
        if not code:
            return None

        _log_step("提交验证码", code)
        code_body = json.dumps({"code": code})
        code_resp = s.post(
            "https://auth.openai.com/api/accounts/email-otp/validate",
            headers={
                "referer": "https://auth.openai.com/email-verification",
                "accept": "application/json",
                "content-type": "application/json",
            },
            data=code_body,
        )
        _log_step("验证码校验状态", code_resp.status_code)

        name = random_name()
        birthday = random_birthday()
        _log_step("创建账户", f"{name}, {birthday}")
        create_account_body = json.dumps({"name": name, "birthdate": birthday})
        create_account_resp = s.post(
            "https://auth.openai.com/api/accounts/create_account",
            headers={
                "referer": "https://auth.openai.com/about-you",
                "accept": "application/json",
                "content-type": "application/json",
            },
            data=create_account_body,
        )
        _log_step("账户创建状态", create_account_resp.status_code)

        if create_account_resp.status_code != 200:
            print(f"[-] 账户创建失败: {create_account_resp.text}")
            return None

        _log_step("注册完成，重新发起登录流程拿 token...")
        time.sleep(random.uniform(1.0, 2.0))

        profile2, fp_headers2 = _pick_fingerprint()
        s2 = requests.Session(proxies=proxies, impersonate=profile2)
        s2.headers.update(fp_headers2)
        _log_step("浏览器指纹", profile2)

        oauth2 = generate_oauth_url()
        _log_step("重新发起 OAuth (登录)")
        resp2 = s2.get(oauth2.auth_url, timeout=15)
        did2 = s2.cookies.get("oai-did")
        _log_step("Device ID", did2)

        time.sleep(random.uniform(0.8, 2.0))

        _log_step("重新求解 Sentinel PoW...")
        ua2 = s2.headers.get("User-Agent", "Mozilla/5.0")
        try:
            pow_token2 = build_sentinel_pow_token(ua2)
        except SentinelPOWError as e:
            print(f"[-] 重新登录 Sentinel PoW 求解失败: {e}")
            return None
        _log_step("PoW token 已生成")

        sentinel_body2 = json.dumps({
            "p": pow_token2,
            "id": did2,
            "flow": "authorize_continue",
        }, separators=(",", ":"))
        sen_resp2 = s2.post(
            SENTINEL_URL,
            data=sentinel_body2,
            headers={
                "Origin": "https://sentinel.openai.com",
                "Referer": "https://sentinel.openai.com/backend-api/sentinel/frame.html",
                "Content-Type": "text/plain;charset=UTF-8",
            },
            timeout=15,
        )
        if sen_resp2.status_code < 200 or sen_resp2.status_code >= 300:
            print(f"[-] 重新登录 Sentinel 失败: {sen_resp2.status_code}")
            return None
        sen_token2 = sen_resp2.json()["token"]
        sentinel2 = json.dumps({
            "p": "", "t": "", "c": sen_token2,
            "id": did2, "flow": "authorize_continue",
        })
        _log_step("Sentinel token OK")

        time.sleep(random.uniform(0.5, 1.5))

        _log_step("提交邮箱 (登录)", email)
        login_body = json.dumps({"username": {"value": email, "kind": "email"}, "screen_hint": "login"})
        login_resp = s2.post(
            "https://auth.openai.com/api/accounts/authorize/continue",
            headers={
                "referer": "https://auth.openai.com/log-in",
                "accept": "application/json",
                "content-type": "application/json",
                "openai-sentinel-token": sentinel2,
            },
            data=login_body,
        )
        if login_resp.status_code != 200:
            print(f"[-] 重新登录提交邮箱失败: {login_resp.status_code}")
            return None

        login_page_type = ""
        try:
            login_page_type = login_resp.json().get("page", {}).get("type", "")
        except Exception:
            pass
        _log_step("页面类型", login_page_type)

        if login_page_type != "login_password":
            print(f"[-] 重新登录未进入密码页面: {login_page_type}")
            return None

        time.sleep(random.uniform(0.5, 1.5))

        _log_step("提交登录密码")
        pwd_verify_body = json.dumps({"password": password})
        pwd_verify_resp = s2.post(
            "https://auth.openai.com/api/accounts/password/verify",
            headers={
                "referer": "https://auth.openai.com/log-in/password",
                "accept": "application/json",
                "content-type": "application/json",
            },
            data=pwd_verify_body,
        )
        if pwd_verify_resp.status_code != 200:
            print(f"[-] 重新登录提交密码失败: {pwd_verify_resp.status_code}")
            return None

        pwd_page_type = ""
        try:
            pwd_page_type = pwd_verify_resp.json().get("page", {}).get("type", "")
        except Exception:
            pass
        _log_step("页面类型", pwd_page_type)

        if pwd_page_type != "email_otp_verification":
            print(f"[-] 重新登录未进入验证码页面: {pwd_page_type}")
            return None

        _log_step("密码校验通过，等待验证码...")
        time.sleep(random.uniform(0.5, 1.5))

        code2 = get_oai_code(dev_token, email, proxies)
        if not code2:
            return None

        _log_step("验证登录 OTP", code2)
        verify_body2 = json.dumps({"code": code2})
        verify_resp2 = s2.post(
            "https://auth.openai.com/api/accounts/email-otp/validate",
            headers={
                "referer": "https://auth.openai.com/email-verification",
                "accept": "application/json",
                "content-type": "application/json",
            },
            data=verify_body2,
        )
        if verify_resp2.status_code != 200:
            print(f"[-] 重新登录 OTP 验证失败: {verify_resp2.status_code}")
            return None
        _log_step("验证码校验成功")

        time.sleep(random.uniform(0.5, 1.5))

        auth_cookie = s2.cookies.get("oai-client-auth-session")
        if not auth_cookie:
            print("[-] 未能获取到授权 Cookie")
            return None

        auth_json = _decode_jwt_segment(auth_cookie.split(".")[0])
        workspaces = auth_json.get("workspaces") or []
        if not workspaces:
            print("[-] 授权 Cookie 里没有 workspace 信息")
            return None
        workspace_id = str((workspaces[0] or {}).get("id") or "").strip()
        if not workspace_id:
            print("[-] 无法解析 workspace_id")
            return None

        _log_step("选择 workspace", workspace_id)
        select_body = json.dumps({"workspace_id": workspace_id})
        select_resp = s2.post(
            "https://auth.openai.com/api/accounts/workspace/select",
            headers={
                "referer": "https://auth.openai.com/sign-in-with-chatgpt/codex/consent",
                "content-type": "application/json",
            },
            data=select_body,
        )

        if select_resp.status_code != 200:
            print(f"[-] 选择 workspace 失败: {select_resp.status_code}")
            return None

        continue_url = str((select_resp.json() or {}).get("continue_url") or "").strip()
        if not continue_url:
            print("[-] workspace/select 响应里缺少 continue_url")
            return None

        _log_step("跟随重定向链")
        current_url = continue_url
        for _ in range(6):
            final_resp = s2.get(current_url, allow_redirects=False, timeout=15)
            location = final_resp.headers.get("Location") or ""

            if final_resp.status_code not in [301, 302, 303, 307, 308]:
                break
            if not location:
                break

            next_url = urllib.parse.urljoin(current_url, location)
            if "code=" in next_url and "state=" in next_url:
                _log_step("获取到 callback URL")
                token_json = submit_callback_url(
                    callback_url=next_url,
                    code_verifier=oauth2.code_verifier,
                    redirect_uri=oauth2.redirect_uri,
                    expected_state=oauth2.state,
                )

                print("\n" + "="*60)
                print("✅ 注册成功！")
                print(f"邮箱: {email}")
                print(f"密码: {password}")
                print(f"姓名: {name}")
                print("="*60)

                return token_json
            current_url = next_url

        print("[-] 未能在重定向链中捕获到最终 Callback URL")
        return None

    except Exception as e:
        print(f"[-] 运行时错误: {e}")
        import traceback
        traceback.print_exc()
        return None


if __name__ == "__main__":
    import os

    print("="*60)
    print("   OpenAI 全自动注册工具")
    print("="*60)

    MY_PROXY = "http://127.0.0.1:7890"
    SLEEP_MIN = 5
    SLEEP_MAX = 30

    count = 0

    while True:
        count += 1
        print(f"\n{'='*60}")
        print(f">>> 开始第 {count} 次注册流程 <<<")
        print(f"{'='*60}\n")

        try:
            password = generate_password()
            token_json = run_register(MY_PROXY, password)

            if token_json:
                try:
                    t_data = json.loads(token_json)
                    fname_email = t_data.get("email", "unknown").replace("@", "_")
                except Exception:
                    fname_email = "unknown"

                script_dir = os.path.dirname(os.path.abspath(__file__))
                tokens_dir = os.path.join(script_dir, "tokens")
                os.makedirs(tokens_dir, exist_ok=True)

                file_name = os.path.join(tokens_dir, f"token_{fname_email}_{int(time.time())}.json")

                with open(file_name, "w", encoding="utf-8") as f:
                    f.write(token_json)

                print(f"\n[+] Token 已保存至: {file_name}")
            else:
                print("\n[-] 本次注册失败")

        except Exception as e:
            print(f"[-] 发生未捕获异常: {e}")

        wait_time = random.randint(SLEEP_MIN, SLEEP_MAX)
        print(f"\n[*] 休息 {wait_time} 秒后继续下一次注册...")
        time.sleep(wait_time)
