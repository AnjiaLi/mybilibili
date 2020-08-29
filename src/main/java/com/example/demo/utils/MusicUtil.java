package com.example.demo.utils;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MusicUtil {

    static {
        AudioFileIO.logger.setLevel(Level.SEVERE);
        ID3v23Frame.logger.setLevel(Level.SEVERE);
        ID3v23Tag.logger.setLevel(Level.SEVERE);
    }

    public static String getMusicDuration(String path) {
        return getMusicDuration(new File(path));
    }

    public static String getMusicDuration(File file) {
        String duration = "";
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
//            System.out.println(audioHeader.getTrackLength());
//            System.out.println(audioHeader.getTrackLengthAsString());
            duration = audioHeader.getTrackLengthAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    public static List<String> getMusicDuration(List mp3List) {
//        System.out.println(mp3List.get(0) instanceof File);
        int size = mp3List.size();
        List<String> durationList = new ArrayList<>(size);
        boolean isFile = false, isString = false;
        if (size != 0) {
            isFile = mp3List.get(0).getClass().equals(File.class);
            isString = mp3List.get(0).getClass().equals(String.class);
        }
        MP3File f;
        MP3AudioHeader audioHeader;

            for (int i = 0; i < size; i++) {
                try {
                if (isFile)
                    f = (MP3File) AudioFileIO.read((File) mp3List.get(i));
                else if (isString)
                {
                    File ffff=new File((String) mp3List.get(i));
                    System.out.println(ffff.getAbsolutePath());
                    f = (MP3File) AudioFileIO.read(ffff);

                }
                else break;
                audioHeader = (MP3AudioHeader) f.getAudioHeader();
                durationList.add(audioHeader.getTrackLengthAsString());
                } catch (Exception e) {
//                    e.printStackTrace();
                    durationList.add("00:00");
                }
            }

        return durationList;
    }
}
