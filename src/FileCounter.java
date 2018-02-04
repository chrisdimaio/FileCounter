import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.lang.Runtime;
import java.lang.Process;

public class FileCounter
{
	private static final int ACTIVITY_FREQUENCY = 100;
	private static String activityIcon = "-";
	private static int activityCounter = ACTIVITY_FREQUENCY;
	private static long lastActivityChange = 0;
	
	public static void main(String args[])
	{
		ArrayList<File> files = findFiles(args[0], null);
		
		
		System.out.println(files.size() + " files counted.");
    }
	
	public static ArrayList<File> findFiles(String directory, ArrayList<File> fileList)
	{
		if(fileList == null)
		{
			fileList = new ArrayList();
		}
	
		File file = new File(directory);
		File list[] = file.listFiles();
		
		if(list != null)
		{
			for(int i = 0; i < list.length; i++)
			{
				File tmp = list[i];
				if(tmp.isFile())
				{
					fileList.add(tmp);
				}
				else if(tmp.isDirectory())
				{
					findFiles(tmp.getPath(), fileList);
				}
				showActivity();
			}
		}
		return fileList;
	}
	
	private static void showActivity()
	{
		long timeLapse = System.currentTimeMillis() - lastActivityChange;
		if(timeLapse >= ACTIVITY_FREQUENCY)
		{
			System.out.print(activityIcon + "\r");
			updateActivityIcon();
		}
	}
	
	private static void updateActivityIcon()
	{
		if(activityIcon.equals("-"))
		{
			activityIcon = "\\";
		}
		else if(activityIcon.equals("\\"))
		{
			activityIcon = "|";
		}
		else if(activityIcon.equals("|"))
		{
			activityIcon = "/";
		}
		else
		{
			activityIcon = "-";
		}
		lastActivityChange = System.currentTimeMillis();
	}
}