package org.example.excel.utils;

/**
 *
 *
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 16:13
 */
public class SnowflakeUtil {

  private long workerId = 0;
  private long datacenterId = 0;

  private static final long twepoch = 1288834974657L;
  private static final long workerIdBits = 5L;
  private static final long datacenterIdBits = 5L;
  private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
  private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  private static final long sequenceBits = 12L;
  private static final long workerIdShift = sequenceBits;
  private static final long datacenterIdShift = sequenceBits + workerIdBits;
  private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

  private static SnowflakeUtil snowflake;

  private long sequence = 0L;
  private long lastTimestamp = -1L;

  public static long getId() {
    return snowflake.nextId();
  }

  static {
    snowflake = new SnowflakeUtil();
  }

  private synchronized long nextId() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(
          String.format(
              "Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = timestamp;

    return ((timestamp - twepoch) << timestampLeftShift)
        | (datacenterId << datacenterIdShift)
        | (workerId << workerIdShift)
        | sequence;
  }

  private long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  private long timeGen() {
    return System.currentTimeMillis();
  }

  public static void main(String[] args) {
    //        for (int i = 0;i < 5;i++) {
    //
    //            String s = "insert into baojia_bike.coupon_package_code ( package_id, code,
    // remark, allow_multi_exchange," +
    //                    " exchanged_times, is_card, creator_id, creator_name, creator_mobile)\n" +
    // " values (6,"+SnowflakeUtil.getId()+",'50" +
    //                    "元优惠券',0,0,0,0,'大雄','15101010101');";
    //            System.out.println( s);
    //        }

  }
}
