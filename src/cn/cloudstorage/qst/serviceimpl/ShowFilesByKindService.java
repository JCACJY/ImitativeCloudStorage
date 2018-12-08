package cn.cloudstorage.qst.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cloudstorage.qst.domain.SameKindFile;
import cn.cloudstorage.qst.util.FileTypeMapping;

@Component("showFilesByKindService")
@Scope("prototype")
public class ShowFilesByKindService {
	/**
	 * @param path      	当前所在目录，用户home目录所在的真实路径
	 * @param fileType  	想要查找的文件的类型，可以传image,application,text,audio,video,zip类型
	 * @param applicationURL应用的URL，用于组合资源的URL以供客户端下载
	 * @return Set			返回想要查找的所有符合类型的文件集合 
	 */
	private Set<SameKindFile> files=new HashSet<SameKindFile>();	//存放符合类型的文件
	private List<String> fileType_MIME_List;						//描述需要查找的文件的MIME类型
	private String applicationPath;
	public Set<SameKindFile> showSameKindFile(String fileType,String path,String applicationURL){
		this.applicationPath=applicationURL;		//得到项目的访问路径
		fileType_MIME_List=FileTypeMapping.fileTypeMap.get(fileType);//取得MIME类型(集合)
		File file=new File(path);
		return getFiles(file, fileType_MIME_List);
	}
	private Set<SameKindFile> getFiles(File file,List<String> fileType){
		if(file!=null){	    						//判断文件对象是否为空
			if(file.isDirectory()){	 				//如果是文件夹就继续向下查找
				File[] f= file.listFiles();			//列出全部的文件
				if(f!=null){						//判断此目录能否列出
					for(int i=0;i<f.length;i++){
						getFiles(f[i],fileType);	//递归搜索子目录的文件
					}
				}
			}else{
				//不是文件夹，先判断是否是需要的类型，如果是，则添加到set集合
				boolean except=fileType.contains(getContentType(file.getPath()));//判断是否是想找的类型的文件
				if(except){
					SameKindFile file_expect=new SameKindFile();
					file_expect.setFilename(file.getName());//名称
					String halfpath=file.getPath().substring(file.getPath()
										.indexOf("upload\\")+7).replace("\\", "/");
					file_expect.setFilepath(applicationPath+halfpath);//路径--资源的URL---可下载
					file_expect.setFilepathonserver(file.getPath());//路径--文件在服务端的地址
					//file_expect.setFilesize(file.length()/1024+"K");
					if(file.length()/1024<1000L){					//大小
						file_expect.setFilesize(new Formatter().format("%.1f", file.length()/(1024.0f))+"K");
					}else{
						file_expect.setFilesize(new Formatter().format("%.1f", file.length()/(1024*1024.0f))+"M");
					}
					file_expect.setFiletype(file_expect.getFilename().
											substring(file_expect.getFilename().lastIndexOf(".")+1));	//后缀类型
					file_expect.setFiledate(getLastModifiedDate(file));//日期
					files.add(file_expect);
				}
			}
		}
		return files;
	}
	//取得文件的MIME类型
	private String getContentType(String filename){
		String type = null;
		Path path = Paths.get(filename);
		try {
			type = Files.probeContentType(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return type+"";
	}
	//取得文件最后修改时间
	private String getLastModifiedDate(File f){
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
