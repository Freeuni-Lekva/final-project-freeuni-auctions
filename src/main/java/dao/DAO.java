package dao;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.util.UUID;

public class DAO {
    private Connection con;
    public DAO(Connection con) {
        this.con = con;
    }

    public DAO() {
    }

    public static String SQLWrapUUID(UUID id) {
        String uuid;
        if (id == null) {
            uuid = "uuid()";
        } else {
            uuid = "'" + id + "'";
        }
        return "unhex(replace(" + uuid + ",'-',''))";
    }

    public static UUID binaryToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}

