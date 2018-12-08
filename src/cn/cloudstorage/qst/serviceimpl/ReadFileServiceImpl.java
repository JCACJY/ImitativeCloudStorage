package cn.cloudstorage.qst.serviceimpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.FileInfo;
import cn.cloudstorage.qst.service.ReadFileService;

@Component("readFileServiceImpl")
public class ReadFileServiceImpl implements ReadFileService{	
	/**
	 * presentPath：当前文件目录
	 * 返回值是一个set集合，里面保存了当前目录下的子文件及子目录信息
	 * 调用此API需要显式捕捉RuntimeException异常
	 * presentPath:当前真实路劲
	 * urlPrefix  :url地址前缀
	 */
	public Set<FileInfo> getChildFiles(String presentPath,String fileURL){
		Set<FileInfo> fileInfos=new HashSet<FileInfo>();
		if(presentPath==null||"".equals(presentPath)){
			throw new RuntimeException("PresentPath is error");
		}
		File file=new File(presentPath);
		if(!file.exists()){
			throw new RuntimeException("Directory does not exist");
		}
		String prefix=null;
		String fileName=null;
		File[] files=file.listFiles();
		for (File file2 : files) {
			FileInfo file_ls=new FileInfo();
			fileName=file2.getName();
			if(file2.isDirectory()){//文件夹
				file_ls.setFiletype("dir");
				file_ls.setFilesize(" --");
			}else{					//普通文件
				prefix=fileName.substring(fileName.lastIndexOf(".")+1);
				file_ls.setFiletype(prefix);				//类型
				if(file2.length()/1024<1000L){				//大小
					file_ls.setFilesize(new Formatter().format("%.1f", file2.length()/(1024.0f))+"K");
				}else{
					file_ls.setFilesize(new Formatter().format("%.1f", file2.length()/(1024*1024.0f))+"M");
				}
			}
			file_ls.setFilename(fileName);					//文件名
			file_ls.setFiledate(getLastModifiedDate(file2));//日期
			file_ls.setFileurl(fileURL+"/"+fileName);		//URL
			file_ls.setFilepathonserver(file2.getPath());
			fileInfos.add(file_ls);
		}
		return fileInfos;
	}
	public String getLastModifiedDate(File f){
		try {
			Calendar cal = Calendar.getInstance();  
	        long time = f.lastModified();  
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");         
	        cal.setTimeInMillis(time);    
	        return formatter.format(cal.getTime()); 
		} catch (Exception e) {
			return "";
		}
	}
}
