package cn.cloudstorage.qst.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTypeMapping {
	private FileTypeMapping(){}//私有化构造
	public static Map<String, List<String>> fileTypeMap;
	static{
		fileTypeMap=new HashMap<String, List<String>>();
	//----------------------------------------------------------------------------------//
			List<String> list_image=new ArrayList<String>();
			list_image.add("image/gif");		//gif
			list_image.add("image/jpeg");		//jpg
			list_image.add("image/png");		//png
		fileTypeMap.put("image", list_image);					//图片---image
			List<String> list_text=new ArrayList<String>();
			list_text.add("text/plain");		//txt
			list_text.add("text/html");			//html
			list_text.add("application/msword");//doc
			list_text.add("text/xml");			//xml
			list_text.add("application/vnd.ms-powerpoint");//ppt
		fileTypeMap.put("text", list_text);						//文档---text
			List<String> list_application=new ArrayList<String>();
			list_application.add("application/octet-stream");//exe
		fileTypeMap.put("application", list_application);		//应用---application
			List<String> list_video=new ArrayList<String>();
			list_video.add("video/mp4");//mp4
			list_video.add("video/x-ms-wmv");//wmv
			list_video.add("video/avi");	//avi
		fileTypeMap.put("video", list_video);					//视频---video
			List<String> list_audio=new ArrayList<String>();
			list_audio.add("audio/mpeg");//mp3
			list_audio.add("audio/x-wav");//wav
			list_audio.add("audio/midi");//mid
		fileTypeMap.put("audio", list_audio);					//音乐---audio
			List<String> list_zip=new ArrayList<String>();
			list_zip.add("application/x-gzip");//gzip
			list_zip.add("application/x-tar");//tar
			list_zip.add("application/zip");//zip
		fileTypeMap.put("zip", list_zip);						//压缩包---zip
	}
}
