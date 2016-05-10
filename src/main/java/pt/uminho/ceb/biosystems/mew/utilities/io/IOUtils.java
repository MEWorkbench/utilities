package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class IOUtils {
	
	private static final int BUFFER_SIZE = 0x1000;
	
	public static byte[] toByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		byte [] ret = out.toByteArray();		
		in.close();
		out.flush();
		out.close();
		return ret;
	}
	
	public static long copy(InputStream from, OutputStream to) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		long total = 0;
		while (true) {
			int r = from.read(buf);
			if (r == -1) {
				break;
			}
			to.write(buf, 0, r);
			total += r;
		}
		return total;
	}

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
