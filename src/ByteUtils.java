

import java.nio.ByteBuffer;
public class ByteUtils {
    public static void setIntBytes(byte[] dst, int offset, int value) {
        dst[offset] = (byte) (value & 0xff);// 最低位
        dst[offset + 1] = (byte) ((value >> 8) & 0xff);// 次低位
        dst[offset + 2] = (byte) ((value >> 16) & 0xff);// 次高位
        dst[offset + 3] = (byte) (value >>> 24);// 最高位,无符号右移。
    }

    public static int getIntFromBytes(byte[] src, int offset) {
        int targets = (src[offset] & 0xff) | ((src[offset + 1] << 8) & 0xff00) // | 表示安位或
                | ((src[offset + 2] << 24) >>> 8) | (src[offset + 3] << 24);
        return targets;
    }

}
