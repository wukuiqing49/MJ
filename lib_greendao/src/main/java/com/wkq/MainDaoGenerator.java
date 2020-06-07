package com.wkq;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(6, "com.wkq.database.dao");
        addDefautDao(schema);
        addUserInfo(schema);

        new DaoGenerator().generateAll(schema, args[0]);
    }


    //
    private static void addDefautDao(Schema schema) {
        Entity uploadVideo = schema.addEntity("ExceptionInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addIdProperty().autoincrement().primaryKey();
        uploadVideo.addStringProperty("ErrorTag").notNull();
        uploadVideo.addStringProperty("ErrorMessage").notNull();
    }

    private static void addUserInfo(Schema schema) {
        Entity uploadVideo = schema.addEntity("UserInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("UserName").notNull();
        uploadVideo.addStringProperty("UserPhoneNum").primaryKey();
        uploadVideo.addStringProperty("UserPwd").notNull();
        uploadVideo.addStringProperty("UserIcon").notNull();
        uploadVideo.addBooleanProperty("IsLogout").notNull();
    }


}
