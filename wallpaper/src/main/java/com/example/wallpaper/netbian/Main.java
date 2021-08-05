package com.example.wallpaper.netbian;

import com.google.common.base.Joiner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/07/10 00:48
 **/
@Slf4j
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String PAGE_URL = "https://pic.netbian.com/";

    private static final String DOWN_URL = "https://pic.netbian.com/downpic.php?id={id}&classid=53";

    private static final String BASE_FILE_PATH = "/Users/yang/Pictures/4K/";

    private static final Map<String, Base> BASE_MAP = new HashMap<>();

    private static Set<String> localFileNames = new CopyOnWriteArraySet<>();

    private static AtomicInteger downInteger = new AtomicInteger(0);

    private static AtomicInteger repeatInteger = new AtomicInteger(0);

    private static List<String> than4mImg = new CopyOnWriteArrayList<>();

    private static AtomicInteger imgs = new AtomicInteger(0);


    static AtomicInteger integer = new AtomicInteger(0);

    static AtomicInteger timeOut = new AtomicInteger(0);

    static CopyOnWriteArrayList<String> errors = new CopyOnWriteArrayList<>();

    static {
        BASE_MAP.put("fengjing", new Base(PAGE_URL + "4kfengjing/", BASE_FILE_PATH + "风景/"));
        BASE_MAP.put("4kmeinv", new Base(PAGE_URL + "4kmeinv/", BASE_FILE_PATH + "美女/"));

    }



    public static String getPageHtml(int pageNums, String type) {
        String url = BASE_MAP.get(type).getUri();

        if(pageNums > 1) {
            url += "index_"+ pageNums +".html";
        }
        logger.info(url);
        ResponseEntity<String> entity = NetBianHttpClient.rest().exchange(url, HttpMethod.GET,
                new HttpEntity<String>(NetBianHttpClient.getHttpHeaders(pageNums + "")), String.class);
        if(HttpStatus.OK == entity.getStatusCode()) {
            return entity.getBody();
        }

        return null;
    }

    public static double getImgSize(String url) {
        double size = 0D;
        try {
            String body = getHtml(url);
            if(StringUtils.isNotBlank(body)) {
                Document root = Jsoup.parse(body);
                String strSize = root.getElementsByClass("infor")
                        .get(0)
                        .children()
                        .get(2)
                        .children()
                        .get(0)
                        .text();
                String[] str = strSize.split(" ");
                if("MB".equals(str[1])) {
                    size = Double.parseDouble(str[0]) * 1024;
                } else if ("KB".equals(str[1])) {
                    size = Double.parseDouble(str[0]);
                }
            }
        } catch (Exception e) {
            // nothing
        }

        return size;
    }

    private static int maxPage(String url) {
        String html = getHtml(url);
        if(StringUtils.isNotBlank(html)) {
            Document root = Jsoup.parse(html);
            Elements page = root.getElementsByClass("page").get(0).children();
            int size = page.size();
            if (size - 2 > 0) {
                TextNode node = (TextNode) page.get(size - 2).childNode(0);
                String wholeText = node.getWholeText();
                return Integer.parseInt(wholeText);
            }
        }

        return -1;

    }

    private static String getHtml(String url) {
        ResponseEntity<String> forEntity = NetBianHttpClient.restTemplate()
                .getForEntity(url, String.class);
        if(forEntity.getStatusCode() == HttpStatus.OK) {
            return forEntity.getBody();
        }

        return null;
    }

    private static void down(int pageNums, String type) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1, 30, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(200), new BasicThreadFactory.Builder().namingPattern(Joiner.on("-").join("IMG-DOWN-THREAD", "%s")).build(), new ThreadPoolExecutor.CallerRunsPolicy());
        String pageBody = getPageHtml(pageNums, type);
        Elements slist = null;
        if(StringUtils.isNotBlank(pageBody)) {
            Document root = Jsoup.parse(pageBody);
            if(null != root) {
                slist = root.getElementsByClass("slist");
            }
        }

        if(null == slist) {
            logger.error("获取 li标签 为空");
            return;
        }
        Element element = slist.get(0);
        Elements a = element.select("a");
        imgs.addAndGet(a.size());
        for (Element e : a) {
            if(null != e) {
                threadPoolExecutor.execute(() -> {
                    String href = e.attributes().get("href");
                    String url = PAGE_URL + href.substring(href.indexOf("/") +1);
                    double size = getImgSize(url);
                    if(size < 512) {
                        than4mImg.add(url);
                        return;
                    }
                    Elements img = e.select("img");
                    String imgName = img.get(0).attributes().get("alt") + ".jpeg";
                    String id = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf("."));
                    String downUrl = DOWN_URL.replace("{id}", id);
                    String newImgName = imgName.replaceAll(" ", "");
                    down(pageNums, size, id, downUrl, newImgName, type);
                });
            }
        }
        threadPoolExecutor.shutdown();
        for (;;) {
            if(threadPoolExecutor.isTerminated()) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @SneakyThrows
    private static void down(int pageNums, double size, String id, String downUrl, String newImgName, String type) {
        logger.info("将要下载文件{}, 大小:{}, 地址:{}", newImgName, size, downUrl);
        Base base = BASE_MAP.get(type);
        if(!localFileNames.contains(newImgName)) {
            ResponseEntity<byte[]> exchange = null;
            try {
                exchange = NetBianHttpClient.rest()
                        .exchange(downUrl, HttpMethod.GET, new HttpEntity<String>(NetBianHttpClient.getHttpHeaders(id)), byte[].class);
            } catch (RestClientException e1) {
                logger.error("*********** 超时:{}, 文件数:{} *******", downUrl, timeOut.addAndGet(1));
            }

            if (null != exchange && null != exchange.getBody() && exchange.getStatusCode() == HttpStatus.OK) {

                int realSize = 0;
                byte[] bodyByte = exchange.getBody();
                if (ArrayUtils.isNotEmpty(bodyByte)) {
                    realSize = bodyByte.length / 1024;
                }
                if(realSize < 10) {
                    String body = new String(bodyByte, "gb2312");
                    errors.add(body + ",url:" + downUrl + ",page:" + pageNums);
                    logger.info("********** {} 无法下载 , reason:{}************", newImgName, body);
                    return;
                }
                logger.info("*** 开始写入文件:{} *****", newImgName);
                try (FileImageOutputStream imageOutput =
                             new FileImageOutputStream(new File(base.getWriteLocalPath() + newImgName))) {
                    imageOutput.write(exchange.getBody(), 0, Objects.requireNonNull(exchange.getBody()).length);
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
                synchronized (Main.class) {
                    appendNameToTxt(newImgName, type);
                }
                localFileNames.add(newImgName);
                logger.info("*** 文件写入完成:{}, {}, pageNums:{}, 文件大小:{}kb, 真实大小:{}kb *****",
                        base.getWriteLocalPath(), integer.addAndGet(1), pageNums, size, realSize);
                downInteger.addAndGet(1);
            }
        } else {
            logger.error("** {} 该文件已存在 ***", newImgName);
            repeatInteger.addAndGet(1);
        }
    }

    private static void down(String type) {
        localFileNames = localImgNames(type);

        int start = 33;
        int end = start + 5;
        for (int z = start; z < end; z++) {
            down(z, type);
        }

        logger.error("** 执行完毕:图片总数:{} 下载数:{}, 重复数:{}, 小于512K数量:{}, 超时失败数量:{}, 其他失败数量:{}",
                imgs.get(),
                downInteger.get(),
                repeatInteger.get(), than4mImg.size(),
                timeOut.get(), errors.size());
        errors.forEach(e -> log.error("失败原因:{}", e));
        logger.info("next page:{}", end);
    }

    private static void  appendNameToTxt(String name, String type) {
        Base base = BASE_MAP.get(type);
        name = name + "\n";
        try (FileWriter fileWriter = new FileWriter(base.getImgNameTxt(), true)) {
            File file = new File(base.getImgNameTxt());
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            fileWriter.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Set<String> localImgNames (String type) {
        String fileName = BASE_MAP.get(type).getImgNameTxt();
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdir();
        }
        Set<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String name = null;
            while (null != (name = br.readLine())) {
                set.add(name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    // 179
    public static void main(String[] args) throws InterruptedException {
        down("4kmeinv");
    }


}