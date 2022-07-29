package dao;

import java.util.UUID;

public class DAO {

    public static String SQLWrapUUID(UUID id) {
        String uuid;
        if (id == null) {
            uuid = "uuid()";
        } else {
            uuid = id.toString();
        }
        return "unhex(replace(" + uuid + ",'-',''))";
    }
}
