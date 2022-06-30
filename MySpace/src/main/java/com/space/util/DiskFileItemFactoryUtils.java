package com.space.util;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class DiskFileItemFactoryUtils {
    public static DiskFileItemFactory diskFileItemFactory;
    static {
        diskFileItemFactory=new DiskFileItemFactory();
    }
    public static DiskFileItemFactory getDiskFileItemFactory(){
        return diskFileItemFactory;
    }
}
