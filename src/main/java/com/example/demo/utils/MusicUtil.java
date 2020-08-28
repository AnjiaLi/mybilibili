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

    public static void getMusicDuration() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\story.mp3");
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            System.out.println(audioHeader.getTrackLength());
            System.out.println(audioHeader.getTrackLengthAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

//    public static List<String> getMusicDuration(List<File> mp3List) {
//        int size = mp3List.size();
//        List<String> durationList = new ArrayList<>(size);
//        MP3File f;
//        MP3AudioHeader audioHeader;
//        try {
//            for (int i = 0; i < size; i++) {
//                f = (MP3File) AudioFileIO.read(mp3List.get(i));
//                audioHeader = (MP3AudioHeader) f.getAudioHeader();
//                durationList.add(audioHeader.getTrackLengthAsString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return durationList;
//    }

    public static List<String> getMusicDuration(List<String> mp3List) {
        System.out.println(mp3List.get(1).getClass());
        int size = mp3List.size();
        List<String> durationList = new ArrayList<>(size);
        MP3File f;
        MP3AudioHeader audioHeader;
        try {
            for (int i = 0; i < size; i++) {
                f = (MP3File) AudioFileIO.read(new File(mp3List.get(i)));
                audioHeader = (MP3AudioHeader) f.getAudioHeader();
                durationList.add(audioHeader.getTrackLengthAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return durationList;
    }
}
