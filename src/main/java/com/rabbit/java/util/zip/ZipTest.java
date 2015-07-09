package com.rabbit.java.util.zip;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhangjing on 15/7/9.
 */
public class ZipTest {

    @Test
    public void readZip() {
        try (ZipInputStream in = new ZipInputStream(new FileInputStream("ti-file-srv-rt-1.0.0-SNAPSHOT-install.zip"))) {
            ZipEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        } catch (Exception e) {

        }
    }

    @Test
    public void writeZip() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("test.zip"))) {
            ZipEntry e = new ZipEntry("/Users/zhangjing/tmp");
            zipOutputStream.putNextEntry(e);
            zipOutputStream.closeEntry();
        } catch (Exception e) {

        }
    }
}
