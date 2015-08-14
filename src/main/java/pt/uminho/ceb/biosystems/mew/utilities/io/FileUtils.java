/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection.CollectionUtils;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.table.GenericTable;

public class FileUtils {
	private static String DIR_SEP = "/";
	private static final int maxRetries = 10;

	public static URL getGlobalResourceURL(String resourcePath){
		try{
			URL url = FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
			
			try {
				if (url.getFile().endsWith(".jar")){
					url = new URL(url.toString().substring(0,url.toString().lastIndexOf('/'))+"/.."+resourcePath);
				}else{
					url = new URL(url+".."+resourcePath);
				}
				
				
				return url;
					
							
			} catch (MalformedURLException e1) {
				throw new RuntimeException("URL "+resourcePath+" seems to be malformed:"+e1);
			}
		}catch(NullPointerException e){
			try {
				return new File("./"+resourcePath).toURI().toURL();
			} catch (Exception e1) {
				throw new RuntimeException("Can't find resource in path "+resourcePath+" due to "+e1);
			}
		}
	}
	
	public static URL getGlobalResourceURL(Class<?>klazz, String resourcePath){
		try{
			URL url = klazz.getProtectionDomain().getCodeSource().getLocation();
			
			try {
				if (url.getFile().endsWith(".jar")){
					url = new URL(url.toString().substring(0,url.toString().lastIndexOf('/'))+"/.."+resourcePath);
				}else{
					url = new URL(url+resourcePath);
				}
				
				
				return url;
					
							
			} catch (MalformedURLException e1) {
				throw new RuntimeException("URL "+resourcePath+" seems to be malformed:"+e1);
			}
		}catch(NullPointerException e){
			try {
				return new File("./"+resourcePath).toURI().toURL();
			} catch (Exception e1) {
				throw new RuntimeException("Can't find resource in path "+resourcePath+" due to "+e1);
			}
		}
	}
	
	public static boolean createFoldersFromPath(String path){
		  // Create one directory
		  boolean success = (new File(path)).mkdirs();
		  return success;
	}

	public static boolean existsPath(String path){
		  boolean exists = (new File(path)).exists();
		  return exists;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T loadSerializableObject(File file, ClassLoader classLoader) throws IOException, ClassNotFoundException{
		
		if(!file.exists()) return null;
		
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream ois = new CostumObjectInputStream(fin, classLoader);
		
		Object toReturn;
		try {
			toReturn = ois.readObject();
		} finally{
			ois.close();
			fin.close();
		}
		
		return (T)toReturn;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T loadSerializableObject(File file) throws IOException, ClassNotFoundException{
		
		
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fin);
		
		Object toReturn;
		try {
			toReturn = ois.readObject();
		} finally{
			ois.close();
			fin.close();
		}
		
		return (T)toReturn;
	}
	
	public static <T> T loadSerializableObject(String filePath) throws IOException, ClassNotFoundException{
		return loadSerializableObject(new File(filePath));
	}
	
	public static void saveSerializableObject(Object obj, String path) throws IOException{
		
		FileOutputStream fout = new FileOutputStream(path);
	    ObjectOutputStream oos = new ObjectOutputStream(fout);
	    
	    try{
	    	oos.writeObject(obj);
	    } finally{
		    oos.flush();
		    oos.close();
		    fout.close();
	    }
	}
	
	public static void getFile(String link, String path) throws IOException{
		
        URL url  = new URL(link);
        URLConnection urlC = null;
        int i =0;
        while(urlC==null && i < maxRetries) {
        	try {
				urlC = url.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
        }
        
        InputStream is = url.openStream();
        
        FileOutputStream fos = new FileOutputStream(path);
        int oneChar;
        
        try{
	        while((oneChar=is.read()) != -1)
	           fos.write(oneChar);
        }finally{
	        is.close();
	        fos.close();
        }
		
	}
	

	public static List<String> readLines(String filePath) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		List<String> lines = new ArrayList<String>();
		String line;
		
		try{
			while((line = br.readLine()) != null)
				lines.add(line);
		}finally{
			br.close();
			fr.close();
		}
		
		return lines;
	}
	
	public static GenericTable readTable(String filePath, Boolean hasHeaders) throws IOException {
		GenericTable table = new GenericTable(hasHeaders);
		List<String> lines = readLines(filePath);
		if(hasHeaders)
			table.parseHeader(lines.remove(0));
		for(String line : lines)
			table.parseLine(line);
		return table;
	}
	
	/**
	 * @return
	 */
	public static String getCurrentDirectory(){
		String tokenizer = File.pathSeparator;
		StringTokenizer st = new StringTokenizer(System.getProperty("java.class.path"),tokenizer);
		String path = st.nextToken();
		return path;
		
	}
	/**
	 * @return
	 */
	public static String getCurrentTempDirectory(){
		String path = getCurrentDirectory();
		path =path +"/../../temp/";
		new File(path).mkdir();
		return path;
		
	}
	
	/**
	 * @param dirName
	 * @return
	 */
	public static String getCurrentTempDirectory(String dirName){
		String path = getCurrentTempDirectory();
		path =path+dirName+"/";
		File file = new File(path);
		if(	(file.exists() && file.isDirectory()) || file.mkdir())
		{
			return path;
		}
		if(file.mkdirs())
		{
			return path;
		}
		return null;
	}
	
	public static void saveStringInFile(String filePath, String data) throws IOException{
	
		FileWriter file = new FileWriter(filePath);
		try{
			file.write(data);
		}finally{
			file.close();
		}
	}
	
	public static String getLastFolderName(String path){
		
		File n = new File(path);
		String ret = "";
		if (n.isDirectory()){
			ret=n.getName();
		}else{
			ret = n.getParentFile().getName();
		}
		return ret;
	}
	
	public static void remove(String path) {
		if(path!=null){
			File f = new File(path);
			delete(f);
		}
	}
	
	public static boolean delete(File file){
		if(file.exists()){
			boolean b = true;
			File[] files = file.listFiles();
		    if(files!=null) {
		        for(File f: files) {
		           b = delete(f) && b ;
		        }
		    }
		    return file.delete() && b;
		}
	    return false;
	}
	
	//-----------------------------------------------------------------------
    /**
     * Recursively delete a directory.
     * @param directory directory to delete
     * @throws IOException in case deletion is unsuccessful
     */
    public static void deleteDirectory(File directory)
        throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);
        if (!directory.delete()) {
            String message =
                "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * Clean a directory without deleting it.
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
	
  //-----------------------------------------------------------------------
    /**
     * <p>
     * Delete a file. If file is a directory, delete it and all sub-directories.
     * </p>
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     *      (java.io.File methods returns a boolean)</li>
     * </ul>
     * @param file file or directory to delete.
     * @throws IOException in case deletion is unsuccessful
     */
    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: " + file);
            }
            if (!file.delete()) {
                String message =
                    "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }

    /**
     * Schedule a file to be deleted when JVM exits.
     * If file is directory delete it and all sub-directories.
     * @param file file or directory to delete.
     * @throws IOException in case deletion is unsuccessful
     */
    public static void forceDeleteOnExit(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    /**
     * Recursively schedule directory for deletion on JVM exit.
     * @param directory directory to delete.
     * @throws IOException in case deletion is unsuccessful
     */
    private static void deleteDirectoryOnExit(File directory)
            throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectoryOnExit(directory);
        directory.deleteOnExit();
    }

    /**
     * Clean a directory without deleting it.
     * @param directory directory to clean.
     * @throws IOException in case cleaning is unsuccessful
     */
    private static void cleanDirectoryOnExit(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                forceDeleteOnExit(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
	
	public static void createZipFile(String path, String dest, int compressionLevel) throws IOException {
		
		File destFile = new File(dest);
		if(!destFile.exists()){
			FileUtils.createFoldersFromPath(destFile.getParent());
			destFile.createNewFile();
		}
		path = windowsWorkaround(path);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
		try{
			out.setLevel(compressionLevel);
			
			byte[] buf = new byte[10*1024];
			
			File dir = new File(path);
			List<String> filenames = directoryTree(dir);
			String[] dirs = path.split(DIR_SEP);
			path = CollectionUtils.join(Arrays.copyOfRange(dirs, 0, dirs.length-1), DIR_SEP) + DIR_SEP;
			for (int i=0; i< filenames.size(); i++) {
				String filename = filenames.get(i);
				File file = new File(filename);
				String relativeFilename = filename.replace(path, "");
	
				if(file.isDirectory()){
					
					ZipEntry k = new ZipEntry(relativeFilename+"/");
					out.putNextEntry(k);
				}else {
					
					InputStream in = new FileInputStream(filename);
					try{
						out.putNextEntry(new ZipEntry(relativeFilename));
						
			        	int len;
					    while ((len = in.read(buf)) > 0)
					    	out.write(buf, 0, len);
					}finally{
						in.close();
					}
				}
		    	out.closeEntry();
			}
		}finally{
			out.close();
		}
	}
	
	public static void extractZipFile(String file, String dest) throws IOException  {
		System.out.println("estou a fazer unzip para: " + dest);
		
		ZipInputStream in = new ZipInputStream(new FileInputStream(file));
	    File destDir = new File(dest);
	    if(!destDir.exists())
	    	destDir.mkdir();
	    
	    ZipEntry entry;
	    
	    try{
		    while((entry= in.getNextEntry()) != null) {
		    	String filename = destDir + DIR_SEP + entry.getName();
	
		    	File entryFile = new File(filename);
		    	entryFile.mkdirs();
		    	
		    	ZipEntry mockEntry = new ZipEntry(windowsWorkaround(entry.getName()));
		    	System.out.println(filename+"\t"+mockEntry.isDirectory());
		    	if(!mockEntry.isDirectory()) {
		    		entryFile.delete();
		    		OutputStream out = new FileOutputStream(filename);
		    		
		    		byte[] buf = new byte[1024];
		    		int len;
		    		try{
		    			while ((len = in.read(buf)) > 0)
		    				out.write(buf, 0, len);
		    		}finally{
		    			out.close();
		    		}
		    	}
		    	else { 
	//	    		in.read();
		    		entryFile.mkdirs();
		    	}
	
		    }
	    }finally{
	    	in.close();
	    }
	}
	
	public static List<String> directoryTree(File file) {
		List<String> fileList = new ArrayList<String>();
		if(file.isDirectory()) {
			for(File child : file.listFiles())
				fileList.addAll(directoryTree(child));
			fileList.add(windowsWorkaround(file.getPath()));
		}
		else
			fileList.add(windowsWorkaround(file.getPath()));
		
		return fileList;
	}
	
	private static String windowsWorkaround(String filePath) {
		return filePath.replaceAll("\\"+ System.getProperty("file.separator"), DIR_SEP);
	}


	public static String read(String filePath) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String fileData = "";
		String line;
		try{
			while((line = br.readLine()) != null) fileData += line + "\n";
		}finally{
			br.close();
			fr.close();
		}
		return fileData;
	}

	public static void copyFile(String orig, String dest){
		copyFile(new File(orig), new File(dest));
	}
	
	
	public static void copyFile(File orig, File dest){
		
		InputStream in;
		try {
			in = new FileInputStream(orig);
	
		OutputStream out = new FileOutputStream(dest);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
		   out.write(buf, 0, len);
		}
		in.close();
		out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static void copy(File src, File dest)
		    	throws IOException{
		
		System.out.println(src.getAbsolutePath() + " to " +dest.getAbsoluteFile());
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copy(srcFile,destFile);
    		}
 
    	}else{
    		copyFile(src, dest);
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes
    	        try{
    	        	while ((length = in.read(buffer)) > 0)
    	        		out.write(buffer, 0, length);
    	        }finally{
    	        	in.close();
    	        	out.close();
    	        }
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
	 }
	 
	 public static File copy(String src, String dest) throws IOException{
		 
		 File d = new File(dest);
		 if(src.matches(".*//*$")){
			 src = src.substring(0, src.length()-1);
			 File s = new File(src);
			 
			 File[] toCopy = s.listFiles();
			 for(File f : toCopy)
				 copy(f, d);
		 }else{
			 File s = new File(src);
			 
			 if(s.isFile() && d.isDirectory())
				 d = new File(d,s.getName());			 
			 copy(s, d);
		 }
		 
		 return d;
	 }
	
	 static public Map<String, String[]> readTableFileFormat(File file, String sep, int indexKey) throws IOException{
		 FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			
			Map<String, String[]> data = new LinkedHashMap<String, String[]>();
			String line = br.readLine();
			Integer i = 0;
			while(line !=null){
				if(!line.trim().equals("")){
					String[] lineData = line.split(sep,-1);
					String id = i+"";
					if(indexKey!=-1)
						id = lineData[indexKey].trim();
					
					data.put(id, lineData);
				}
				line = br.readLine();
				i++;
			}
			
			br.close();
			fr.close();
			
			return data;
	 }
	 
	 
	static public Map<String, String[]> readTableFileFormat(String filePath, String sep, int indexKey) throws IOException{

		return readTableFileFormat(new File(filePath), sep, indexKey);
	}
	
	static public Map<String, Map<String, String>> readTableFileFormat(String filePath, String sep, int indexKey, boolean hasHeaders) throws Exception{
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		
		Collection<String> headers = null;
		Map<String, Map<String,String>> data = new LinkedHashMap<String, Map<String,String>>();
		
		try{
			String line = br.readLine();
		
		
			headers = CollectionUtils.parseSeparatedString(line, sep);
			if(!hasHeaders){
				int nColumns = headers.size();
				headers = new LinkedHashSet<String>();
				for(int i =0 ; i < nColumns; i++)
				headers.add(i+"");
			}
			
			Integer i = 0;
			while(line !=null){
				
				String[] lineData = line.split(sep);
				Map<String, String> info = putIdentifiers(headers, lineData);
				String id = i+"";
				if(indexKey!=-1)
					id = lineData[indexKey];
				
				data.put(id, info);
				line = br.readLine();
				i++;
			}
		}finally{
		
			br.close();
			fr.close();
		}
		
		return data;
	}
	
	private static Map<String, String> putIdentifiers(Collection<String> tags,
			String[] lineData) throws Exception {
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(tags.size() != lineData.length)
			throw new Exception();
		
		int i = 0;
		for(String id : tags){
			map.put(id, lineData[i]);
			i++;
		}
		
		return map;
	}

	public static void saveInputStreamInFile(InputStream stream, FileWriter f) throws IOException{
		
		InputStreamReader st = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(st);
		
		String line;
		try{
			while ((line = br.readLine()) != null) 
				f.append(line+"\n");
		}finally{
			st.close();
			br.close();
		}
	}
	
	public static String getFileExtension(File file) throws FileNotFoundException{
		if(file!=null && file.exists()){
			return getFileExtension(file.getAbsolutePath());
		}
		else if(file==null)
			throw new NullPointerException("File is null");
		else 
			throw new FileNotFoundException("File ["+file.getAbsolutePath()+"] does not seem to exist.");
	}
	
	public static String getFileExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
	public static File createTempDirectory(String prefix, String suffix) throws IOException	{
		final File temp;
		
		temp = File.createTempFile(prefix, suffix);
		
		if(!(temp.delete()))
			throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
		
		if(!(temp.mkdir()))
			throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
		
		return (temp);
	}

}
