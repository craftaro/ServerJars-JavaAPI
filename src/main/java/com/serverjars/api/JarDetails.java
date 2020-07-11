package com.serverjars.api;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 7:35 PM
 */
public class JarDetails {

    private String version;
    private String file;
    private String hash;
    private int built;

    public JarDetails(String version, String file, String hash, int built) {
        this.version = version;
        this.file = file;
        this.hash = hash;
        this.built = built;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getBuilt() {
        return built;
    }

    public void setBuilt(int built) {
        this.built = built;
    }

    @Override
    public String toString() {
        return "JarDetails{" +
                "version='" + version + '\'' +
                ", file='" + file + '\'' +
                ", hash='" + hash + '\'' +
                ", built=" + built + '\'' +
                ", date=" + getBuildDate() +
                '}';
    }

    public String getBuildDate() {
        return getBuildDate("d/MM/yyyy");
    }

    public String getBuildDate(String format) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(built,0, ZoneOffset.UTC);
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }
}
