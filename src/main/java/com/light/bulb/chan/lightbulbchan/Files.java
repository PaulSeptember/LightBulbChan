package com.light.bulb.chan.lightbulbchan;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class Files {
    public Files() {
    }

    private File[] getAllFiles() {
        return File.listRoots()[2].listFiles();
    }

    public File[] getAllWithExpansion(final String expansion) {
        List<File> list = new ArrayList<>();
        for (File file : getAllFiles()) {
            if (file.getName().contains(expansion)) {
                list.add(file);
            }
        }
        File[] arr = new File[list.size()];
        list.toArray(arr);
        return list.toArray(arr);
    }
    public File[] getAllMp3() {
        return getAllWithExpansion(".mp3");
    }

    public File[] getAllTxt() {
        return getAllWithExpansion(".txt");
    }

    public File[] getAllPdf() {
        return getAllWithExpansion(".pdf");
    }
}
