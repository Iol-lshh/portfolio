package test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

	public static void error(String message) {

		File workFile = new File(".");
		String workSpace = workFile.getAbsolutePath().substring(0, workFile.getAbsolutePath().lastIndexOf("eclipse"));
		String project = "LTNS_jsp_re";
		String savePath = workSpace + project + "/src/main/java/com/log/error";
		String fileName = "error.txt";

		File file = new File(savePath, fileName);
		StringBuffer log = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			log = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				log.append(line + "\n");
			}

			Date today = new Date();
			StringBuffer regDate = new StringBuffer();
			regDate.append("[");
			regDate.append(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(today));
			regDate.append("]");

			log.append(regDate);
			log.append(" ");
			log.append(message);

			br.close();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(log.toString());
			bw.flush();

			bw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
