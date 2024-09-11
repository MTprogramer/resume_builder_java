package com.resumemaker.resumecvbuilder;

import java.io.File;
import java.util.ArrayList;

public interface SaveCVCalback {
    void sendSaveList(ArrayList<File> arrayList);
}
