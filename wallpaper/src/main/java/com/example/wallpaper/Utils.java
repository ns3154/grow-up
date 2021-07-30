package com.example.wallpaper;

import com.google.common.base.Joiner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/07/10 16:38
 **/
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private static final String BASE_PATH = "/Users/yang/Pictures/4K/";

    private static final String LOCAL_FILE_PATH = "/Users/yang/Pictures/4K/风景/";

    private static final Map<String, File> PATH_MAP = new HashMap<>();

    private Utils() {
        // ignore
    }

    public static Executor executor(String threadName) {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1, 30,
                60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(200),
                new BasicThreadFactory.Builder().namingPattern(Joiner.on("-")
                        .join(threadName, "%s")).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 获取图片尺寸
     * @param file 文件
     * @return {@link int[]}
     * @throws IOException ioexception
     */
    public static int[] wh(File file) throws IOException {
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
        if (null == sourceImg) {
            logger.info("unknow, name: {}", file.getName());
            return new int[]{0, 0};
        }
        return new int[]{sourceImg.getWidth(), sourceImg.getHeight()};
    }

    public static String whStr(File file) throws IOException {
        int[] wh = wh(file);
        return wh[0] + "x" + wh[1];
    }

    public static String writeLocal(File file, String basePath) throws IOException {
        String p = whStr(file);
        File newPath = newPath(basePath + p);
        FileUtils.copyFileToDirectory(file, newPath);
        return newPath.getPath();
    }

    public static File newPath(String path) {
        return PATH_MAP.computeIfAbsent(path, s -> {
            File destDir = new File(path);
            if(!destDir.exists()) {
                destDir.mkdir();
            }
            return destDir;
        });
    }



    public static void main (String[] args) throws IOException {
        File file = new File(LOCAL_FILE_PATH);
        File[] files = file.listFiles();
        int length = files.length;
        int i = 0;
        for (File f : files) {
            if (f.isFile()) {
                writeLocal(f, LOCAL_FILE_PATH);
                logger.info("复制进度: {}/{}", ++i, length);
            }
        }

    }


}
