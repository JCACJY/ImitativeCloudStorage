package cn.cloudstorage.qst.service;

import java.io.File;
import java.util.Set;

import cn.cloudstorage.qst.domain.FileInfo;

public interface ReadFileService {
	public Set<FileInfo> getChildFiles(String presentPath,String fileURL);
	public String getLastModifiedDate(File f);

}
