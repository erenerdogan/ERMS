package com.erms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;

public class FileProcess {

	
	@SuppressWarnings("unused")
    private String fileName;
    private String directoryName;
    private File file;

    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    public FileProcess() {
        // TODO Auto-generated constructor stub
        this.directoryName = Environment.getExternalStorageDirectory()
                .toString() + "/ERMS";
        File directory = new File(directoryName);
        fileName="";
        if (directory.isAbsolute()) {
            Log.v("Directory Absolute", "Yes");
            directory.mkdir();
        } else {
            Log.v("Directory Absolute", "No");
        }
    }

    public FileProcess(String fileName) throws IOException {
        // TODO Auto-generated constructor stub
        this.directoryName = Environment.getExternalStorageDirectory()
                .toString() + "/ERMS";
        this.fileName = fileName;
        File directory = new File(directoryName);
        if (directory.isAbsolute()) {
            Log.v("Directory Absolute", "Yes");
            directory.mkdir();
            file = new File(directory, fileName);
        } else {
            Log.v("Directory Absolute", "No");
        }
    }

    public boolean dosyaVarMi() { 
        return file.exists();
    }
    
    public boolean dosyaYarat() throws Exception{
    	Log.v("Dosya Path", file.getPath());
        return file.createNewFile();
    }

    public boolean dosyaOkunabilirMi() {
        return file.canRead();
    }

    public boolean dosyaYazilabilirMi() {
        return file.canWrite();
    }
    public long fileSize(){
    	return file.length();
    }

    public void fileWriteOrderList(List<OrderModel> arrayList)
            throws IOException {
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(arrayList);
        oos.close();
        fos.close();
    }
    
    public List<OrderModel> fileReadOrderList()
			throws OptionalDataException, ClassNotFoundException, IOException {
		fis = new FileInputStream(file);
		ois = new ObjectInputStream(fis);
		@SuppressWarnings("unchecked")
		List<OrderModel> arrayList = (List<OrderModel>) ois.readObject();
		ois.close();
		fis.close();
		return arrayList;
	}
    
}
