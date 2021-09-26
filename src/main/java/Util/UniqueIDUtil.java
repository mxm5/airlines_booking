package Util;

import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueIDUtil {
    public static Long generate() {
        long val = -1;
        do {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        }
        while (val < 0);
        return val;
    }


}
