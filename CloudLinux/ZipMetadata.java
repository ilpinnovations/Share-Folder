
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipMetadata {
	
	public static void main(String args[]){
		
	}
	
	
	 void zip(String result,String resultPath,String directoryZip){
		 try {
				FileOutputStream fos = new FileOutputStream(directoryZip);
				ZipOutputStream zos = new ZipOutputStream(fos);
				String file=result;

				addToZipFile(file, zos,resultPath);
				
				zos.close();
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 public void addToZipFile(String fileName, ZipOutputStream zos,String path) throws FileNotFoundException, IOException {

			System.out.println("Writing '" + fileName + "' to zip file");

			File file = new File(fileName);
			System.out.println(file.getName());
			FileInputStream fis = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(path);
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}

			zos.closeEntry();
			fis.close();
		}
	}