package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class IOUtils {

	public static String readInputStream(InputStream is, int bufferSize) throws IOException {
		char[] buffer = new char[bufferSize];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(is, "UTF-8");
		try {
			int rsz;
			while((rsz = in.read(buffer, 0, buffer.length)) > 0)
				out.append(buffer, 0, rsz);
		} finally {
			in.close();
		}
		
		return out.toString();
	}
	
	
    public static byte[] gzip(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.close();
        return obj.toByteArray();
     }

      public static String gunzip(byte[] in) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(in));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null)
          outStr += line;
        
        return outStr;
     }

}
